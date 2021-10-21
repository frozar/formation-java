package fr.gouv.finances.dgfip.banque.v2.services;

import fr.gouv.finances.dgfip.banque.v2.CompteException;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Operation;

public interface CompteBancaireServiceInterface {

  public Operation creerOperation(CompteBancaire compte, String libelle,
      Double montant) throws CompteException;
  
  public void afficherSyntheseOperations(CompteBancaire compte);
  
  public double calculerInteret(CompteEpargne compte);

}
