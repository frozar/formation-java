package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

  @Autowired
  private CommuneRepository communeRepository;

  @Autowired
  private DepartementRepository departementRepository;

  @Autowired
  private DisplayService displayService;

  private static final Logger log = LoggerFactory
      .getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class);
  }

  // Run at startup of the application
  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      log.info("####### Création d'1 département et d'1 commune");
      Commune saintDenis = new Commune("Saint Denis");
      Departement reunion = new Departement("Réunion");

      saintDenis.setDepartement(reunion);
      departementRepository.save(reunion);
      communeRepository.save(saintDenis);

      Departement mayotte = new Departement("Mayotte");
      departementRepository.save(mayotte);

      Commune communeMayotte = new Commune("Commune Mayotte");
      communeMayotte.setDepartement(mayotte);
      communeRepository.save(communeMayotte);

      displayService.displayDB();

      // Création d'un contexte d'exécution pour que la variable "run"
      // ne se propage pas dans tout le scope de cette fonction.
      {
        log.info("####### Ajouter une commune");
        Departement run = departementRepository.findById(1L).get();
        Commune saintBenoit = new Commune("Saint Benoit");
        saintBenoit.setDepartement(run);
        communeRepository.save(saintBenoit);
      }

      displayService.displayDB();

//      log.info("####### Supprimer une commune");
//      communeRepository.deleteById(3L);
//      displayService.displayDB();
//
//      log.info("####### Modifier une commune");
//      Commune c = communeRepository.findById(2L).get();
//      c.setNom("Bras Panon");
//      communeRepository.save(c);
//      displayService.displayDB();
//
//      log.info("####### Supprimer la commune et le département");
//      communeRepository.deleteById(2L);
//      departementRepository.deleteById(1L);
//      displayService.displayDB();
    };
  }
}
