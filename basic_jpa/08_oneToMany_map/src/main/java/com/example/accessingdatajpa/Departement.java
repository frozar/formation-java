package com.example.accessingdatajpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
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
  // @MapKeyColumn : give the name of the column which will store the key
  // value of the map
  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
  @MapKeyColumn(name = "codePostal")
  Map<Integer, Commune> communes = new HashMap<Integer, Commune>();

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

  public Map<Integer, Commune> getCommunes() {
    return communes;
  }

  public void setCommunes(Map<Integer, Commune> communes) {
    this.communes = communes;
  }

  @Override
  public String toString() {
    return "Departement [id=" + id + ", nom=" + nom + ", communes=" + communes
        + "]";
  }

}
