package com.example.accessingdatajpa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Commune {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;

  // L'annotation "@OneToOne" permet de créer la relation entre Commune et
  // Maire.
  // L'argument "cascade" de "@OneToOne" décrit le comportement pour un champ de
  // type bean lors d'une écriture
  @OneToOne(cascade = CascadeType.PERSIST)
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
