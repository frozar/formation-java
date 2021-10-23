package fr.gouv.finances.dgfip.banque.v2.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;

public interface CompteBancaireDao extends CrudRepository<CompteBancaire, UUID>
{
    Iterable<CompteBancaire> findByBanque(Banque banque);
}
