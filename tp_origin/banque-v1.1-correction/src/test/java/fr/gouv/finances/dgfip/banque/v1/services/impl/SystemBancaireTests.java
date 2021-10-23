package fr.gouv.finances.dgfip.banque.v1.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.PersonneServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.SystemeBancaireInterface;

@SpringBootTest
public class SystemBancaireTests
{
    @Autowired
    private BanqueServiceInterface banqueService;
    @Autowired
    private PersonneServiceInterface personneService;
    @Autowired SystemeBancaireInterface systemeBancaire;
    
    @Test
    void testVerifierCodePin() throws CompteException, SystemeBancaireException {
        Banque banqueTest = new Banque("TEST");
        Personne dominique = personneService.creerPersonne("Guibert", "Dominique");
        CompteCourant ccDominique = banqueService.creerCompteCourant(banqueTest, dominique, "5678");
        CarteBancaire cbDominique = banqueService.creerCarte(banqueTest, dominique, ccDominique);
        assertTrue(systemeBancaire.verifierCodePin(banqueTest, cbDominique.getNumCarte(), cbDominique.getCodePin()));
    }
    
    @Test
    void testVerifierMauvaisCodePin() throws CompteException, SystemeBancaireException {
        Banque banqueTest = new Banque("TEST");
        Personne dominique = personneService.creerPersonne("Guibert", "Dominique");
        CompteCourant ccDominique = banqueService.creerCompteCourant(banqueTest, dominique, "5678");
        CarteBancaire cbDominique = banqueService.creerCarte(banqueTest, dominique, ccDominique);
        assertFalse(systemeBancaire.verifierCodePin(banqueTest, cbDominique.getNumCarte(), "XXXX"));
    }
    
    @Test
    void testVerifierCodePinCarteInconnu()  {
        Banque banqueTest = new Banque("TEST");
        assertThrows(SystemeBancaireException.class, () -> {systemeBancaire.verifierCodePin(banqueTest, "YYYY ZZZZ AAAA BBBB", "XXXX");});
    }
    
    @Test
    void testRechercherRIBCompte() throws CompteException, SystemeBancaireException {
        Banque banqueTest = new Banque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        
        CompteCourant ccPaulette = banqueService.creerCompteCourant(banqueTest, paulette, "1234");
        CarteBancaire cbPaulette = banqueService.creerCarte(banqueTest, paulette, ccPaulette);
        
        //On vérifie que la recherche du compte par le numéro de carte fonctionne dans le cas nominal
        List<String> comptes = systemeBancaire.rechercherRIBCompteCarte(banqueTest, cbPaulette.getNumCarte());
        assertEquals(1, comptes.size());
    }

    @Test
    void testRechercherRIBCompteCarteInconnue() throws CompteException, SystemeBancaireException {
        Banque banqueTest = new Banque("TEST");
        assertThrows(SystemeBancaireException.class, () -> {systemeBancaire.rechercherRIBCompteCarte(banqueTest, "YYYY ZZZZ AAAA BBBB");});
    }
    
    @Test
    void testCreerOperation() throws CompteException, SystemeBancaireException {
        Banque banqueTest = new Banque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        
        CompteCourant ccPaulette = banqueService.creerCompteCourant(banqueTest, paulette, "1234");
        
        assertNotNull(systemeBancaire.creerOperation(banqueTest, ccPaulette.getRib(), "LIBELLE TEST", 100.0));
    }

    void testCreerOperationCompteInconnu() throws CompteException, SystemeBancaireException {
        Banque banqueTest = new Banque("TEST");
        assertThrows(SystemeBancaireException.class, () -> {systemeBancaire.creerOperation(banqueTest, "FRXX XXX....", "LIBELLE TEST", 100.0);});
    }
}
