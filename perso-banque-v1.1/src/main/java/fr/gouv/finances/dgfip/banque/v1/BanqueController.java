package fr.gouv.finances.dgfip.banque.v1;

import java.util.HashMap;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
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
    model.addAttribute("adherents",
        systemeBancaireService.listeAdherent(banque));
    return "home";
  }

  @GetMapping("/synthese-compte")
  public String syntheseCompte(Model model) {
    HashMap<CompteBancaire, Personne> mapCompteAPersonne = banque
        .getMapCompteAPersonne();
    model.addAttribute("compteAPersonne", mapCompteAPersonne);
    return "syntheseCompte";
  }

  @GetMapping("/add-current-account-full")
  public String addAccountFull(ModelMap model) {
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
  public String addAccountFullSubmit(
      @Valid @ModelAttribute("personne") Personne personne,
      BindingResult resultPersonne,
      @Valid @ModelAttribute("compteCourant") CompteCourant compteCourant,
      BindingResult resultCompte, Model model) {
    if (resultPersonne.hasErrors() || resultCompte.hasErrors()) {
      // In case of error in the form, you have to bind the banque again to the
      // model as it is not retrieve from the argument of this method.
      // Otherwise, you get an exception:
      // javax.servlet.jsp.JspTagException: Neither BindingResult nor plain
      // target object for bean name 'codeBanque' available as request attribute
      model.addAttribute("banque", banque);
      return "formAddCurrentAccountFull";
    }

    try {
      banqueService.creerCompteCourant(banque, personne,
          compteCourant.getCodeGuichet());
      return "formResponseAddCurrentAccountFull";
    } catch (CompteException e) {
      // As both Personne and CompteCourant models are valided,
      // this catch should never happen.
      e.printStackTrace();
      return null;
    }
  }

  @GetMapping("/add-current-account")
  public ModelAndView showForm() {
    return new ModelAndView("formAddCurrentAccount", "compteCourantForm",
        new CompteCourantForm());
  }

  @PostMapping("/add-current-account")
  public String addAccountSubmit(
      @Valid @ModelAttribute("compteCourantForm") CompteCourantForm compteCourantForm,
      BindingResult resultCompte, Model model) {
    if (resultCompte.hasErrors()) {
      return "formAddCurrentAccount";
    }

    try {
      Personne personne = new Personne(compteCourantForm.getNom(),
          compteCourantForm.getPrenom());
      // Effectively create a current accompte
      CompteCourant compteCourant = banqueService.creerCompteCourant(banque,
          personne, compteCourantForm.getCodeGuichet());
      model.addAttribute("personne", personne);
      model.addAttribute("compteCourant", compteCourant);
      return "formResponseAddCurrentAccount";
    } catch (CompteException e) {
      // As both Personne and CompteCourant models are valided,
      // this catch should never happen.
      e.printStackTrace();
      return null;
    }
  }
}
