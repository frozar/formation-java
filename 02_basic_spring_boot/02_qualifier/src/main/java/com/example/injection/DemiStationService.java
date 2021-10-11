package com.example.injection;

import org.springframework.stereotype.Component;

@Component("demi")
public class DemiStationService implements StationServiceInterface {
  public void faireLePlein(Voiture v) {
    v.setCaburant(Voiture.carburantCapacite / 2);
  }
}
