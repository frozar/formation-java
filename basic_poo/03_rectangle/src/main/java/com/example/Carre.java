package com.example;

public class Carre extends Rectangle {
  public Carre(Double sideLength) {
    super(sideLength, sideLength);
  }

  @Override
  public String toString() {
    return "Carre " + super.toString();
  }
}