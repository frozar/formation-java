package fr.gouv.finances.dgfip.banque.v1.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v1.entites.Operation;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.CompteBancaireServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.PersonneServiceInterface;

@SpringBootTest
public class CompteBancaireServiceTests
{
    @Autowired
    private BanqueServiceInterface banqueService;
    @Autowired
    private PersonneServiceInterface personneService;
    @Autowired
    private CompteBancaireServiceInterface compteBancaireService;

    @Test
    public void testCreerOperation() throws CompteException {
        Banque banqueTest = new Banque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(banqueTest, paulette, "GUICHET");
        int nbOpeAvant = cc.getOperations().size();
        Operation newOpe = compteBancaireService.creerOperation(cc, "Dépôt chèque", 100.0);
        
        // On vérifie le montant de l'opération et le libellé
        assertEquals(100.0,  newOpe.getMontant());
        assertEquals("Dépôt chèque",  newOpe.getLibelle());
        
        //On vérifie que le compte contient 1 opération de plus que l'opération renvoyée par le service est bien dans cette liste
        assertEquals(nbOpeAvant + 1, cc.getOperations().size());
        assertTrue(cc.getOperations().contains(newOpe));
    }
    
    /**
     * Test de retrait sur un compte d'épargne avec un montant supérieur au solde du compte
     *
     * @throws CompteException
     */
    public void testCreerOperationCompteEpargneSansSolde() throws CompteException {
        Banque banqueTest = new Banque("TEST");
        Personne thibault = personneService.creerPersonne("Guillou", "Thibault");
        CompteEpargne ceThibault = banqueService.creerCompteEpargne(banqueTest, thibault, "1234", 3000.0, 0.02);
        compteBancaireService.creerOperation(ceThibault, "Dépôt", 1000.0);
        assertThrows(CompteException.class, () -> {compteBancaireService.creerOperation(ceThibault, "Retrait", -10000.0);});
    }
}
