package fr.gouv.finances.dgfip.banque.v1.services.impl;

import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
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
  public void afficherSyntheseComptes(Banque banque) {
    banque.afficherSyntheseComptes();
  }
}
