package com.example.accessingdatajpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

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
  // @OrderColumn : add an order column in DB
  // Documentation link:
  // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/order-column.html
  // https://www.objectdb.com/api/java/jpa/OrderColumn
  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
  @OrderColumn
  List<Commune> communes = new ArrayList<Commune>();

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

  public List<Commune> getCommunes() {
    return communes;
  }

  public void setCommunes(List<Commune> communes) {
    this.communes = communes;
  }

  @Override
  public String toString() {
    return "Departement [id=" + id + ", nom=" + nom + ", communes=" + communes
        + "]";
  }

}
