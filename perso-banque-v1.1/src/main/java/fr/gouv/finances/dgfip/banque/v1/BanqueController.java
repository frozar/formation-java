package fr.gouv.finances.dgfip.banque.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.PersonneServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.SystemeBancaireInterface;

@Controller
public class BanqueController implements WebMvcConfigurer {

  private final Banque banque = new Banque("DGFiP");

  @Autowired
  BanqueServiceInterface banqueService;

  @Autowired
  PersonneServiceInterface personneService;

  @Autowired
  SystemeBancaireInterface systemeBancaireService;

  @RequestMapping("/")
  public String home(Model model) {
//    model.put("message", this.message);
    model.addAttribute("adherents",
        systemeBancaireService.listeAdherent(banque));
    return "home";
  }

//  @RequestMapping(value = "/add-person", method = RequestMethod.GET)
//  @RequestMapping("/addPerson")
  @GetMapping("/add-person")
  public String addPerson() {
    return "formAddPerson";
  }

  @GetMapping("/response-add-person")
  public String responseAddPerson(
      @RequestParam(name = "prenom", required = true) String prenom,
      @RequestParam(name = "nom", required = true) String nom) {
    System.out.println("prenom: " + prenom);
    System.out.println("nom: " + nom);
    return "formResponseAddPerson";
  }

////  public ModelAndView addPersonModelAndView() {
////    return new ModelAndView("formAddPersonModelAndView", "person",
////        personneService.creerPersonne("", ""));
////  }
//  @GetMapping("/add-person-model-and-view")
//  public String addPersonModelAndView(Personne personne) {
//    return "formAddPersonModelAndView";
//  }
//
//  // @RequestMapping(value = "/response-add-person-model-and-view", method =
//  // RequestMethod.POST)
//  @PostMapping("/response-add-person-model-and-view")
////  public ModelAndView addPersonModelAndView(
//  public String addPersonModelAndView(@Valid Personne personne,
//      BindingResult result) {
//    if (result.hasErrors()) {
////      return new ModelAndView("formAddPersonModelAndView", "person",
////          personne);
//      return "formAddPersonModelAndView";
//    }
////    model.addAttribute("nom", personne.getNom());
////    model.addAttribute("prenom", personne.getPrenom());
////    return new ModelAndView("formResponseAddPersonModelAndView", "person",
////        personne);
////    return "redirect:/formResponseAddPersonModelAndView";
//    return "formResponseAddPersonModelAndView";
//  }

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

  @GetMapping("/add-current-account-full")
  public String addAccount(ModelMap model) {
    CompteCourant compteCourant = new CompteCourant();
    compteCourant.setSolde(0.0);
    model.addAttribute("personne", new Personne());
    model.addAttribute("compteCourant", compteCourant);
    model.addAttribute("banque", banque);

    return "formAddCurrentAccountFull";
  }

  // NB: Don't dash for @ModelAttribute argument
  // @ModelAttribute("compteCourant") : OK
  // @ModelAttribute("compte-courant") : FAILED
  @PostMapping("/add-current-account-full")
  public String addAccountSubmit(
      @Valid @ModelAttribute("personne") Personne personne,
      BindingResult resultPersonne,
      @Valid @ModelAttribute("compteCourant") CompteCourant compteCourant,
      BindingResult resultCompte, Model model) {
    if (resultPersonne.hasErrors() || resultCompte.hasErrors()) {
      return "formAddCurrentAccount";
    }

    try {
      banqueService.creerCompteCourant(banque, personne,
          compteCourant.getCodeGuichet());
    } catch (CompteException e) {
      // As both Personne and CompteCourant models are valided,
      // should never happen.
      e.printStackTrace();
    }

    return "formResponseAddCurrentAccountFull";
  }

//  banqueService.creerCompteCourant(maBanque, paulette, "1234");

}
