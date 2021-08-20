package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "commune", path = "commune")
public interface CommuneRepository extends CrudRepository<Commune, Long> {
  List<Commune> findByNom(String nom);
}
