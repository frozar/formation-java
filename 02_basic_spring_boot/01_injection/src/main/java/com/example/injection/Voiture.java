package com.example.injection;

public class Voiture {
  /*******************************/
  private int carburantCapacite = 60;
  private int carburant = carburantCapacite;
  static final int capaciteRoue = 4;
  protected int roue = capaciteRoue;

  /*******************************/
  public int getCarburant() {
    return carburant;
  }

  public void setCarburant(int carburant) {
    this.carburant = carburant;
  }

  public int getCarburantCapacite() {
    return carburantCapacite;
  }

  public void setCarburantCapacite(int carburantCapacite) {
    this.carburantCapacite = carburantCapacite;
  }

  /*******************************/
  public void roule(int distance) throws Exception {
//    System.out.println("Voiture roule");
//    System.out.println("carburant: " + carburant);
//    System.out.println("distance: " + distance);
    if ((carburant - distance) < 0) {
      throw new Exception("Pas assez d'essence");
    }
    if ((roue - 1) < 0) {
      throw new Exception("Roues usées");
    }
    carburant -= distance;
    roue -= 1;
  }

//  public void faireLePlein() {
//    this.carburant = this.carburantCapacite;
//  }

//  @Override
//  public String toString() {
//    return "Voiture [caburant=" + carburant + "]";
//  }

  @Override
  public String toString() {
    return "Voiture [carburantCapacite=" + carburantCapacite + ", carburant="
        + carburant + ", roue=" + roue + "]";
  }

}
