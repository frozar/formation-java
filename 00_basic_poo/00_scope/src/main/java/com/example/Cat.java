package com.example;

public class Cat extends AbstractAnimal
    implements SpeedInterface, HeightInterface {
  @Override
  public void sound() {
    System.out.println("Miaou");
  }

  public Double speed() {
    return 48.;
  }

  public Double height() {
    return 24.;
  }
}
