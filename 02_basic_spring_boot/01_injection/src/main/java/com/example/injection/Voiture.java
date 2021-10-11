package com.example.injection;

public class Voiture {
  /*******************************/
  static int carburantCapacite = 60;
  int caburant = carburantCapacite;

  /*******************************/
  public int getCaburant() {
    return caburant;
  }

  public void setCaburant(int caburant) {
    this.caburant = caburant;
  }

  /*******************************/
  public void roule(int distance) throws Exception {
    if ((caburant - distance) < 0) {
      throw new Exception("Pas assez d'essence");
    }
    caburant -= distance;
  }

  @Override
  public String toString() {
    return "Voiture [caburant=" + caburant + "]";
  }
}
