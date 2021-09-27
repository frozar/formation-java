package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.Date;

public class Operation {
  private final Integer numOperation;
  private final Date dateOperation;
  private final String libelle;
  private final Double montant;
  
  public Operation(Integer numOperation, Date dateOperation, String libelle, Double montant) {
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
