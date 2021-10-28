package com.example.accessingdatajpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commune {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // unique: each Commune must have a different name in DB
  // length: limit the size of DB field NOM to 60 char
  @Column(unique = true, length = 60)
  private String nom;

  // L'annotation "@ManyToOne" permet de créer la relation entre Commune et
  // Departement.
  // interprétation de @ManyToOne : plusieurs Commune appartiennent à un
  // département
//  @ManyToOne(fetch = FetchType.EAGER)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DEPARTEMENT_ID", nullable = false, updatable = false)
  private Departement departement;

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

  public Departement getDepartement() {
    return departement;
  }

  public void setDepartement(Departement departement) {
    this.departement = departement;
  }

  @Override
  public String toString() {
    return "Commune [id=" + id + ", nom=" + nom + ", departement=" + departement
        + "]";
  }

}
