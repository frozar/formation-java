package fr.gouv.finances.dgfip.banque.v2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;
import fr.gouv.finances.dgfip.banque.v2.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v2.services.CompteBancaireServiceInterface;
import fr.gouv.finances.dgfip.banque.v2.services.PersonneServiceInterface;

@Component
public class InitDonneesRunner implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(InitDonneesRunner.class);

  // Décommenter le code suivant après l'implémentation
  BanqueServiceInterface banqueService;
  PersonneServiceInterface personneService;
  CompteBancaireServiceInterface compteBancaireService;
  Gabier gabier;

  @Override
  public void run(String... args) throws Exception {
    Banque maBanque = banqueService.creerBanque("DGFIP");
    Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
    Personne dominique = personneService.creerPersonne("Guibert", "Dominique");
    Personne thibault = personneService.creerPersonne("Guillou", "Thibault");
    Personne andre = personneService.creerPersonne("Labbe", "André");

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Ouverture d'un compte courant Paulette Blanchard  ***");
    CompteCourant ccPaulette = banqueService.creerCompteCourant(maBanque,
        paulette, "1234");
    LOGGER.info("*** Dépôt d'un chèque de 100                          ***");
    compteBancaireService.creerOperation(ccPaulette, "Dépôt chèque", 100.0);
    compteBancaireService.afficherSyntheseOperations(ccPaulette);
    LOGGER.info("*********************************************************\n");

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Ouverture d'un compte courant Dominique Guibert   ***");
    CompteCourant ccDominique = banqueService.creerCompteCourant(maBanque,
        dominique, "5678");
    LOGGER.info("*** Dépôt d'un chèque de 200                          ***");
    compteBancaireService.creerOperation(ccDominique, "Dépôt espèces", 200.0);
    compteBancaireService.afficherSyntheseOperations(ccDominique);
    LOGGER.info("*********************************************************\n");

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Débit de 50 sur le compte     Paulette Blanchard  ***");
    compteBancaireService.creerOperation(ccPaulette, "Débit", -50.0);
    LOGGER.info("*** Débit de 300 sur le compte    Dominique Guibert   ***");
    compteBancaireService.creerOperation(ccDominique, "Débit", -300.0);
    LOGGER.info("*********************************************************\n");

    compteBancaireService.afficherSyntheseOperations(ccPaulette);
    LOGGER.info("\n");
    compteBancaireService.afficherSyntheseOperations(ccDominique);

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Ouverture d'un compte épargne Thibault Guillou    ***");
    LOGGER.info("*** montant initial=3000, taux=2%                     ***");
    CompteEpargne ceThibault = banqueService.creerCompteEpargne(maBanque,
        thibault, "1234", 3000.0, 0.02);
    LOGGER.info("*** Crédit de 1000 sur le compte Thibaud Guillou      ***");
    compteBancaireService.creerOperation(ceThibault, "Dépôt", 1000.0);
    try {
      LOGGER.info("*** Débit de 10000 sur le compte Thibaud Guillou      ***");
      compteBancaireService.creerOperation(ceThibault, "Retrait", -10000.0);
    } catch (CompteException ce) {
      LOGGER.error(
          "*** Impossible de débiter le compteEpargne1: " + ce.getMessage());
    }
    LOGGER.info("*********************************************************\n");

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Ouverture d'un compte épargne André Labbe         ***");
    LOGGER.info("*** montant initial=10000, taux=2%                    ***");
    banqueService.creerCompteEpargne(maBanque, andre, "5678", 10000.0, 0.02);
    LOGGER.info("*********************************************************\n");

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Synthèse banque                                   ***");
    banqueService.afficherSyntheseComptes(maBanque);
    LOGGER.info("*********************************************************\n");

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Création CB Paulette                              ***");
    CarteBancaire cbPaulette = banqueService.creerCarte(maBanque, paulette,
        ccPaulette);
    LOGGER.info("*** Création CB Dominique                             ***");
    CarteBancaire cbDominique = banqueService.creerCarte(maBanque, dominique,
        ccDominique);
    LOGGER.info("*********************************************************\n");

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Synthèse cartes                                   ***");
    banqueService.afficherSyntheseCartes(maBanque);
    LOGGER.info("*********************************************************\n");

    LOGGER.info("*********************************************************");
    LOGGER.info("*** Retrait de 314 sur le compte courant de Paulette  ***");
    try {
      List<String> comptesPaulette = gabier.accesComptes(maBanque,
          cbPaulette.getNumCarte(), cbPaulette.getCodePin());
      LOGGER.info("*** Liste de comptes de Paulette:");
      for (String rib : comptesPaulette) {
        LOGGER.info("***  - " + rib);
      }
      int numOperation = gabier.retirerEspeces(maBanque, comptesPaulette.get(0),
          314.0);
      LOGGER
          .info(String.format("*** Opération réalisée: %d  ***", numOperation));
      compteBancaireService.afficherSyntheseOperations(ccPaulette);
    } catch (SystemeBancaireException e) {
      LOGGER
          .error("Accès impossible aux comptes de Paulette: " + e.getMessage());
    }
    LOGGER.info("*********************************************************\n");

    LOGGER.info("***********************************************************");
    LOGGER.info("*** Tentative retrait de 567 sur le compte de Dominique ***");
    try {
      List<String> comptesDominique = gabier.accesComptes(maBanque,
          cbDominique.getNumCarte(), "CODE PIN FAUX");
      LOGGER.info("*** Liste de comptes de Dominique:");
      for (String rib : comptesDominique) {
        LOGGER.info("***  - " + rib);
      }
      int numOperation = gabier.retirerEspeces(maBanque,
          comptesDominique.get(0), 567.0);
      LOGGER
          .info(String.format("*** Opération réalisée: %d  ***", numOperation));
    } catch (SystemeBancaireException e) {
      LOGGER.error(
          "Accès impossible aux comptes de Dominique: " + e.getMessage());
      compteBancaireService.afficherSyntheseOperations(ccDominique);
    }
    LOGGER.info("*********************************************************");

  }

  @Autowired
  public void setPersonneService(PersonneServiceInterface personneService) {
    this.personneService = personneService;
  }

  @Autowired
  public void setBanqueService(BanqueServiceInterface banqueService) {
    this.banqueService = banqueService;
  }

  @Autowired
  public void setCompteBancaireService(
      CompteBancaireServiceInterface compteBancaireService) {
    this.compteBancaireService = compteBancaireService;
  }

  @Autowired
  public void setGabier(Gabier gabier) {
    this.gabier = gabier;
  }
}
