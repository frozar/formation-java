package com.example.accessingdatajpa;

import java.util.Set;

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
      Commune saintDenis = new Commune("Saint Denis");
      Commune saintBenoit = new Commune("Saint Benoit");
      Departement reunion = new Departement("Réunion");

      saintDenis.setDepartement(reunion);
      saintBenoit.setDepartement(reunion);
      Set<Commune> communes = reunion.getCommunes();
      communes.add(saintDenis);
      communes.add(saintBenoit);
      departementRepository.save(reunion);
      displayDB();

      log.info(reunion.toString());

//      log.info("####### Ajouter une commune");
//      log.info("tentative 1");
//      log.info(" => n'est pas ajouter au département, "
//          + "génére une commune avec un id null");
//      Commune sainteRose = new Commune("Sainte Rose");
//      sainteRose.setDepartement(reunion);
//      log.info(sainteRose.toString());
//      communes.add(sainteRose);
//      try {
//        departementRepository.save(reunion);
//      } catch (Exception e) {
//        log.error(e.getMessage());
//      }
      // displayDB();

//      log.info("tentative 2");
//      log.info(" => n'est pas ajouter au département, "
//          + "mais présent dans la table des communes.");
//      communeRepository.save(sainteRose);
//      departementRepository.save(reunion);
//      displayDB();
//      communeRepository.delete(sainteRose);
//      displayDB();

      log.info("tentative 3");
      log.info(" => ajout correctement la commune 'Sainte Rose'");
      // Récupération du département
      Departement run = departementRepository.findById(1L).get();
      Set<Commune> runCommunes = run.getCommunes();

      // Création de la commune à ajouter
      Commune sainteRose = new Commune("Sainte Rose");
      sainteRose.setDepartement(run);

//      // Enregistrement de la commune en DB
//      communeRepository.save(sainteRose);
//      log.info(sainteRose.toString());

      // Association de la commune au département et enregistrement en DB
      runCommunes.add(sainteRose);
      departementRepository.save(run);
      displayDB();

//      log.info("tentative 4");
//      run = departementService.addCommune("Réunion", "Saint André");
//      displayDB();
//
//      log.info("####### Supprimer une commune");
//      log.info("tentative 1");
//      run = departementService.deleteCommune("Réunion", "Saint André");
//      displayDB();
//
//      log.info("####### Modifier une commune");
//      log.info("tentative 1");
//      Commune c = communeRepository.findById(2L).get();
//      c.setNom("Bras Panon");
//      communeRepository.save(c);
//      displayDB();
//
//      log.info("####### Supprimer un département");
//      log.info("tentative 1");
//      departementRepository.delete(run);
//      displayDB();
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
