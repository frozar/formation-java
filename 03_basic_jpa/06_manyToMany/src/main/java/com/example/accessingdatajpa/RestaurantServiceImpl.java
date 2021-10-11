package com.example.accessingdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

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

    communeRepository.save(commune);

    restaurant.getCommunes().add(commune);
    restaurantRepository.save(restaurant);

    return restaurant;
  }

  @Override
  public Restaurant deleteCommune(String restaurantNom, String communeNom) {
    // Split the delete operation of a commune (slave side of relation ship) in
    // 2 transactions.
    Restaurant restaurant = deleteCommuneInRestaurant(restaurantNom,
        communeNom);

    if (restaurant != null) {
      deleteOrphanCommune(communeNom);
    }

    return restaurant;
  }

  @Transactional
  private Restaurant deleteCommuneInRestaurant(String restaurantNom,
      String communeNom) {
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
    return restaurant;
  }

  @Transactional(value = TxType.REQUIRES_NEW)
  private void deleteOrphanCommune(String communeNom) {
    List<Commune> researchCommune = communeRepository.findByNom(communeNom);
    if (!researchCommune.isEmpty()) {
      Commune commune = researchCommune.get(0);
      if (commune.getRestaurants().isEmpty()) {
        communeRepository.delete(commune);
      }
    }
  }

  @Override
  public Boolean deleteRestaurant(String restaurantNom) {
    // If restaurantNom doesn't match with any restaurant in DB, exit
    List<Restaurant> researchRestaurant = restaurantRepository
        .findByNom(restaurantNom);

    if (researchRestaurant.isEmpty()) {
      return false;
    }

    Restaurant restaurant = researchRestaurant.get(0);

    deleteRestaurantOnly(restaurant);

    deleteRestaurantOrphanCommune(restaurant);

    return true;
  }

  @Transactional
  private void deleteRestaurantOnly(Restaurant restaurant) {
    restaurantRepository.delete(restaurant);
  }

  @Transactional(TxType.REQUIRES_NEW)
  private void deleteRestaurantOrphanCommune(Restaurant restaurant) {
    for (Commune c : restaurant.getCommunes()) {
      deleteOrphanCommune(c.getNom());
    }
  }
}
