package com.externe;

import org.springframework.stereotype.Component;

@Component
public class Other {
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
