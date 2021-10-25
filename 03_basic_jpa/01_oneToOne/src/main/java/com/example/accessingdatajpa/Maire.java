package com.example.accessingdatajpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// Ajoute une contrainte sur la table en DB pour éviter d'avoir des doublons 
// en DB. Lien de documentation :
// https://www.baeldung.com/jpa-unique-constraints
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "Unique_Nom_And_Prenom", columnNames = { "nom",
        "prenom" }),
    // @UniqueConstraint(name = "Unique_Nom", columnNames = { "nom" })
})

@Entity
public class Maire {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;
  private String prenom;

  // L'argument "mappedBy" passé à l'annotation "@OneToOne" permet de créer une
  // relation bidirectionnelle entre Commune et Maire. Sans cet argument, un
  // champ de clé étrangère "COMMUNE_ID" aurait été créé dans la table "Maire"
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

  public Commune getCommune() {
    return commune;
  }

  public void setCommune(Commune commune) {
    this.commune = commune;
  }

  @Override
  public String toString() {
//    return "Maire [id=" + id + ", nom=" + nom + ", prenom=" + prenom
//        + ", commune=" + commune + "]";
    return "Maire [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
  }

}
