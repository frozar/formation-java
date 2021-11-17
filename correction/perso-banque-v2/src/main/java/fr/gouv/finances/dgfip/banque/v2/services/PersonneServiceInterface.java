package fr.gouv.finances.dgfip.banque.v2.services;

import fr.gouv.finances.dgfip.banque.v2.entites.Personne;

public interface PersonneServiceInterface {
  public Personne creerPersonne(String nom, String prenom);

  public void deleteAllPersonne();
}
