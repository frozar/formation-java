package fr.gouv.finances.dgfip.banque.v1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.BanqueServiceInterface;

@Controller
public class BanqueController {

  private Banque banque = new Banque("CREDIT974");

  @Autowired
  private BanqueServiceInterface banqueService;

  @Autowired
  private SystemeBancaireInterface systemeBancaireService;

  @RequestMapping("/")
  public String home(Model model) {
    // model.put("message", this.message);
    model.addAttribute("adherents",
        systemeBancaireService.listeAdherent(banque));
    return "home";
  }

  @RequestMapping("/creerBanque")
  public String creerBanque() {
    // model.put("message", this.message);
    return "creerBanque";
  }

  @GetMapping("/formAddPersonne")
  public String addPerson() {
    return "formAddPersonne";
  }

  @GetMapping("/formResponseAddPersonne")
  public String responseAddPersonne(
      @RequestParam(name = "nom", required = true) String nom,
      @RequestParam(name = "prenom", required = true) String prenom) {
    System.out.println("nom: " + nom);
    System.out.println("prenom: " + prenom);
    return "formResponseAddPersonne";
  }

  @GetMapping("/add-person-model-and-view")
  public String showForm(Model model) {
    Personne personne = new Personne();
    personne.setPrenom("Nam Ha Minh");
    model.addAttribute("personne", personne);

    return "formAddPersonModelAndView";
  }

  @PostMapping("/add-person-model-and-view")
  public String submitForm(@Valid @ModelAttribute("personne") Personne personne,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      System.out.println("bindingResult: " + bindingResult);
      return "formAddPersonModelAndView";
    }

    return "formResponseAddPersonModelAndView";
  }

  @GetMapping("/add-current-account")
  public String addAccount(ModelMap model) {
    Personne personne = new Personne();
    CompteCourant compteCourant = new CompteCourant();
    model.addAttribute("personne", personne);
    model.addAttribute("compte-courant", compteCourant);
    model.addAttribute("codeBanque", banque.getCodeBanque());

    return "formAddCurrentAccount";
  }

  @PostMapping("/add-current-account")
  public String addAccountSubmit(
      @Valid @ModelAttribute("personne") Personne personne,
      BindingResult resultPersonne,
      @Valid @ModelAttribute("compte-courant") CompteCourant compteCourant,
      BindingResult resultCompte, Model model) {
    if (resultPersonne.hasErrors() || resultCompte.hasErrors()) {
      System.out.println("resultPersonne: " + resultPersonne);
      System.out.println("resultCompte: " + resultCompte);
      return "formAddCurrentAccount";
    }

    System.out.println("personne: " + personne);
    System.out.println("compteCourant: " + compteCourant);

    try {
      banqueService.creerCompteCourant(banque, personne,
          compteCourant.getCodeGuichet(), compteCourant.getSolde());
    } catch (CompteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return "formResponseAddCurrentAccount";
  }

  @GetMapping("/add-current-account-model-and-view")
  public ModelAndView addAccount() {
    return new ModelAndView("formAddCurrentAccountModelAndView",
        "addCompteCourantForm", new AddCompteCourantForm());
  }

  @PostMapping("/add-current-account-model-and-view")
  public String submitFormAccount(
      @Valid @ModelAttribute("addCompteCourantForm") AddCompteCourantForm newCompteCourant,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      System.out.println("bindingResult: " + bindingResult);
      return "formAddCurrentAccountModelAndView";
    }
    try {
      Personne personne = new Personne(newCompteCourant.getNom(),
          newCompteCourant.getPrenom());
      // Effectively create a current accompte
      CompteCourant compteCourant = banqueService.creerCompteCourant(banque,
          personne, newCompteCourant.getCodeGuichet());
      model.addAttribute("personne", personne);
      model.addAttribute("compteCourant", compteCourant);
      return "formResponseAddCurrentAccountModelAndView";
    } catch (CompteException e) {
      // As both Personne and CompteCourant models are valided,
      // this catch should never happen.
      e.printStackTrace();
      return null;
    }
//		return "formResponseAddCurrentAccountModelAndView";
  }

  // tous les comptes:
  @GetMapping("/synthese-compte")
  public String syntheseCompte(Model model,
      @ModelAttribute("error") String error) {
    HashMap<CompteBancaire, Personne> mapCompteAPersonne = banque
        .getMapCompteAPersonne();

    model.addAttribute("error", error);
    model.addAttribute("compteAPersonne", mapCompteAPersonne);
    return "syntheseCompte";
  }

  // pour un compte:
  @GetMapping("/form-synthese-compte")
  public String syntheseCompte() {
    return "formSyntheseCompte";
  }

  @GetMapping("/form-response-synthese-compte")
  public String responseSyntheseCompte(
      @RequestParam(name = "nom", required = true) String nom,
      @RequestParam(name = "prenom", required = true) String prenom,
      Model model) {
//			System.out.println("nom: " + nom);
//			System.out.println("prenom: " + prenom);
    HashMap<CompteBancaire, Personne> mapCompteAPersonne = banque
        .getMapCompteAPersonne();

    Set<CompteBancaire> result = new HashSet<CompteBancaire>();

    for (Map.Entry<CompteBancaire, Personne> entry : mapCompteAPersonne
        .entrySet()) {
      CompteBancaire compte = entry.getKey();
      Personne personne = entry.getValue();

      if (personne.getNom().equals(nom)
          && personne.getPrenom().equals(prenom)) {
        result.add(compte);
      }
    }

    model.addAttribute("comptes", result);

    return "formResponseSyntheseCompte";
  }

  @RequestMapping("/syntheseBanque")
  public String syntheseBanque() {
    // model.put("message", this.message);

//		  SpringApplication.
    /*
     * Banque maBanque = new Banque("DGFIP"); BanqueServiceInterface
     * banqueService = context.getBean(BanqueServiceInterface.class);
     * PersonneServiceInterface personneService =
     * context.getBean(PersonneServiceInterface.class);
     * CompteBancaireServiceInterface compteBancaireService =
     * context.getBean(CompteBancaireServiceInterface.class);
     * 
     * Personne paulette = personneService.creerPersonne("Blanchard",
     * "Paulette"); Personne dominique =
     * personneService.creerPersonne("Guibert", "Dominique"); Personne thibault
     * = personneService.creerPersonne("Guillou", "Thibault"); Personne andre =
     * personneService.creerPersonne("Labbe", "André");
     * 
     * LOGGER.info("*********************************************************");
     * LOGGER.info("*** Ouverture d'un compte courant Paulette Blanchard  ***");
     * CompteCourant ccPaulette = banqueService.creerCompteCourant(maBanque,
     * paulette, "1234");
     * LOGGER.info("*** Dépôt d'un chèque de 100                          ***");
     * compteBancaireService.creerOperation(ccPaulette, "Dépôt chèque", 100.0);
     * compteBancaireService.afficherSyntheseOperations(ccPaulette);
     * LOGGER.info("*********************************************************\n"
     * );
     */

    return "syntheseBanque";
  }

}
