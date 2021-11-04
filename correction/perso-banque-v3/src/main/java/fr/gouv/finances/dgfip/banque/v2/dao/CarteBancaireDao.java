package fr.gouv.finances.dgfip.banque.v2.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;

public interface CarteBancaireDao extends CrudRepository<CarteBancaire, UUID> {

}
