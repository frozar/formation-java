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
import fr.gouv.finances.dgfip.banque.v2.dao.OperationDao;
import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Operation;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;
import fr.gouv.finances.dgfip.banque.v2.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v2.services.CompteBancaireServiceInterface;
import fr.gouv.finances.dgfip.banque.v2.services.PersonneServiceInterface;

@SpringBootTest(classes=fr.gouv.finances.dgfip.banque.v2.BanqueV2Application.class)
public class CompteBancaireServiceTests
{
    @Autowired
    private BanqueServiceInterface banqueService;
    @Autowired
    private PersonneServiceInterface personneService;
    @Autowired
    private CompteBancaireServiceInterface compteBancaireService;
    @Autowired
    private OperationDao operationDao;

    @Test
    public void testCreerOperation() throws CompteException {
        Banque banqueTest = banqueService.creerBanque("TEST");
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CompteCourant cc = banqueService.creerCompteCourant(banqueTest, paulette, "GUICHET");
        int nbOpeAvant = operationsCompte(cc).size();
        Operation newOpe = compteBancaireService.creerOperation(cc, "D??p??t ch??que", 100.0);
        
        // On v??rifie le montant de l'op??ration et le libell??
        assertEquals(100.0,  newOpe.getMontant());
        assertEquals("D??p??t ch??que",  newOpe.getLibelle());
        
        //On v??rifie que le compte contient 1 op??ration de plus que l'op??ration renvoy??e par le service est bien dans cette liste
        List<Operation> operations = operationsCompte(cc);
        assertEquals(nbOpeAvant + 1, operations.size());
    }
    
    /**
     * Test de retrait sur un compte d'??pargne avec un montant sup??rieur au solde du compte
     *
     * @throws CompteException
     */
    public void testCreerOperationCompteEpargneSansSolde() throws CompteException {
        Banque banqueTest = banqueService.creerBanque("TEST");
        Personne thibault = personneService.creerPersonne("Guillou", "Thibault");
        CompteEpargne ceThibault = banqueService.creerCompteEpargne(banqueTest, thibault, "1234", 3000.0, 0.02);
        compteBancaireService.creerOperation(ceThibault, "D??p??t", 1000.0);
        assertThrows(CompteException.class, () -> {compteBancaireService.creerOperation(ceThibault, "Retrait", -10000.0);});
    }
    
    private List<Operation> operationsCompte(CompteBancaire cc) {
        Iterable<Operation> opeIterator = operationDao.findByCompte(cc);
        return StreamSupport.stream(opeIterator.spliterator(), false).collect(Collectors.toList());
    }
    
}
