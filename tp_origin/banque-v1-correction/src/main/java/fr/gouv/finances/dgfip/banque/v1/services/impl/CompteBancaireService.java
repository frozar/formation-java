package fr.gouv.finances.dgfip.banque.v1.services.impl;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v1.entites.Operation;
import fr.gouv.finances.dgfip.banque.v1.services.CompteBancaireServiceInterface;

@Component
public class CompteBancaireService implements CompteBancaireServiceInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CompteBancaireService.class);

    public Operation creerOperation(CompteBancaire compte, String libelle, Double montant) throws CompteException {
        if(compte instanceof CompteEpargne && compte.getSolde() + montant < 0)
            throw new CompteException(String.format("Solde (%.2f) insuffisant pour une opération de %.2f", compte.getSolde(), montant));
        Operation ope = new Operation(compte.getNumOperation(), libelle, montant);
        compte.ajouterOperation(ope);
        compte.setSolde(this.calculerSolde(compte));
        return ope;
    }
    
    private double calculerSolde(CompteBancaire compte) {
        return compte.getOperations().stream().map(e -> e.getMontant()).reduce(0.0, Double::sum).doubleValue();
        /*
         double s = 0;
         for(Operation ope: compte.getOperations()) {
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
        for (Operation ope : compte.getOperations()) {
            LOGGER.info(String.format(leftAlignFormat, ope.getNumOperation(), ope.getDateOperation().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), ope.getLibelle(), ope.getMontant()));
        }
        LOGGER.info(String.format("+---------+-------------------------+-------------------------+------------+"));
        LOGGER.info(String.format("Solde: %10.2f",  compte.getSolde()));
    }

}
