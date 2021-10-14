package com.example.injection;

import org.springframework.stereotype.Component;

@Component
public class Transport {
  private String nom;

  public Transport(String nom) {
    this.nom = nom;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }
}
