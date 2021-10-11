package fr.gouv.finances.dgfip.banque.v1.services.impl;

import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.PersonneServiceInterface;

@Service
public class PersonneService implements PersonneServiceInterface {

  @Override
  public Personne creerPersonne(String nom, String prenom) {
    return new Personne(nom, prenom);
  }

}
