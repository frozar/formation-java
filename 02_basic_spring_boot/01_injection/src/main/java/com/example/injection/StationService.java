package com.example.injection;

import org.springframework.stereotype.Service;

@Service
public class StationService implements StationServiceInterface {
//  public String msg = "my MSG";

  public void faireLePlein(Voiture v) {
    System.out.println("faireleplein v " + v);
    v.setCarburant(v.getCarburantCapacite());
//    v.faireLePlein();
  }
}
