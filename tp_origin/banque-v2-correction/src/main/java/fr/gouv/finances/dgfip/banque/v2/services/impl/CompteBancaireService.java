package fr.gouv.finances.dgfip.banque.v2.services.impl;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v2.CompteException;
import fr.gouv.finances.dgfip.banque.v2.dao.CompteBancaireDao;
import fr.gouv.finances.dgfip.banque.v2.dao.OperationDao;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Operation;
import fr.gouv.finances.dgfip.banque.v2.services.CompteBancaireServiceInterface;

@Component
public class CompteBancaireService implements CompteBancaireServiceInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CompteBancaireService.class);
    private OperationDao operationDao;
    private CompteBancaireDao compteBancaireDao;

    @Transactional
    public Operation creerOperation(CompteBancaire compte, String libelle, Double montant) throws CompteException {
        if(compte instanceof CompteEpargne && compte.getSolde() + montant < 0)
            throw new CompteException(String.format("Solde (%.2f) insuffisant pour une opération de %.2f", compte.getSolde(), montant));
        Operation ope = new Operation(compte.getNumOperation(), libelle, montant);
        ope.setCompte(compte);
        operationDao.save(ope);
        compte.setNumOperation(compte.getNumOperation() + 1);
        compte.setSolde(this.calculerSolde(compte));
        compteBancaireDao.save(compte);
        return ope;
    }
    
    private double calculerSolde(CompteBancaire compte) {
        return StreamSupport
            .stream(operationDao.findByCompte(compte).spliterator(), false)
            .map(e -> e.getMontant())
            .reduce(0.0, Double::sum).doubleValue();
        /*
         double s = 0;
         for(Operation ope: operationDao.findByCompteBancaire(compte)) {
            s += ope.getMontant();
         }
         return s;
         */
    }
    
    public double calculerInteret(CompteEpargne compte)
    {
        return compte.getTxInteret() * compte.getSolde();
    }
    
    public void afficherSyntheseOperations(CompteBancaire compte) {
        String leftAlignFormat = "| %7d | %-23s | %-23s | %10.2f |";

        LOGGER.info(String.format("Synthèse du compte: %s", compte.getRib()));
        LOGGER.info(String.format("Titulaire: %s", compte.getTitulaire()));
        LOGGER.info(String.format("+---------+-------------------------+-------------------------+------------+"));
        LOGGER.info(String.format("| Num opé | Date opération          | Libellé                 | Montant    |"));
        LOGGER.info(String.format("+---------+-------------------------+-------------------------+------------+"));
        for (Operation ope : operationDao.findByCompte(compte)) {
            LOGGER.info(String.format(leftAlignFormat, ope.getNumOperation(), ope.getDateOperation().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), ope.getLibelle(), ope.getMontant()));
        }
        LOGGER.info(String.format("+---------+-------------------------+-------------------------+------------+"));
        LOGGER.info(String.format("Solde: %10.2f",  compte.getSolde()));
    }

    @Autowired
    public void setOperationDao(OperationDao operationDao)
    {
        this.operationDao = operationDao;
    }

    @Autowired
    public void setCompteBancaireDao(CompteBancaireDao compteBancaireDao)
    {
        this.compteBancaireDao = compteBancaireDao;
    }

}
