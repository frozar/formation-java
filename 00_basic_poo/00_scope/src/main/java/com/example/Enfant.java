package com.example;

public class Enfant extends Parent {
  @Override
  public void firstName() {
    System.out.println("Public: My first name is: " + this.firstName);
  }

  @Override
  protected void lastName() {
    System.out.println("Protected: My last name is: " + this.lastName);
  }

  // As Parent.secret field is private, it cannot be used in Enfant class
//  private void discoverSecret() {
//    System.out.println("My secret is: " + this.secret);
//  }

  // As Parent.secret() method is private, it cannot be overrided or
  // used in Enfant class
//  @Override
//  public void secret() {
//    System.out.println("Enfant secret");
//  }

  // As Parent.unoverridable() method is final, it cannot be overrided
  // in Enfant class
//  @Override
//  final void unoverridable() {
//    System.out.println("I'm the Enfant");
//  }

  // As Parent.age() method is static, it cannot be overrided in Enfant class
//  @Override
//  public static void age() {
//    System.out.println("2 years old");
//  }
}
