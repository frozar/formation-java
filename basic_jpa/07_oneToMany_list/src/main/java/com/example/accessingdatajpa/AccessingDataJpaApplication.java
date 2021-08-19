package com.example.accessingdatajpa;

import java.util.List;

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
  private DepartementService departementService;

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
      Commune plaineDesPalmistes = new Commune("La Plaine des Palmistes");
      Commune saintBenoit = new Commune("Saint Benoit");
      Commune brasPanon = new Commune("Bras Panon");
      Commune saintAndre = new Commune("Saint André");
      Commune sainteSuzanne = new Commune("Sainte Suzanne");
      Commune sainteMarie = new Commune("Sainte Marie");
      Commune saintDenis = new Commune("Saint Denis");
      Departement reunion = new Departement("Réunion");
      List<Commune> communes = reunion.getCommunes();
      communes.add(plaineDesPalmistes);
      communes.add(saintBenoit);
      communes.add(brasPanon);
      communes.add(saintAndre);
      communes.add(sainteSuzanne);
      communes.add(sainteMarie);
      communes.add(saintDenis);
      departementRepository.save(reunion);
      displayDB();

      log.info("####### 0 Afficher un département provenant de la DB");
      Departement run0 = departementRepository.findByNom("Réunion");
      for (Commune c : run0.getCommunes()) {
        log.info(c.toString());
      }
      log.info("");

      log.info(
          "####### Ajouter une commune à un département en première position");
      departementService.addCommuneHead("Réunion", "Saint Paul");
      displayDB();

      log.info("####### 1 Afficher un département provenant de la DB");
      Departement run1 = departementRepository.findByNom("Réunion");
      for (Commune c : run1.getCommunes()) {
        log.info(c.toString());
      }
      log.info("");
    };
  }

  public void displayDB() {
    Iterable<Commune> communes = communeRepository.findAll();
    Iterable<Departement> departements = departementRepository.findAll();
    log.info("Liste des departements");
    for (Departement d : departements) {
      log.info(" - " + d.getNom());
      log.info("Communes du departement");
      for (Commune c : d.getCommunes()) {
        log.info("   * " + c);
      }
    }
    log.info("Liste des communes");
    for (Commune c : communes) {
      log.info(" - " + c.toString());
    }
    log.info("");
  }
}
