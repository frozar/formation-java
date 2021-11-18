package fr.gouv.finances.dgfip.banque.v3.services;

import fr.gouv.finances.dgfip.banque.v3.CompteException;
import fr.gouv.finances.dgfip.banque.v3.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v3.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v3.entites.Operation;

public interface CompteBancaireServiceInterface {

  public Operation creerOperation(CompteBancaire compte, String libelle,
      Double montant) throws CompteException;

  public void afficherSyntheseOperations(CompteBancaire compte);

  public double calculerInteret(CompteEpargne compte);
}
