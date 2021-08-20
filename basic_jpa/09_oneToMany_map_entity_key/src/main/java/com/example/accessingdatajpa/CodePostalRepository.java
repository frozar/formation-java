package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "codePostal", path = "codePostal")
public interface CodePostalRepository extends CrudRepository<CodePostal, Long> {
  List<CodePostal> findByNumero(Integer codePostal);
}
