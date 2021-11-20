package fr.gouv.finances.dgfip.banque.v4.services;

import java.util.List;
import java.util.Set;

import fr.gouv.finances.dgfip.banque.v4.CompteException;
import fr.gouv.finances.dgfip.banque.v4.entites.Banque;
import fr.gouv.finances.dgfip.banque.v4.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v4.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v4.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v4.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v4.entites.Personne;

public interface BanqueServiceInterface {

  public List<Banque> listeBanque();

  public Banque creerBanque(String nomBanque);

  public Banque getBanque(String nomBanque);

  public Banque getBanqueWithAllField(String nomBanque);

  public Set<CompteBancaire> getSetCompteBancaire(String nomBanque);

  public Set<CompteBancaire> getSetCompteBancaireManyRequest(String nomBanque);

  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial) throws CompteException;

  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet) throws CompteException;

  public CompteEpargne creerCompteEpargne(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial, Double taux) throws CompteException;

  public CompteBancaire getCompteBancaire(String numCompte);

  public CompteBancaire updateCompteBancaire(String numCompte,
      Personne titulaire);

  public Banque deleteCompteBancaire(String numCompte);

  public CarteBancaire creerCarte(Banque banque, Personne titulaire,
      CompteCourant compte) throws CompteException;

  public void afficherSyntheseComptes(Banque banque);

  public void afficherSyntheseCartes(Banque banque);

  public void deleteAllBanque();

  public void deleteAllCompteBancaire();

  public void deleteAllCartebancaire();

}
