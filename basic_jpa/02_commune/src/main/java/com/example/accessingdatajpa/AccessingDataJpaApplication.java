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
  public CommandLineRunner demo(CommuneRepository communeRepository) {
    return (args) -> {
      // save a few customers
      Commune saintDenis = new Commune("Saint Denis");
      Maire maireSaintDenis = new Maire("BAREIGTS", "Ericka");
      saintDenis.setMaire(maireSaintDenis);

      Commune saintBenoit = new Commune("Saint Benoit");
      Maire maireSaintBenoit = new Maire("SELLY", "Patrice");
      saintBenoit.setMaire(maireSaintBenoit);

      communeRepository.save(saintDenis);
      communeRepository.save(saintBenoit);

      // fetch all customers
      log.info("Communes found with findAll():");
      log.info("-------------------------------");
      for (Commune commune : communeRepository.findAll()) {
        log.info("commune : " + commune);
      }
      log.info("");

      communeRepository.deleteById(1L);

      // fetch all customers
      log.info("Communes after delete:");
      log.info("-------------------------------");
      for (Commune commune : communeRepository.findAll()) {
        log.info("commune : " + commune);
      }
      log.info("");

//      // Impossible d'enregistré une commune se elle n'a pas de Maire associé
//      // => conséquence de l'annotation @Column(nullable = false)
//      Commune plaineDesPalmistes = new Commune("La Plaine des Palmistes");
//      communeRepository.save(plaineDesPalmistes);
//
//      // fetch all customers
//      log.info("nullable = false:");
//      log.info("-------------------------------");
//      for (Commune commune : communeRepository.findAll()) {
//        log.info("commune : " + commune);
//      }
//      log.info("");

    };
  }

}
