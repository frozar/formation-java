package fr.gouv.finances.dgfip.banque.v1.services;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;

public interface BanqueServiceInterface {

  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial) throws CompteException;

  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet) throws CompteException;
  
  public void afficherSyntheseComptes(Banque banque);
}
