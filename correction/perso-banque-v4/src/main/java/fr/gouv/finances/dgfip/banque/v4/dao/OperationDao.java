package fr.gouv.finances.dgfip.banque.v4.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import fr.gouv.finances.dgfip.banque.v4.entites.Operation;

public interface OperationDao extends CrudRepository<Operation, UUID> {

}
