package fr.gouv.finances.dgfip.banque.v3.services;

import fr.gouv.finances.dgfip.banque.v3.entites.Personne;

public interface PersonneServiceInterface {
  public Personne creerPersonne(String nom, String prenom);

  public void deleteAllPersonne();
}
