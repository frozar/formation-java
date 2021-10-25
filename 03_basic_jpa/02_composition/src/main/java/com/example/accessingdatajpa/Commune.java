package com.example.accessingdatajpa;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commune {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;

  // Relation de composition entre Commune et Maire :
  // Si une commune est supprimé, son maire l'est aussi. L'entité Maire
  // se retrouve dans la même table que Commune.
  // @Column(nullable = false)
  @Embedded
  private Maire maire;

  protected Commune() {
  }

  public Commune(String nom) {
    this.nom = nom;
  }

  public Long getId() {
    return id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Maire getMaire() {
    return maire;
  }

  public void setMaire(Maire maire) {
    this.maire = maire;
  }

  @Override
  public String toString() {
    return "Commune [id=" + id + ", nom=" + nom + ", maire=" + maire.getNom()
        + " " + maire.getPrenom() + "]";
  }

}
