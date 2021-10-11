package com.example.accessingdatajpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Commune {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // unique: each Commune must have a different name in DB
  // length: limit the size of DB field NOM to 60 char
  @Column(unique = true, length = 60)
  private String nom;

  // L'annotation "@ManyToMany" permet de créer la relation entre Commune et
  // Departement.
  // mappedBy : partie esclave de la relation ManyToMany
  @ManyToMany(mappedBy = "communes", fetch = FetchType.EAGER)
  Set<Restaurant> restaurants = new HashSet<Restaurant>();

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

  public Set<Restaurant> getRestaurants() {
    return restaurants;
  }

  public void setRestaurants(Set<Restaurant> restaurants) {
    this.restaurants = restaurants;
  }

  @Override
  public String toString() {
    return "Commune [id=" + id + ", nom=" + nom + ", restaurants=" + restaurants
        + "]";
  }

}
