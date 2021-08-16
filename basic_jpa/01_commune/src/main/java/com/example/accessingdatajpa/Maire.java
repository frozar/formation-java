package com.example.accessingdatajpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Maire {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;
  private String prenom;

  // L'argument "mappedBy" passé à l'annotation "@OneToOne" permet de créer une
  // relation bidirectionnelle entre Commune et Maire. Sans cet argument, un
  // champ de clé étrangère "ID_Commune" aurait été créé dans la table "Maire"
  // ce qui peut entrainer des états incohérents de la DB.
  @OneToOne(mappedBy = "maire")
  private Commune commune;

  public Maire() {
  }

  public Maire(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    return "Maire [id=" + id + ", nom=" + nom + ", prenom=" + prenom
        + ", commune=" + commune + "]";
  }

}
