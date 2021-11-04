package fr.gouv.finances.dgfip.banque.v2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import fr.gouv.finances.dgfip.banque.v2.CompteException;
import fr.gouv.finances.dgfip.banque.v2.Gabier;
import fr.gouv.finances.dgfip.banque.v2.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;

@SpringBootTest
@AutoConfigureMockMvc
public class BanqueServiceWithContextTest {

  @Autowired
  private BanqueServiceInterface banqueService;

  @Autowired
  private PersonneServiceInterface personneService;

  @Autowired
  CompteBancaireServiceInterface compteBancaireService;

  @Autowired
  Gabier gabier;

  @BeforeEach
  public void populate() throws Exception {
    Banque maBanque = banqueService.creerBanque("DGFIP");
    Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
    Personne dominique = personneService.creerPersonne("Guibert", "Dominique");
    Personne thibault = personneService.creerPersonne("Guillou", "Thibault");
    Personne andre = personneService.creerPersonne("Labbe", "André");

    CompteCourant ccPaulette = banqueService.creerCompteCourant(maBanque,
        paulette, "1234");
    compteBancaireService.creerOperation(ccPaulette, "Dépôt chèque", 100.0);
//    compteBancaireService.afficherSyntheseOperations(ccPaulette);

    CompteCourant ccDominique = banqueService.creerCompteCourant(maBanque,
        dominique, "5678");
    compteBancaireService.creerOperation(ccDominique, "Dépôt espèces", 200.0);
//    compteBancaireService.afficherSyntheseOperations(ccDominique);

    compteBancaireService.creerOperation(ccPaulette, "Débit", -50.0);
    compteBancaireService.creerOperation(ccDominique, "Débit", -300.0);

//    compteBancaireService.afficherSyntheseOperations(ccPaulette);
//    compteBancaireService.afficherSyntheseOperations(ccDominique);

    CompteEpargne ceThibault = banqueService.creerCompteEpargne(maBanque,
        thibault, "1234", 3000.0, 0.02);
    compteBancaireService.creerOperation(ceThibault, "Dépôt", 1000.0);
    try {
      compteBancaireService.creerOperation(ceThibault, "Retrait", -10000.0);
    } catch (CompteException ce) {
    }

    banqueService.creerCompteEpargne(maBanque, andre, "5678", 10000.0, 0.02);

//    banqueService.afficherSyntheseComptes(maBanque);

    CarteBancaire cbPaulette = banqueService.creerCarte(maBanque, paulette,
        ccPaulette);
    CarteBancaire cbDominique = banqueService.creerCarte(maBanque, dominique,
        ccDominique);

//    banqueService.afficherSyntheseCartes(maBanque);

    try {
      String ribComptePaulette = gabier.accesComptes(maBanque,
          cbPaulette.getNumCarte(), cbPaulette.getCodePin());
      gabier.retirerEspeces(maBanque, ribComptePaulette, 314.0);
//      compteBancaireService.afficherSyntheseOperations(ccPaulette);
    } catch (SystemeBancaireException e) {
    }

    try {
      String ribCompteDominique = gabier.accesComptes(maBanque,
          cbDominique.getNumCarte(), "CODE PIN FAUX");
      gabier.retirerEspeces(maBanque, ribCompteDominique, 567.0);
//      compteBancaireService.afficherSyntheseOperations(ccDominique);
    } catch (SystemeBancaireException e) {
    }
  }

  @AfterEach
  public void cleanUpEach() {
    banqueService.deleteAllCartebancaire();
    banqueService.deleteAllCompteBancaire();
    personneService.deleteAllPersonne();
    banqueService.deleteAllBanque();
  }

  @Test
  @Transactional
  public void checkContextTest() throws Exception {
    Banque maBanque = banqueService.getBanque("DGFIP");
    assertEquals(maBanque.getSetCompteBancaire().size(), 4);
    assertEquals(maBanque.getAdherents().size(), 4);
    assertEquals(maBanque.getSetCarte().size(), 2);
  }

  @Test
  public void creerBanqueTest() throws Exception {
    Banque banqueInDB = banqueService.creerBanque("AnotherBanque");
    assertTrue(banqueInDB.getCodeBanque().equals("AnotherBanque"));
    assertEquals(banqueService.listeBanque().size(), 2);
  }

  @Test
  public void creerBanqueTest_Duplicate() throws Exception {
    try {
      banqueService.creerBanque("DGFIP");
    } catch (Exception e) {
      assertTrue(true);
      return;
    }
    assertTrue(false);
  }

  @Test
  public void getCompteBancaireTest() throws Exception {
    CompteBancaire compteBancaire = banqueService
        .getCompteBancaire("0000000000");
    assertTrue(compteBancaire.getTitulaire().getNom().equals("Blanchard"));
    assertTrue(compteBancaire.getTitulaire().getPrenom().equals("Paulette"));
  }

