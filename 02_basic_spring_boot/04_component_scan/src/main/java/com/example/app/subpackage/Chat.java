package com.example.app.subpackage;

import org.springframework.stereotype.Component;

@Component
public class Chat {
  private String nom;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
  public String toString() {
    return "Chat [nom=" + nom + "]";
  }
}
