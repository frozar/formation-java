package fr.gouv.finances.dgfip.banque.v2.services;

import java.util.List;

import fr.gouv.finances.dgfip.banque.v2.CompteException;
import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;

public interface BanqueServiceInterface {

  public List<Banque> listeBanque();

  public Banque creerBanque(String nomBanque);

  public Banque getBanque(String nomBanque);

  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial) throws CompteException;

  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet) throws CompteException;

  public CompteEpargne creerCompteEpargne(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial, Double taux) throws CompteException;

  public CompteBancaire getCompteBancaire(String numCompte);

  public CompteBancaire updateCompteBancaire(String numCompte,
      Personne titulaire);

  public void deleteCompteBancaire(String numCompte);

  public CarteBancaire creerCarte(Banque banque, Personne titulaire,
      CompteCourant compte) throws CompteException;

  public void afficherSyntheseComptes(Banque banque);

  public void afficherSyntheseCartes(Banque banque);

  public void deleteAllBanque();

  public void deleteAllCompteBancaire();

  public void deleteAllCartebancaire();

}
