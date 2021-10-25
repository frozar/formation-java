package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

  private static final Logger log = LoggerFactory
      .getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    System.out.println("Pass main BEFORE");
    ApplicationContext context = SpringApplication
        .run(AccessingDataJpaApplication.class);
    System.out.println("Pass main AFTER");

    RunModern runModern = context.getBean(RunModern.class);
    runModern.execute();
//    RunEntityManager runEntityManager = context.getBean(RunEntityManager.class);
//    runEntityManager.execute();
  }

  // Run at startup of the application
  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return (args) -> {
//      System.out.println("Pass CommandLineRunner");
    };
  }

}
