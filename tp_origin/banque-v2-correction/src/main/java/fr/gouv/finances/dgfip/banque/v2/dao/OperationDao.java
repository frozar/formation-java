package fr.gouv.finances.dgfip.banque.v2.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.Operation;

public interface OperationDao  extends CrudRepository<Operation, UUID>
{
    // Spring interprète le nom de la méthode pour générer la requête
    Iterable<Operation> findByCompte(CompteBancaire compte);
}
