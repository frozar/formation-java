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
  private CodePostalRepository codePostalRepository;

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

      Map<CodePostal, Commune> communes = reunion.getCommunes();

      CodePostal plaineDesPalmistesCode = new CodePostal(97431);
      CodePostal saintBenoitCode = new CodePostal(97437);
      CodePostal brasPanonCode = new CodePostal(97412);
      CodePostal saintAndreCode = new CodePostal(97440);
      CodePostal sainteSuzanneCode = new CodePostal(97441);
      CodePostal sainteMarieCode = new CodePostal(97438);
      CodePostal saintDenisCode = new CodePostal(97400);

      communes.put(plaineDesPalmistesCode, plaineDesPalmistes);
      communes.put(saintBenoitCode, saintBenoit);
      communes.put(brasPanonCode, brasPanon);
      communes.put(saintAndreCode, saintAndre);
      communes.put(sainteSuzanneCode, sainteSuzanne);
      communes.put(sainteMarieCode, sainteMarie);
      communes.put(saintDenisCode, saintDenis);

      codePostalRepository.save(plaineDesPalmistesCode);
      codePostalRepository.save(saintBenoitCode);
      codePostalRepository.save(brasPanonCode);
      codePostalRepository.save(saintAndreCode);
      codePostalRepository.save(sainteSuzanneCode);
      codePostalRepository.save(sainteMarieCode);
      codePostalRepository.save(saintDenisCode);

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
