package fr.gouv.finances.dgfip.banque.v4.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import fr.gouv.finances.dgfip.banque.v4.Gabier;
import fr.gouv.finances.dgfip.banque.v4.entites.Banque;
import fr.gouv.finances.dgfip.banque.v4.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v4.entites.Personne;
import fr.gouv.finances.dgfip.banque.v4.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v4.services.CompteBancaireServiceInterface;
import fr.gouv.finances.dgfip.banque.v4.services.PersonneServiceInterface;

@SpringBootTest
public class BanqueServiceWithContextFilesTest {

  @Autowired
  private BanqueServiceInterface banqueService;

  @Autowired
  private PersonneServiceInterface personneService;

  @Autowired
  CompteBancaireServiceInterface compteBancaireService;

  @Autowired
  Gabier gabier;

//  @Autowired
//  private BanqueDao banqueDao;

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  // @BeforeAll
//  public static void beforeAll() {
//    System.err.println("Before All");
//  }
//
//  @AfterAll
//  public static void afterAll() {
//    System.err.println("After All");
//  }

//  @BeforeAll
//  public void initTest() throws SQLException {
//    Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
//        .start();
//  }

//  @BeforeEach
//  public void populate() throws Exception {
//    Banque maBanque = banqueService.creerBanque("DGFIP");
//    Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
//    Personne dominique = personneService.creerPersonne("Guibert", "Dominique");
//    Personne thibault = personneService.creerPersonne("Guillou", "Thibault");
//    Personne andre = personneService.creerPersonne("Labbe", "Andr??");
//
//    CompteCourant ccPaulette = banqueService.creerCompteCourant(maBanque,
//        paulette, "1234");
//    compteBancaireService.creerOperation(ccPaulette, "D??p??t ch??que", 100.0);
//
//    CompteCourant ccDominique = banqueService.creerCompteCourant(maBanque,
//        dominique, "1235");
//    compteBancaireService.creerOperation(ccDominique, "D??p??t esp??ces", 200.0);
//
//    compteBancaireService.creerOperation(ccPaulette, "D??bit", -50.0);
//    compteBancaireService.creerOperation(ccDominique, "D??bit", -300.0);
//
//    CompteEpargne ceThibault = banqueService.creerCompteEpargne(maBanque,
//        thibault, "5677", 3000.0, 0.02);
//    compteBancaireService.creerOperation(ceThibault, "D??p??t", 1000.0);
//    try {
//      compteBancaireService.creerOperation(ceThibault, "Retrait", -10000.0);
//    } catch (CompteException ce) {
//    }
//
//    banqueService.creerCompteEpargne(maBanque, andre, "5678", 10000.0, 0.02);
//
//    CarteBancaire cbPaulette = banqueService.creerCarte(maBanque, paulette,
//        ccPaulette);
//    CarteBancaire cbDominique = banqueService.creerCarte(maBanque, dominique,
//        ccDominique);
//
//    try {
//      String ribComptePaulette = gabier.accesComptes(maBanque,
//          cbPaulette.getNumCarte(), cbPaulette.getCodePin());
//      gabier.retirerEspeces(maBanque, ribComptePaulette, 314.0);
//    } catch (SystemeBancaireException e) {
//    }
//
//    try {
//      String ribCompteDominique = gabier.accesComptes(maBanque,
//          cbDominique.getNumCarte(), "CODE PIN FAUX");
//      gabier.retirerEspeces(maBanque, ribCompteDominique, 567.0);
//    } catch (SystemeBancaireException e) {
//    }
//  }

//  @AfterEach
////  @BeforeEach
//  public void cleanUpEach() {
//    banqueService.deleteAllCartebancaire();
//    banqueService.deleteAllCompteBancaire();
//    personneService.deleteAllPersonne();
//    banqueService.deleteAllBanque();
//  }

  @Test
  @Sql(scripts = { "/import-data.sql" })
  public void checkContextTest() throws Exception {
    Banque maBanque = banqueService.getBanqueWithAllField("DGFIP");
    Set<CompteBancaire> setCompteBancaire = maBanque.getSetCompteBancaire();
    assertEquals(setCompteBancaire.size(), 4);
    assertEquals(maBanque.getAdherents().size(), 4);
    assertEquals(maBanque.getSetCarte().size(), 2);
    assertTrue(
        setCompteBancaire.stream().map((CompteBancaire c) -> c.getNumCompte())
            .collect(Collectors.toSet()).contains("0000000003"));
  }

  @Test
  @Sql(scripts = { "/import-data.sql" })
  public void creerBanqueTest() throws Exception {
    Banque banqueInDB = banqueService.creerBanque("AnotherBanque");
    assertTrue(banqueInDB.getCodeBanque().equals("AnotherBanque"));
    assertEquals(banqueService.listeBanque().size(), 2);
  }

  @Test
  @Sql(scripts = { "/import-data.sql" })
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
  @Sql(scripts = { "/import-data.sql" })
  public void getCompteBancaireTest() throws Exception {
    CompteBancaire compteBancaire = banqueService
        .getCompteBancaire("0000000000");
    assertEquals(compteBancaire.getTitulaire().getNom(), "Blanchard");
    assertEquals(compteBancaire.getTitulaire().getPrenom(), "Paulette");
  }

  @Test
  @Sql(scripts = { "/import-data.sql" })
  public void updateCompteBancaireTest() throws Exception {
    CompteBancaire compteBancaire = banqueService
        .updateCompteBancaire("0000000000", new Personne("Delon", "Alain"));
    assertTrue(compteBancaire.getTitulaire().getNom().equals("Delon"));
    assertTrue(compteBancaire.getTitulaire().getPrenom().equals("Alain"));

    Banque maBanque = banqueService.getBanqueWithAllField("DGFIP");
    assertEquals(maBanque.getSetCompteBancaire().size(), 4);
    assertEquals(maBanque.getSetCarte().size(), 2);
  }

  // This is not transactional.
  // The delete call for the compteBancaire "0000000000" happens in a
  // dedicate transaction.
  // The check of the number of account in the banque happens in another
  // dedicated transaction.
  // You can also use in this class an entity manager to create a query
  // in its own transaction and configure the request to check the number
  // of accounts in the banque.
  @Test
  @Sql(scripts = { "/import-data.sql" })
  public void deleteCompteBancaire() throws Exception {
    banqueService.deleteCompteBancaire("0000000000");

    Set<CompteBancaire> setCompteBancaire = banqueService
        .getSetCompteBancaire("DGFIP");

    System.err.println(
        "banqueService.getSetCompteBancaire(\"DGFIP\"): " + setCompteBancaire);
    assertEquals(setCompteBancaire.size(), 3);
    assertFalse(
        setCompteBancaire.stream().map((CompteBancaire c) -> c.getNumCompte())
            .collect(Collectors.toSet()).contains("0000000000"));

    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    // Here the "FETCH" keyword means to Hibernate to fetch the field
    // "setCompteBancaire" data as is doing this query, as this field
    // is configured as a FETCH=Lazy by default.
    TypedQuery<Banque> q = em.createQuery(
        "SELECT b FROM Banque b LEFT JOIN FETCH b.setCompteBancaire WHERE b.codeBanque = 'DGFIP'",
        Banque.class);
    Banque banInDB = q.getSingleResult();

    System.err.println(
        "banInDB.getSetCompteBancaire(): " + banInDB.getSetCompteBancaire());
    em.getTransaction().commit();
    em.close();
  }

}
