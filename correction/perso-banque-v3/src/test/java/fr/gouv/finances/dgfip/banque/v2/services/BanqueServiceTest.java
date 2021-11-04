package fr.gouv.finances.dgfip.banque.v2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;

@SpringBootTest
public class BanqueServiceTest {

  @Autowired
  private BanqueServiceInterface banqueService;

  @Autowired
  private PersonneServiceInterface personneService;

  @AfterEach
  public void cleanUpEach() {
    banqueService.deleteAllCartebancaire();
    banqueService.deleteAllCompteBancaire();
    personneService.deleteAllPersonne();
    banqueService.deleteAllBanque();
  }

  @Test
  public void creerBanqueTest() throws Exception {
    Banque banqueInDB = banqueService.creerBanque("DGFIP");
    assertTrue(banqueInDB.getCodeBanque().equals("DGFIP"));
  }

  @Test
  public void creerBanqueTest_Duplicate() throws Exception {
    banqueService.creerBanque("DGFIP");
    try {
      banqueService.creerBanque("DGFIP");
    } catch (Exception e) {
      assertTrue(true);
      return;
    }
    assertTrue(false);
  }

  @Test
  public void creerCompteCourantTest() throws Exception {
    Banque maBanque = banqueService.creerBanque("DGFIP");
    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
    CompteCourant compteCourantInDB = banqueService.creerCompteCourant(maBanque,
        personne, "1234");
    assertTrue(compteCourantInDB.getTitulaire().equals(personne));
    assertTrue(compteCourantInDB.getBanque().equals(maBanque));
  }

  @Test
  public void creerCompteCourantTest_Duplicate() throws Exception {
    Banque maBanque = banqueService.creerBanque("DGFIP");
    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
    banqueService.creerCompteCourant(maBanque, personne, "1234");
    CompteCourant compteCourantInDB2 = banqueService
        .creerCompteCourant(maBanque, personne, "1234");

    assertNotEquals(Integer.parseInt(compteCourantInDB2.getNumCompte()), 0);
    assertEquals(Integer.parseInt(compteCourantInDB2.getNumCompte()), 1);
  }

  @Test
  public void creerCompteCourantSoldeTest() throws Exception {
    Banque maBanque = banqueService.creerBanque("DGFIP");
    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
    CompteCourant compteCourantInDB = banqueService.creerCompteCourant(maBanque,
        personne, "1234", 1000.);
    assertEquals(compteCourantInDB.getSolde(), 1000.);
  }

  @Test
  public void creerCompteEpargneTest() throws Exception {
    Banque maBanque = banqueService.creerBanque("DGFIP");
    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
    CompteEpargne compteEpargneInDB = banqueService.creerCompteEpargne(maBanque,
        personne, "1234", 1000., 5.);
    assertEquals(compteEpargneInDB.getSolde(), 1000.);
    assertEquals(compteEpargneInDB.getTxInteret(), 5.);
  }

  @Test
  public void creerCarteBancaireTest() throws Exception {
    Banque maBanque = banqueService.creerBanque("DGFIP");
    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
    CompteCourant compteCourant = banqueService.creerCompteCourant(maBanque,
        personne, "1234");
    CarteBancaire carteBancaireInDB = banqueService.creerCarte(maBanque,
        personne, compteCourant);
    assertTrue(carteBancaireInDB.getTitulaire().equals(personne));
    assertTrue(carteBancaireInDB.getBanque().equals(maBanque));
    assertTrue(carteBancaireInDB.getCompteCourant().equals(compteCourant));
  }

  @Test
  public void creerCarteBancaireTest_Duplicate() throws Exception {
    Banque maBanque = banqueService.creerBanque("DGFIP");
    Personne personne = personneService.creerPersonne("Blanchard", "Paulette");
    CompteCourant compteCourant = banqueService.creerCompteCourant(maBanque,
        personne, "1234");
    CarteBancaire carteBancaireInDB1 = banqueService.creerCarte(maBanque,
        personne, compteCourant);
    CarteBancaire carteBancaireInDB2 = banqueService.creerCarte(maBanque,
        personne, compteCourant);
    assertNotEquals(carteBancaireInDB1.getNumCarte(),
        carteBancaireInDB2.getNumCarte());
  }
}
