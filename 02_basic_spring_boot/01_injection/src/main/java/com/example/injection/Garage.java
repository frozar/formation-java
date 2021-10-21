package com.example.injection;

import org.springframework.stereotype.Service;

@Service("roue")
public class Garage implements GarageInterface {

  @Override
  public void entretien(Voiture v) {
    v.roue = v.capaciteRoue;
  }

}
