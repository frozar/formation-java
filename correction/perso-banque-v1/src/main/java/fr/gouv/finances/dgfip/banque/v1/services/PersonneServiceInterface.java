package fr.gouv.finances.dgfip.banque.v1.services;

import fr.gouv.finances.dgfip.banque.v1.entites.Personne;

public interface PersonneServiceInterface {
  public Personne creerPersonne(String nom, String prenom);
}
