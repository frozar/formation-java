package com.example.accessingdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "departement", path = "departement")
public interface DepartementRepository
    extends CrudRepository<Departement, Long> {
}
