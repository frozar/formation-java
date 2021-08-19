package com.example.accessingdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Documentation link:
// https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html

@RepositoryRestResource(collectionResourceRel = "departement", path = "departement")
public interface DepartementRepository
    extends CrudRepository<Departement, Long> {
  Departement findByNom(String nom);
}
