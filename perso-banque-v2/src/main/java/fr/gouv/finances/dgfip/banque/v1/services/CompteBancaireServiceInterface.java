package fr.gouv.finances.dgfip.banque.v1.services;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;

public interface CompteBancaireServiceInterface {
  
  public void afficherSyntheseOperations(CompteBancaire compte);

}
