package com.example.accessingdatajpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CodePostal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // unique: each Commune must have a different name in DB
  // length: limit the size of DB field NOM to 60 char
  @Column(unique = true, length = 60)
  private Integer numero;

  protected CodePostal() {
  }

  public CodePostal(Integer numero) {
    this.numero = numero;
  }

  public Long getId() {
    return id;
  }

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  @Override
  public String toString() {
    return "CodePostal [id=" + id + ", numero=" + numero + "]";
  }
}
