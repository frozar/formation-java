package fr.gouv.finances.dgfip.banque.v2.services.impl;

import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v2.entites.Personne;
import fr.gouv.finances.dgfip.banque.v2.services.PersonneServiceInterface;

@Service
public class PersonneService implements PersonneServiceInterface {

  @Override
  public Personne creerPersonne(String nom, String prenom) {
    return new Personne(nom, prenom);
  }

}
