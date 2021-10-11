package com.example;

public class Dog extends AbstractAnimal
    implements SpeedInterface, HeightInterface {
  @Override
  public void sound() {
    System.out.println("Wouf");
  }

  public Double speed() {
    return 72.;
  }

  public Double height() {
    return 62.5;
  }
}
