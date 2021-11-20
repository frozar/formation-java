package fr.gouv.finances.dgfip.banque.v4.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import fr.gouv.finances.dgfip.banque.v4.entites.CompteBancaire;

public interface CompteBancaireDao
    extends CrudRepository<CompteBancaire, UUID> {

  CompteBancaire findByNumCompte(String numCompte);

}
