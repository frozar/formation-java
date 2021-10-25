package com.example.accessingdatajpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Maire {

  // Pour éviter la colision des noms de colonnes entre les entités Commune et
  // Maire, les noms de colonnes de Maire sont ajustés avec l'annotation @Column
  @Column(name = "maire_nom", nullable = false)
  private String nom;
  @Column(name = "maire_prenom", nullable = false)
  private String prenom;

  public Maire() {
  }

  public Maire(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
  }

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  @Override
  public String toString() {
    return "Maire [nom=" + nom + ", prenom=" + prenom + "]";
  }

}
