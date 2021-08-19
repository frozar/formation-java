package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "restaurant", path = "restaurant")
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
  List<Restaurant> findByNom(String nom);
}
