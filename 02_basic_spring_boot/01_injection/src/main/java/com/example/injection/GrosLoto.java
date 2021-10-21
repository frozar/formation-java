package com.example.injection;

public class GrosLoto extends Voiture {
  /*******************************/
  public GrosLoto() {
    super();
    this.setCarburant(80);
    this.setCarburantCapacite(80);
  }

  /*******************************/
  @Override
  public String toString() {
    return "GrosLoto [carburant=" + super.getCarburant()
        + ", carburantCapacite=" + super.getCarburantCapacite() + ", roue="
        + roue + "]";
  }
}