  @Test
  @Transactional
  public void updateCompteBancaireTest() throws Exception {
    CompteBancaire compteBancaire = banqueService
        .updateCompteBancaire("0000000000", new Personne("Delon", "Alain"));
    assertTrue(compteBancaire.getTitulaire().getNom().equals("Delon"));
    assertTrue(compteBancaire.getTitulaire().getPrenom().equals("Alain"));

    Banque maBanque = banqueService.getBanque("DGFIP");
    assertEquals(maBanque.getSetCompteBancaire().size(), 4);
    assertEquals(maBanque.getSetCarte().size(), 2);
  }

  @Transactional
  private void anotherTransaction() {
    Banque maBanque = banqueService.getBanque("DGFIP");
    System.err.println(
        "maBanque.getSetCompteBancaire(): " + maBanque.getSetCompteBancaire());
    assertEquals(maBanque.getSetCompteBancaire().size(), 3);
    assertEquals(maBanque.getSetCarte().size(), 2);
  }

  @Test
//  @Transactional
  public void deleteCompteBancaire() throws Exception {
    banqueService.deleteCompteBancaire("0000000000");

    anotherTransaction();

//    Banque maBanque = banqueService.getBanque("DGFIP");
//    System.err.println(
//        "maBanque.getSetCompteBancaire(): " + maBanque.getSetCompteBancaire());
//    assertEquals(maBanque.getSetCompteBancaire().size(), 3);
//    assertEquals(maBanque.getSetCarte().size(), 2);
  }

//  @Test
//  public void creerCompteCourantTest() throws Exception {
//    Banque maBanque = banqueService.creerBanque("DGFIP");
//    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
//    CompteCourant compteCourantInDB = banqueService.creerCompteCourant(maBanque,
//        personne, "1234");
//    assertTrue(compteCourantInDB.getTitulaire().equals(personne));
//    assertTrue(compteCourantInDB.getBanque().equals(maBanque));
//  }
//
//  @Test
//  public void creerCompteCourantTest_Duplicate() throws Exception {
//    Banque maBanque = banqueService.creerBanque("DGFIP");
//    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
//    banqueService.creerCompteCourant(maBanque, personne, "1234");
//    CompteCourant compteCourantInDB2 = banqueService
//        .creerCompteCourant(maBanque, personne, "1234");
//
//    assertNotEquals(Integer.parseInt(compteCourantInDB2.getNumCompte()), 0);
//    assertEquals(Integer.parseInt(compteCourantInDB2.getNumCompte()), 1);
//  }
//
//  @Test
//  public void creerCompteCourantSoldeTest() throws Exception {
//    Banque maBanque = banqueService.creerBanque("DGFIP");
//    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
//    CompteCourant compteCourantInDB = banqueService.creerCompteCourant(maBanque,
//        personne, "1234", 1000.);
//    assertEquals(compteCourantInDB.getSolde(), 1000.);
//  }
//
//  @Test
//  public void creerCompteEpargneTest() throws Exception {
//    Banque maBanque = banqueService.creerBanque("DGFIP");
//    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
//    CompteEpargne compteEpargneInDB = banqueService.creerCompteEpargne(maBanque,
//        personne, "1234", 1000., 5.);
//    assertEquals(compteEpargneInDB.getSolde(), 1000.);
//    assertEquals(compteEpargneInDB.getTxInteret(), 5.);
//  }
//
//  @Test
//  public void creerCarteBancaireTest() throws Exception {
//    Banque maBanque = banqueService.creerBanque("DGFIP");
//    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
//    CompteCourant compteCourant = banqueService.creerCompteCourant(maBanque,
//        personne, "1234");
//    CarteBancaire carteBancaireInDB = banqueService.creerCarte(maBanque,
//        personne, compteCourant);
//    assertTrue(carteBancaireInDB.getTitulaire().equals(personne));
//    assertTrue(carteBancaireInDB.getBanque().equals(maBanque));
//    assertTrue(carteBancaireInDB.getCompteCourant().equals(compteCourant));
//  }
//
//  @Test
//  public void creerCarteBancaireTest_Duplicate() throws Exception {
//    Banque maBanque = banqueService.creerBanque("DGFIP");
//    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
//    CompteCourant compteCourant = banqueService.creerCompteCourant(maBanque,
//        personne, "1234");
//    CarteBancaire carteBancaireInDB1 = banqueService.creerCarte(maBanque,
//        personne, compteCourant);
//    CarteBancaire carteBancaireInDB2 = banqueService.creerCarte(maBanque,
//        personne, compteCourant);
//    assertNotEquals(carteBancaireInDB1.getNumCarte(),
//        carteBancaireInDB2.getNumCarte());
//  }
}
