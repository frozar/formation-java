package com.example.accessingdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunEntityManager {

  private static final Logger log = LoggerFactory
      .getLogger(RunEntityManager.class);

  @Autowired
  private EntityManagerFactory entityManagerFactory;

//  @PersistenceContext
//  private EntityManager entityManager;

//  @Transactional
  @SuppressWarnings("unchecked")
  public void execute() {
    System.out.println("execute");

    // Use below code on create/update
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(new Customer("Jack", "Bauer"));
    entityManager.persist(new Customer("Chloe", "O'Brian"));
    entityManager.persist(new Customer("Kim", "Bauer"));
    entityManager.persist(new Customer("David", "Palmer"));
    entityManager.persist(new Customer("Michelle", "Dessler"));
    entityManager.getTransaction().commit();

    // ############# fetch all customers
    // 0 - Native query
    log.info("fetch all: Customers found with a native query:");
    log.info("-------------------------------");
    List<Customer> nativeQueryCustomers = entityManager
        .createNativeQuery("SELECT * from Customer", Customer.class)
        .getResultList();
    for (Customer customer : nativeQueryCustomers) {
      log.info(customer.toString());
    }
    log.info("");

    // 1 - JPQL
    log.info("fetch all: Customers found with a JPQL query:");
    log.info("-------------------------------");
    List<Customer> jpqlCustomers = entityManager
        .createQuery("SELECT c from Customer c", Customer.class)
        .getResultList();
    for (Customer customer : jpqlCustomers) {
      log.info(customer.toString());
    }
    log.info("");

    // 2 - Named Query
    log.info("fetch all: Customers found with a Named query:");
    log.info("-------------------------------");
    List<Customer> customers = entityManager
        .createNamedQuery("AllCustomers", Customer.class).getResultList();
    for (Customer customer : customers) {
      log.info(customer.toString());
    }
    log.info("");

    // ############# fetch an individual customer by ID
    // 0 - Method find
    log.info(
        "fetch individual: Customer found with entityManager.find(Customer.class, 1L):");
    log.info("--------------------------------");
    Customer oneCustomer = entityManager.find(Customer.class, 1L);
    log.info(oneCustomer.toString());
    log.info("");

    // 1 - Native query
    log.info("fetch individual: Customer found with a native query:");
    log.info("-------------------------------");
    Customer nativeQueryOneCustomer = (Customer) entityManager
        .createNativeQuery("SELECT * FROM CUSTOMER WHERE id = 1",
            Customer.class)
        .getSingleResult();
    log.info(nativeQueryOneCustomer.toString());
    log.info("");

    // 2 - JPQL
    // hardcoded query
    log.info("fetch individual: Customer found with a JPQL query:");
    log.info("-------------------------------");
    Customer jpqlOneCustomer = entityManager
        .createQuery("SELECT c from Customer c Where c.id = 1", Customer.class)
        .getSingleResult();
    log.info(jpqlOneCustomer.toString());
    log.info("");

    // indexed parameter query
    log.info(
        "fetch individual: Customer found with a JPQL query indexed parameter:");
    log.info("-------------------------------");
    String jpqlIndexedParameterQuery = "SELECT c from Customer c where c.id = ?2";
    TypedQuery<Customer> indexedParameterQuery = entityManager
        .createQuery(jpqlIndexedParameterQuery, Customer.class);
    indexedParameterQuery.setParameter(2, 1L);
    Customer jpqlOptionIndexedParameterOneCustomer = indexedParameterQuery
        .getSingleResult();
    log.info(jpqlOptionIndexedParameterOneCustomer.toString());
    log.info("");

    // named parameter query
    log.info(
        "fetch individual: Customer found with a JPQL query named parameter:");
    log.info("-------------------------------");
    String jpqlNamedParameterQuery = "SELECT c from Customer c where c.id = :id";
    TypedQuery<Customer> namedParameterQuery = entityManager
        .createQuery(jpqlNamedParameterQuery, Customer.class);
    namedParameterQuery.setParameter("id", 1L);
    Customer jpqlNamedParameterOneCustomer = namedParameterQuery
        .getSingleResult();
    log.info(jpqlNamedParameterOneCustomer.toString());
    log.info("");

    // 3 - Named Query
    log.info("fetch individual: Customer found with a Named query:");
    log.info("-------------------------------");
    TypedQuery<Customer> parametrisedQuery = entityManager
        .createNamedQuery("Customer.byId", Customer.class);
    parametrisedQuery.setParameter("id", 1L);
    Customer namedParametrisedOneCustomer = parametrisedQuery.getSingleResult();
    log.info(namedParametrisedOneCustomer.toString());
    log.info("");

    // ############# fetch customer by last name
    // 0 - Native query
    log.info(
        "fetch last name: Customer found with native query and where clause:");
    log.info("--------------------------------------------");
    List<Customer> nativeQueryCustomersFound = entityManager
        .createNativeQuery("SELECT * from Customer where LAST_NAME = 'Bauer'",
            Customer.class)
        .getResultList();
    for (Customer customer : nativeQueryCustomersFound) {
      log.info(customer.toString());
    }
    log.info("");

    // 1 - JPQL
    log.info(
        "fetch last name: Customer found with JPQL query and where clause:");
    log.info("--------------------------------------------");
    List<Customer> jpqlQueryCustomersFound = entityManager
        .createQuery("SELECT c from Customer c where c.lastName = 'Bauer'",
            Customer.class)
        .getResultList();
    for (Customer customer : jpqlQueryCustomersFound) {
      log.info(customer.toString());
    }
    log.info("");

    // 2 - Named Query
    log.info(
        "fetch last name: Customer found with named query and where clause:");
    log.info("-------------------------------");
    List<Customer> namedQueryCustomersFound = entityManager
        .createNamedQuery("Customer.Bauer", Customer.class).getResultList();
    for (Customer customer : namedQueryCustomersFound) {
      log.info(customer.toString());
    }
    log.info("");
  }

}
