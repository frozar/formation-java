package fr.gouv.finances.dgfip.banque.v1.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.gouv.finances.dgfip.banque.v2.CompteException;
import fr.gouv.finances.dgfip.banque.v2.dao.CompteBancaireDao;
import fr.gouv.finances.dgfip.banque.v2.dao.OperationDao;
import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Operation;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;
import fr.gouv.finances.dgfip.banque.v2.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v2.services.PersonneServiceInterface;

@SpringBootTest(classes=fr.gouv.finances.dgfip.banque.v2.BanqueV2Application.class)
public class BanqueServiceTests
{
    @Autowired
    private BanqueServiceInterface banqueService;
    @Autowired
    private PersonneServiceInterface personneService;
    @Autowired
    private OperationDao operationDao;
    @Autowired
    private CompteBancaireDao compteBancaireDao;

    @Test
    void testCreerCompteCourant() throws CompteException {
        Banque banqueTest = banqueService.creerBanque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(banqueTest, paulette, "GUICHET");
        
        //On vérifie que le code banque et le code guichet sont bien initialisés
        assertEquals("TEST",  cc.getCodeBanque());
        assertEquals("GUICHET",  cc.getCodeGuichet());
        
        //On vérifie que le titulaire est bien initialisé
        assertEquals(paulette, cc.getTitulaire());
        
        //On vérifie que la site des comptes de la banque n'est pas vide
        Iterable<CompteBancaire> comptesIterator = compteBancaireDao.findByBanque(banqueTest);
        List<CompteBancaire> comptes = StreamSupport.stream(comptesIterator.spliterator(), false).collect(Collectors.toList());
        assertTrue(!comptes.isEmpty());
    }
    
    @Test
    void testCreerCompteCourantAvecSoldeInitial() throws CompteException {
        double soldeInitial = 100.0;
        Banque banqueTest = banqueService.creerBanque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(banqueTest, paulette, "GUICHET", soldeInitial);

        // On vérifie que le solde est égal au solde initial
        assertEquals(soldeInitial, cc.getSolde());
        
        //On vérifie que le compte ne contient qu'une opération
        Iterable<Operation> opeIterator = operationDao.findByCompte(cc);
        List<Operation> operations = StreamSupport.stream(opeIterator.spliterator(), false).collect(Collectors.toList());
        assertEquals(1,  operations.size());
        //et que le montant de cette opération correspond au solde initial
        Operation operationInitial = operations.get(0);
        assertEquals(soldeInitial, operationInitial.getMontant());
    }

    @Test
    void testCreerCompteCourantSansTitulaire() {
        Banque banqueTest = banqueService.creerBanque("TEST");
        assertThrows(IllegalArgumentException.class, () -> {banqueService.creerCompteCourant(banqueTest, null, "GUICHET");});
    }

    @Test
    void testCreerCompteEpargne() throws CompteException {
        Banque banqueTest = banqueService.creerBanque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteEpargne cc = banqueService.creerCompteEpargne(banqueTest, paulette, "GUICHET", 100.0, 0.02);
        
        //On vérifie que le code banque et le code guichet sont bien initialisés
        assertEquals("TEST",  cc.getCodeBanque());
        assertEquals("GUICHET",  cc.getCodeGuichet());
        
        //On vérifie que le titulaire est bien initialisé
        assertEquals(paulette, cc.getTitulaire());
        
        //On vérifie que la site des comptes de la banque n'est pas vide
        Iterable<CompteBancaire> comptesIterator = compteBancaireDao.findByBanque(banqueTest);
        List<CompteBancaire> comptes = StreamSupport.stream(comptesIterator.spliterator(), false).collect(Collectors.toList());
        assertTrue(!comptes.isEmpty());
    }

    @Test
    void testCreerCompteEpargneSansTitulaire() {
        Banque banqueTest = banqueService.creerBanque("TEST");
        assertThrows(IllegalArgumentException.class, () -> {banqueService.creerCompteEpargne(banqueTest, null, "GUICHET", 100.0, 0.02);});
    }

    @Test
    void testCreerCompteEpargneAvecSoldeInitialNegatif() {
        Banque banqueTest = banqueService.creerBanque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        assertThrows(CompteException.class, () -> {banqueService.creerCompteEpargne(banqueTest, paulette, "GUICHET", -100.0, 0.02);});
    }
    
    @Test
    void testCreerCarte() throws CompteException {
        Banque banqueTest = banqueService.creerBanque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(banqueTest, paulette, "GUICHET");
        CarteBancaire cb = banqueService.creerCarte(banqueTest, paulette, cc);
        
        // On vérifier l'association avec le titulaire
        assertEquals(paulette, cb.getTitulaire());
        
        //On vérifie que la site des comptes associés à la carte n'est pas vide
        assertTrue(!cb.getComptes().isEmpty());
        //et que le compte courant est bien associé à la liste des comptes de la carte
        assertTrue(cb.getComptes().contains(cc));
    }
    
    @Test
    void testCreerCarteCompteInconnu() throws CompteException{
        Banque banqueTest = banqueService.creerBanque("TEST");
        Banque autreBanque = banqueService.creerBanque("EXTERNE");

        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(autreBanque, paulette, "GUICHET");  //ATTENTION: Compte créé sur une autre banque
        assertThrows(CompteException.class, () -> {banqueService.creerCarte(banqueTest, paulette, cc);});
        
    }
}
