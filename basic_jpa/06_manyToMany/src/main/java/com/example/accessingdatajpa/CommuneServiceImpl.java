package com.example.accessingdatajpa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommuneServiceImpl implements CommuneService {

  @Autowired
  private CommuneRepository communeRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Override
  @Transactional
  public Boolean deleteCommune(String communeNom) {
    // If communeNom doesn't match with any commune in DB, exit
    if (communeRepository.findByNom(communeNom).isEmpty()) {
      return false;
    }

    // Remove commune in each restaurant in DB
    for (Restaurant r : restaurantRepository.findAll()) {
      r.getCommunes().removeIf(c -> c.getNom().equals(communeNom));
    }

    // Remove commune from Commune Table
    communeRepository.deleteByNom(communeNom);

    // Remove restaurant if no associated commune
    for (Restaurant r : restaurantRepository.findAll()) {
      if (r.getCommunes().isEmpty()) {
        restaurantRepository.delete(r);
      }
    }

    return true;
  }

}
