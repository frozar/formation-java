package fr.gouv.finances.dgfip.banque.v1.services;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;

public interface BanqueServiceInterface {

  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial) throws CompteException;

  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet) throws CompteException;

  public CompteEpargne creerCompteEpargne(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial, Double taux) throws CompteException;

  public CarteBancaire creerCarte(Banque banque, Personne titulaire,
      CompteCourant compte) throws CompteException;

  public void afficherSyntheseComptes(Banque banque);

  public void afficherSyntheseCartes(Banque banque);

}
