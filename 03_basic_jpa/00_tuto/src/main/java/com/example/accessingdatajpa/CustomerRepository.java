package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);

  List<Customer> findByLastNameAndFirstName(String lastName, String firstName);

  // TODO: uncomment to generate request by firstname
  // List<Customer> findByFirstName(String firstName);

//  Customer findById(long id);
}
