package com.example;

public class Parent {
  public String mood = "bonne";

  final public String firstName = "Paulette";
  final protected String lastName = "Blanchard";
  final private String secret = "My secret";

  final static protected Double age = 20.0;

  public void firstName() {
    System.out.println("Public method: " + this.firstName);
  }

  protected void lastName() {
    System.out.println("Protected method: " + this.lastName);
  }

  private void secret() {
    System.out.println(this.secret);
  }

  final void unoverridable() {
    System.out.println("I'm the Parent");
  }

  // Static method can use only static member
  public static void age() {
    System.out.println(Parent.age + " years old");
  }
}
