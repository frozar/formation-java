package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// Documentation link:
// https://attacomsian.com/blog/spring-data-jpa-one-to-many-mapping

@SpringBootApplication
public class AccessingDataJpaApplication {

  @Autowired
  private CommuneRepository communeRepository;

  @Autowired
  private DepartementRepository departementRepository;

  @Autowired
  private FetchServiceInterface fetchService;

  private static final Logger log = LoggerFactory
      .getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class);
  }

  // Run at startup of the application
  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      log.info("####### Création de quelques communes");
      Departement reunion = new Departement("Réunion");
      departementRepository.save(reunion);

      // save a few communes
      Commune saintDenis = new Commune("Saint Denis");
      saintDenis.setDepartement(reunion);
      communeRepository.save(saintDenis);

      Departement run = departementRepository.findById(1L).get();
      Commune saintBenoit = new Commune("Saint Benoit");
      saintBenoit.setDepartement(run);
      communeRepository.save(saintBenoit);
      fetchService.displayDB();

      try {
        departementRepository.delete(run);
      } catch (Exception e) {
        log.error(e.getMessage());
      }

//      log.info("####### Supprimer les communes et département");
//      communeRepository.delete(saintDenis);
//      communeRepository.delete(saintBenoit);
//      departementRepository.delete(run);
////      displayDB();
//      fetchService.displayDB();
//      fetchService.fetchBehavior();
    };
  }

  public void displayDB() {
    Iterable<Commune> communes = communeRepository.findAll();
    Iterable<Departement> departements = departementRepository.findAll();
    log.info("Liste des departements");
    for (Departement d : departements) {
      log.info(" - " + d);
    }
    log.info("Liste des communes");
    for (Commune c : communes) {
      log.info(" - " + c.toString());
    }
    log.info("");
  }
}
