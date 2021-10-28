package com.example.accessingdatajpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

//// 1 : JOINED
//@Inheritance(strategy = InheritanceType.JOINED)
//@Entity
//@DiscriminatorColumn(name = "DTYPE")
//@DiscriminatorValue("USER")
//// 2 : TABLE_PER_CLASS
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Entity
// 3 : SINGLE_TABLE
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class User {

//// 1 : JOINED
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//// 2 : TABLE_PER_CLASS
//  @Id
//  @GeneratedValue(strategy = GenerationType.TABLE)
  // 3 : SINGLE_TABLE
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;

  public User() {
    this.nom = "";
  }

  public User(String nom) {
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

  @Override
  public String toString() {
    return "User [id=" + id + ", nom=" + nom + "]";
  }

}
