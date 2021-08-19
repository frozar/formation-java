package com.example.accessingdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantServiceImpl implements RestaurantService {

  @Autowired
  private CommuneRepository communeRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Override
  @Transactional
  public Restaurant createRestaurant(String restaurantNom,
      Set<String> communesNom) {
    List<Restaurant> researchRestaurant = restaurantRepository
        .findByNom(restaurantNom);
    Restaurant restau;
    if (researchRestaurant.isEmpty()) {
      restau = new Restaurant(restaurantNom);
    } else {
      restau = researchRestaurant.get(0);
    }

    Set<Commune> coms = new HashSet<Commune>();
    for (String communeNom : communesNom) {
      List<Commune> researchCommune = communeRepository.findByNom(communeNom);
      if (researchCommune.isEmpty()) {
        coms.add(new Commune(communeNom));
      } else {
        coms.add(researchCommune.get(0));
        // Existing commune
        Commune existingCommune = researchCommune.get(0);
        existingCommune.getRestaurants().add(restau);
        communeRepository.save(existingCommune);
      }
    }

    restau.setCommunes(coms);
    restaurantRepository.save(restau);

    return restau;
  }

  @Override
  @Transactional
  public Restaurant addCommune(String restaurantNom, String communeNom) {
    List<Restaurant> researchRestaurant = restaurantRepository
        .findByNom(restaurantNom);
    if (researchRestaurant.isEmpty()) {
      return null;
    }

    Restaurant restaurant = researchRestaurant.get(0);

    List<Commune> researchCommune = communeRepository.findByNom(communeNom);
    Commune commune = researchCommune.isEmpty() ? new Commune(communeNom)
        : researchCommune.get(0);

    commune.getRestaurants().add(restaurant);
    communeRepository.save(commune);

    restaurant.getCommunes().add(commune);
    restaurantRepository.save(restaurant);

    return restaurant;
  }

  private void deleteCommeIfIsOrphan(String communeNom) {
    // Check if a commune argument is orphan
    Boolean isOrphan = true;
    for (Restaurant r : restaurantRepository.findAll()) {
      for (Commune c : r.getCommunes()) {
        isOrphan &= !c.getNom().equals(communeNom);
      }
      if (isOrphan) {
        break;
      }
    }

    // Remove a commune if it's an orphan commune
    if (isOrphan) {
      communeRepository.deleteByNom(communeNom);
    }
  }

  @Override
  @Transactional
  public Restaurant deleteCommune(String restaurantNom, String communeNom) {
    // If restaurantNom doesn't match with any restaurant in DB, exit
    List<Restaurant> researchRestaurant = restaurantRepository
        .findByNom(restaurantNom);
    if (researchRestaurant.isEmpty()) {
      return null;
    }

    Restaurant restaurant = researchRestaurant.get(0);

    // If communeNom doesn't match with any commune in DB, exit
    if (communeRepository.findByNom(communeNom).isEmpty()) {
      return restaurant;
    }

    Boolean removePerformed = restaurant.getCommunes()
        .removeIf(c -> c.getNom().equals(communeNom));

    // If no remove has been performed, exit
    if (!removePerformed) {
      return restaurant;
    }

    restaurantRepository.save(restaurant);

//    // Check if a commune argument is orphan
//    Boolean isOrphan = true;
//    for (Restaurant r : restaurantRepository.findAll()) {
//      for (Commune c : r.getCommunes()) {
//        isOrphan &= !c.getNom().equals(communeNom);
//      }
//      if (isOrphan) {
//        break;
//      }
//    }
//
//    // Remove a commune if it's an orphan commune
//    if (isOrphan) {
//      communeRepository.deleteByNom(communeNom);
//    }

    deleteCommeIfIsOrphan(communeNom);

    return restaurant;
  }

  @Override
  @Transactional
  public Boolean deleteRestaurant(String restaurantNom) {
    // If restaurantNom doesn't match with any restaurant in DB, exit
    List<Restaurant> researchRestaurant = restaurantRepository
        .findByNom(restaurantNom);
    if (researchRestaurant.isEmpty()) {
      return false;
    }

    Restaurant restaurant = researchRestaurant.get(0);
    restaurantRepository.deleteById(restaurant.getId());

    for (Commune c : restaurant.getCommunes()) {
      deleteCommeIfIsOrphan(c.getNom());
    }

    return true;
  }

}
