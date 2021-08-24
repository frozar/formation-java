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
// https://attacomsian.com/blog/spring-data-jpa-many-to-many-mapping

@SpringBootApplication
public class AccessingDataJpaApplication {

  @Autowired
  private CommuneRepository communeRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private RestaurantService restaurantService;

  @Autowired
  private CommuneService communeService;

  private static final Logger log = LoggerFactory
      .getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class);
  }

  // Run at startup of the application
  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      log.info("####### Création de quelques communes et restaurants");
      Commune com0 = new Commune("Saint Joseph");
      Commune com1 = new Commune("Saint Marie");
      Commune com2 = new Commune("Saint Denis");
      Commune com3 = new Commune("Saint Clotilde");
      Commune com4 = new Commune("Saint Louis");
      Restaurant macDo = new Restaurant("MacDo");

      macDo.setCommunes(Set.of(com0, com1, com2, com3));

      restaurantRepository.save(macDo);
      displayDB();

      log.info(
          "####### Création d'un 2ième restaurant qui partage des communes avec le 1er");
//      log.info("tentative 1");
//      log.info(" => le restaurant n'est pas créé, "
//          + "les variables com0 et com2 ayant déjà était utilisées précédemment par un repository, "
//          + "elles sont maintenant dans l'état DETACHED");
//      Restaurant quick = new Restaurant("Quick");
//      quick.setCommunes(Set.of(com0, com2, com4));
//      try {
//        restaurantRepository.save(quick);
//      } catch (Exception e) {
//        log.error(e.getMessage());
//      }
//      displayDB();

      log.info("tentative 2");
      log.info(" => le restaurant est effectivement créé.");
      restaurantService.createRestaurant("Quick",
          Set.of("Saint Joseph", "Saint Denis", "Saint Louis"));
      displayDB();

      log.info("####### Ajouter une commune a un restaurant");
      restaurantService.addCommune("MacDo", "La Possession");
      displayDB();

      log.info("####### Supprimer Saint Louis pour Quick");
      log.info(
          " => La commune de Saint Louis étant en relation uniquement avec 1 restaurant,");
      log.info(
          "    elle sera supprimée dans la foulée : pas d'assistance de JPA");
      log.info("    suppression \"manuelle\" de la commune");
      restaurantService.deleteCommune("Quick", "Saint Louis");
      displayDB();

      log.info("####### Supprimer Saint Joseph pour Quick");
      log.info(
          " => La commune de Saint Louis étant en relation avec 2 restaurants,");
      log.info("    elle ne sera pas supprimée");
      restaurantService.deleteCommune("Quick", "Saint Joseph");
      displayDB();

      log.info("####### Modifier une commune");
      Commune saintDenis = communeRepository.findByNom("Saint Denis").get(0);
      saintDenis.setNom("Saint Pierre");
      communeRepository.save(saintDenis);
      displayDB();

      saintDenis.setNom("Saint Denis");
      communeRepository.save(saintDenis);
      displayDB();

      log.info(
          "####### Modifier le nom d'un restaurant : \"MacDo\" -> \"McDonald's\"");
      Restaurant tmpMacDo = restaurantRepository.findByNom("MacDo").get(0);
      tmpMacDo.setNom("McDonald's");
      restaurantRepository.save(tmpMacDo);
      displayDB();

      log.info("####### Supprimer la commune de Saint Denis");
      communeService.deleteCommune("Saint Denis");
      displayDB();

      log.info("####### Supprimer un restaurant");
      restaurantService.deleteRestaurant("McDonald's");
      displayDB();
    };
  }

  public void displayDB() {
    Iterable<Commune> communes = communeRepository.findAll();
    Iterable<Restaurant> restaurants = restaurantRepository.findAll();
    log.info("Liste des restaurants");
    for (Restaurant r : restaurants) {
      log.info(" - " + r.getNom());
      log.info("Communes du restaurant");
      for (Commune c : r.getCommunes()) {
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
