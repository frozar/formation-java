package com.example.app;

import org.springframework.stereotype.Component;

@Component
public class Personne {
  private String nom;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
  public String toString() {
    return "Personne [nom=" + nom + "]";
  }
}
