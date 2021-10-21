package com.example.injection;

import org.springframework.stereotype.Service;

@Service("complete")
public class GarageComplete implements GarageInterface {

  @Override
  public void entretien(Voiture v) {
    v.roue = v.capaciteRoue;
    v.setCarburant(v.getCarburantCapacite());
  }

}
