package fr.gouv.finances.dgfip.banque.v4.entites;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Operation {
  @Id
  @GeneratedValue
  private UUID id;
  private final Integer numOperation;
  private final Date dateOperation;
  private final String libelle;
  private final Double montant;

  public Operation() {
    this.numOperation = 0;
    this.dateOperation = new Date();
    this.libelle = "";
    this.montant = 0.;
  }

  public Operation(Integer numOperation, Date dateOperation, String libelle,
      Double montant) {
    this.numOperation = numOperation;
    this.dateOperation = dateOperation;
    this.libelle = libelle;
    this.montant = montant;
  }

  public Integer getNumOperation() {
    return numOperation;
  }

  public Date getDateOperation() {
    return dateOperation;
  }

  public String getLibelle() {
    return libelle;
  }

  public Double getMontant() {
    return montant;
  }
}
