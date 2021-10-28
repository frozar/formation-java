package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

  private static final Logger log = LoggerFactory
      .getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class);
  }

  // Run at startup of the application
  @Bean
  public CommandLineRunner demo(UserRepository userRepository,
      EmployeeRepository employeeRepository) {
    return (args) -> {

      User ericka = new User("Ericka");
      userRepository.save(ericka);

      Employee e = new Employee("Antoine", 10);
      // userRepository.save(e);
      employeeRepository.save(e);

    };
  }

}
