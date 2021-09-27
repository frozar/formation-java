package fr.gouv.finances.dgfip.banque.v1.services.impl;

import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.BanqueServiceInterface;

@Component
public class BanqueService implements BanqueServiceInterface {
  
  @Override
  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial) throws CompteException {
    return banque.creerCompteCourant(titulaire, guichet, soldeInitial);
  }

  @Override
  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet) throws CompteException {
    return banque.creerCompteCourant(titulaire, guichet, 0.0);
  }

  @Override
  public CompteEpargne creerCompteEpargne(Banque banque, Personne titulaire,
      String guichet, Double soldeInitial, Double taux) throws CompteException {
    return banque.creerCompteEpargne(titulaire, guichet, soldeInitial, taux);
  }

  @Override
  public CarteBancaire creerCarte(Banque banque, Personne titulaire,
      CompteCourant compte) throws CompteException {
    return banque.creerCarte(titulaire, compte);
  }

  @Override
  public void afficherSyntheseComptes(Banque banque) {
    banque.afficherSyntheseComptes();
  }

  @Override
  public void afficherSyntheseCartes(Banque banque) {
    banque.afficherSyntheseCartes();
  }
  
  public CarteBancaire lierCarte(Banque banque, CarteBancaire carte, CompteBancaire compte) {
    return banque.lierCarte(carte, compte);
  }

}
