package com.example;

public class Application {
  public static void main(String[] args) {
    int height = Integer.parseInt(args[0]);

    for (int i = 0; i < height; ++i) {
      int lineWidth = height * 2 - 1;
      int nbCross = lineWidth - 2 * i;
      int nbSpace = i;
      for (int j = 0; j < nbSpace; ++j) {
        System.out.print(" ");
      }
      for (int j = 0; j < nbCross; ++j) {
        System.out.print("*");
      }
      for (int j = 0; j < nbSpace; ++j) {
        System.out.print(" ");
      }
      System.out.println("");
    }
  }
}
