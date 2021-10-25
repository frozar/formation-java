package com.example.accessingdatajpa;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunModern {

  private static final Logger log = LoggerFactory.getLogger(RunModern.class);

  @Autowired
  private CustomerRepository repository;

  public void execute() {
    // save a few customers
    repository.save(new Customer("Jack", "Bauer"));
    repository.save(new Customer("Chloe", "O'Brian"));
    repository.save(new Customer("Kim", "Bauer"));
    repository.save(new Customer("David", "Palmer"));
    repository.save(new Customer("Michelle", "Dessler"));

    // fetch all customers
    log.info("Customers found with findAll():");
    log.info("-------------------------------");
    for (Customer customer : repository.findAll()) {
      log.info(customer.toString());
    }
    log.info("");

    // fetch an individual customer by ID
    log.info("Customer found with findById(1L):");
    log.info("--------------------------------");
    Optional<Customer> customerOptinal = repository.findById(1L);
    if (customerOptinal.isPresent()) {
      log.info(customerOptinal.get().toString());
    } else {
      log.info("Customer not Found");
    }
    log.info("");

    // fetch customers by last name
    log.info("Customer found with findByLastName('Bauer'):");
    log.info("--------------------------------------------");
    repository.findByLastName("Bauer").forEach(bauer -> {
      log.info(bauer.toString());
    });
    log.info("");

    log.info(
        "Customer found with findBylastNameAndFirstName('Bauer', 'Jack'):");
    log.info("--------------------------------------------");
    repository.findByLastNameAndFirstName("Bauer", "Jack").forEach(bauer -> {
      log.info(bauer.toString());
    });
//    // for (Customer bauer : repository.findByLastName("Bauer")) {
//    // log.info(bauer.toString());
//    // }
//    log.info("");
  }

}
