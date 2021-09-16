package com.example;

public class Application {
  public static void main(String[] args) {
    Rectangle r = new Rectangle(3., 10.);
    System.out.println(r);
    System.out.println(r.computePerimeter());
    System.out.println(r.computeArea());

    Carre c = new Carre(5.);
    System.out.println(c);
    System.out.println(c.computePerimeter());
    System.out.println(c.computeArea());
  }
}