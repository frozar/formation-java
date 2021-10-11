package com.example.injection;

import org.springframework.stereotype.Component;

@Component
public class Article {
  private String nom;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }
}
