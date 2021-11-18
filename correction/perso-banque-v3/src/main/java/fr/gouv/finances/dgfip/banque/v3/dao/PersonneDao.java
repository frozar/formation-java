package fr.gouv.finances.dgfip.banque.v3.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import fr.gouv.finances.dgfip.banque.v3.entites.Personne;

public interface PersonneDao extends CrudRepository<Personne, UUID> {

  public Personne findByNomAndPrenom(String nom, String prenom);

}
