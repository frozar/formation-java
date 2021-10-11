package com.example.accessingdatajpa;

import java.util.Map;

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
      Map<Integer, Commune> communes = reunion.getCommunes();
      communes.put(97431, plaineDesPalmistes);
      communes.put(97437, saintBenoit);
      communes.put(97412, brasPanon);
      communes.put(97440, saintAndre);
      communes.put(97441, sainteSuzanne);
      communes.put(97438, sainteMarie);
      communes.put(97400, saintDenis);
      departementRepository.save(reunion);
      displayDB();

      log.info("####### 0 Afficher un département provenant de la DB");
      Departement run0 = departementRepository.findByNom("Réunion");
      run0.getCommunes().forEach((codePostal, commune) -> {
        log.info(
            "code postal : " + codePostal + ", commune : " + commune.getNom());
      });
      log.info("");

      log.info(
          "####### Ajouter une commune à un département en première position");
      departementService.addCommune("Réunion", "Saint Paul", 97460);
      displayDB();

      log.info("####### 1 Afficher un département provenant de la DB");
      Departement run1 = departementRepository.findByNom("Réunion");
      run1.getCommunes().forEach((codePostal, commune) -> {
        log.info(
            "code postal : " + codePostal + ", commune : " + commune.getNom());
      });
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
      d.getCommunes().forEach((codePostal, commune) -> {
        log.info(
            "code postal : " + codePostal + ", commune : " + commune.getNom());
      });
    }
    log.info("Liste des communes");
    for (Commune c : communes) {
      log.info(" - " + c.toString());
    }
    log.info("");
  }
}
