package fr.gouv.finances.dgfip.banque.v3.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import fr.gouv.finances.dgfip.banque.v3.entites.CompteBancaire;

public interface CompteBancaireDao
    extends CrudRepository<CompteBancaire, UUID> {

  CompteBancaire findByNumCompte(String numCompte);

}
