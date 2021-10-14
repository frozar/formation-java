package com.example.injection;

import org.springframework.stereotype.Service;

@Service("complete")
public class StationService implements StationServiceInterface {
  public void faireLePlein(Voiture v) {
    v.setCaburant(Voiture.carburantCapacite);
  }
}
