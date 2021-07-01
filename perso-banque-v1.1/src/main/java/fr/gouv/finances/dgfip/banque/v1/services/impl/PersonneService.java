package fr.gouv.finances.dgfip.banque.v1.services.impl;

import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.PersonneServiceInterface;

@Component
public class PersonneService implements PersonneServiceInterface {

  @Override
  public Personne creerPersonne(String nom, String prenom) {
    return new Personne(nom, prenom);
  }

}
