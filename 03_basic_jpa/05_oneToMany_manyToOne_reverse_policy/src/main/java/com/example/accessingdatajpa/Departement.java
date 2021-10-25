package com.example.accessingdatajpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departement {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // unique: each Commune must have a different name in DB
  // length: limit the size of DB field NOM to 60 char
  @Column(unique = true, length = 60)
  private String nom;

  // orphanRemoval : delete commune which doesn't bellow to a departement
//  @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
//      CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
  @OneToMany(mappedBy = "departement", fetch = FetchType.LAZY)
  Set<Commune> communes = new HashSet<Commune>();

  public Departement() {
  }

  public Departement(String nom) {
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

  public Set<Commune> getCommunes() {
    return communes;
  }

  public void setCommunes(Set<Commune> communes) {
    this.communes = communes;
  }

  @Override
  public String toString() {
    return "Departement [id=" + id + ", nom=" + nom + ", communes=" + communes
        + "]";
  }

}
