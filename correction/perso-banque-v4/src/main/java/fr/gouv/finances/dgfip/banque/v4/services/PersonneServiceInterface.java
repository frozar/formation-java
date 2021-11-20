package fr.gouv.finances.dgfip.banque.v4.services;

import fr.gouv.finances.dgfip.banque.v4.entites.Personne;

public interface PersonneServiceInterface {
  public Personne creerPersonne(String nom, String prenom);

  public void deleteAllPersonne();
}
