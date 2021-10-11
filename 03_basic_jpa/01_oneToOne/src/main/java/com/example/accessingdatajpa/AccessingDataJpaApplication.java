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
  public CommandLineRunner demo(CommuneRepository communeRepository,
      MaireRepository maireRepository) {
    return (args) -> {
      // save a few communes
      Commune saintDenis = new Commune("Saint Denis");
      Maire maireSaintDenis = new Maire("BAREIGTS", "Ericka");
      saintDenis.setMaire(maireSaintDenis);

      Commune saintBenoit = new Commune("Saint Benoit");
      Maire maireSaintBenoit = new Maire("SELLY", "Patrice");
      saintBenoit.setMaire(maireSaintBenoit);

      communeRepository.save(saintDenis);
      communeRepository.save(saintBenoit);

      try {
        Commune saintBenoit2 = new Commune("Saint Benoit 2");
        Maire maireSaintBenoit2 = new Maire("SELLY", "Patrice");
        saintBenoit2.setMaire(maireSaintBenoit2);
        // saintBenoit2.setMaire(maireSaintBenoit);
//        Maire maireSaintBenoit2 = maireRepository.findById(2L);
//        saintBenoit2.setMaire(maireSaintBenoit2);

        communeRepository.save(saintBenoit2);
      } catch (Exception e) {
        log.error("exception: " + e);
      }

      // fetch all customers
      log.info("Communes found with findAll():");
      log.info("-------------------------------");
      for (Commune commune : communeRepository.findAll()) {
        log.info("commune : " + commune);
      }
      log.info("");

      Commune toDeleteCommune = communeRepository.findById(1L);
      communeRepository.delete(toDeleteCommune);

      // fetch all customers
      log.info("Communes after delete:");
      log.info("-------------------------------");
      for (Commune commune : communeRepository.findAll()) {
        log.info("commune : " + commune);
      }
      log.info("Maires after delete:");
      log.info("-------------------------------");
      for (Maire maire : maireRepository.findAll()) {
        log.info("maire : " + maire);
      }
      log.info("");

      Commune commune = communeRepository.findById(2L);
      commune.setNom("Sainte Marie");
      communeRepository.save(commune);

      // fetch all customers
      log.info("Communes after setNom:");
      log.info("-------------------------------");
      for (Commune c : communeRepository.findAll()) {
        log.info("commune : " + c);
      }

      commune.setNom("Saint Benoit");
      communeRepository.save(commune);

      // fetch all customers
      log.info("Communes after fix:");
      log.info("-------------------------------");
      for (Commune c : communeRepository.findAll()) {
        log.info("commune : " + c);
      }
      log.info("");

//      // fetch an individual commune by ID
//      commune = communeRepository.findById(2L);
//      log.info("Commune found with findById(2L):");
//      log.info("--------------------------------");
//      log.info(commune.toString());
//      log.info("");
//
//      // fetch communes by nom
//      log.info("Commune found with findByNom('Saint Benoit'):");
//      log.info("--------------------------------------------");
//      communeRepository.findByNom("Saint Benoit").forEach(c -> {
//        log.info(c.toString());
//      });
//      // for (Commune commune : repository.findByNome("Saint Benoit")) {
//      // log.info(commune.toString());
//      // }
//      log.info("");
    };
  }

}
