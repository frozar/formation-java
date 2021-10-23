package fr.gouv.finances.dgfip.banque.v1.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v1.entites.Operation;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.PersonneServiceInterface;

@SpringBootTest
public class BanqueServiceTests
{
    @Autowired
    private BanqueServiceInterface banqueService;
    @Autowired
    private PersonneServiceInterface personneService;

    @Test
    void testCreerCompteCourant() throws CompteException {
        Banque banqueTest = new Banque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(banqueTest, paulette, "GUICHET");
        
        //On vérifie que le code banque et le code guichet sont bien initialisés
        assertEquals("TEST",  cc.getCodeBanque());
        assertEquals("GUICHET",  cc.getCodeGuichet());
        
        //On vérifie que le titulaire est bien initialisé
        assertEquals(paulette, cc.getTitulaire());
        
        //On vérifie que la site des comptes de la banque n'est pas vide
        assertTrue(!banqueTest.getComptes().isEmpty());
        //et que l'on récupère bien le nouveau compte lorsque l'on accès au tableau associatif des comptes par le RIB du nouveau compte
        assertEquals(cc, banqueTest.getComptes().get(cc.getRib()));
    }
    
    @Test
    void testCreerCompteCourantAvecSoldeInitial() throws CompteException {
        double soldeInitial = 100.0;
        Banque banqueTest = new Banque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(banqueTest, paulette, "GUICHET", soldeInitial);

        // On vérifie que le solde est égal au solde initial
        assertEquals(soldeInitial, cc.getSolde());
        
        //On vérifie que le compte ne contient qu'une opération
        assertEquals(1,  cc.getOperations().size());
        //et que le montant de cette opération correspond au solde initial
        Operation operationInitial = cc.getOperations().get(0);
        assertEquals(soldeInitial, operationInitial.getMontant());
    }

    @Test
    void testCreerCompteCourantSansTitulaire() {
        Banque banqueTest = new Banque("TEST");
        assertThrows(IllegalArgumentException.class, () -> {banqueService.creerCompteCourant(banqueTest, null, "GUICHET");});
    }

    @Test
    void testCreerCompteEpargne() throws CompteException {
        Banque banqueTest = new Banque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteEpargne cc = banqueService.creerCompteEpargne(banqueTest, paulette, "GUICHET", 100.0, 0.02);
        
        //On vérifie que le code banque et le code guichet sont bien initialisés
        assertEquals("TEST",  cc.getCodeBanque());
        assertEquals("GUICHET",  cc.getCodeGuichet());
        
        //On vérifie que le titulaire est bien initialisé
        assertEquals(paulette, cc.getTitulaire());
        
        //On vérifie que la site des comptes de la banque n'est pas vide
        assertTrue(!banqueTest.getComptes().isEmpty());
        //et que l'on récupère bien le nouveau compte lorsque l'on accède au tableau associatif des comptes par le RIB du nouveau compte
        assertEquals(cc, banqueTest.getComptes().get(cc.getRib()));
    }

    @Test
    void testCreerCompteEpargneSansTitulaire() {
        Banque banqueTest = new Banque("TEST");
        assertThrows(IllegalArgumentException.class, () -> {banqueService.creerCompteEpargne(banqueTest, null, "GUICHET", 100.0, 0.02);});
    }

    @Test
    void testCreerCompteEpargneAvecSoldeInitialNegatif() {
        Banque banqueTest = new Banque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        assertThrows(CompteException.class, () -> {banqueService.creerCompteEpargne(banqueTest, paulette, "GUICHET", -100.0, 0.02);});
    }
    
    @Test
    void testCreerCarte() throws CompteException {
        Banque banqueTest = new Banque("TEST");
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
        Banque banqueTest = new Banque("TEST");
        Banque autreBanque = new Banque("EXTERNE");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(autreBanque, paulette, "GUICHET");  //ATTENTION: Compte créé sur une autre banque
        assertThrows(CompteException.class, () -> {banqueService.creerCarte(banqueTest, paulette, cc);});
        
    }
}
