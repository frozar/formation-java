package com.example.accessingdatajpa;

import java.util.Set;

public interface RestaurantService {
  public Restaurant createRestaurant(String restaurantNom,
      Set<String> communesNom);

  public Restaurant addCommune(String restaurantNom, String communeNom);

  public Restaurant deleteCommune(String restaurantNom, String communeNom);

  public Boolean deleteRestaurant(String restaurantNom);
}
