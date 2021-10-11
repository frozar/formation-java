package com.example;

public class Rectangle {
  final private Double height;
  final private Double width;

  public Rectangle(Double height, Double width) {
    this.height = height;
    this.width = width;
  }

  public Double computePerimeter() {
    return (this.height + this.width) * 2;
  }

  public Double computeArea() {
    return this.height * this.width;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " [height=" + height + ", width="
        + width + "]";
  }
}