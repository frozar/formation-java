package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "maire", path = "maire")
public interface MaireRepository extends CrudRepository<Maire, Long> {

  List<Maire> findByNom(String nom);

  Maire findById(long id);
}
