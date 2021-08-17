package com.example.accessingdatajpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

  @Column(unique = true)
  private String nom;

  // L'annotation "@OneToOne" permet de créer la relation entre Commune et
  // Maire.
  // L'argument "cascade" de "@OneToOne" décrit le comportement pour un champ de
  // type bean lors d'une écriture
  // CascadeType.PERSIST : écrit le Maire associé lors d'un save()
  // CascadeType.REMOVE : supprime le Maire associé lors d'un delete()
  @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
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
