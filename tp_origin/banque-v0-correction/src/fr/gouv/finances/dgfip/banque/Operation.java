package fr.gouv.finances.dgfip.banque;

import java.time.ZonedDateTime;

public class Operation
{
    private int numOperation;
    private ZonedDateTime dateOperation;
    private String libelle;
    private Double montant;
    
    public Operation(int numOperation, String libelle, Double montant ) {
        this.numOperation = numOperation;
        this.libelle = libelle;
        this.montant = montant;
        this.dateOperation = ZonedDateTime.now();
    }
    
    public Operation(int numOperation, Double montant) {
        this(numOperation, "", montant);
    }

    public int getNumOperation()
    {
        return numOperation;
    }

    public void setNumOperation(int numOperation)
    {
        this.numOperation = numOperation;
    }

    public ZonedDateTime getDateOperation()
    {
        return dateOperation;
    }

    public void setDateOperation(ZonedDateTime dateOperation)
    {
        this.dateOperation = dateOperation;
    }

    public String getLibelle()
    {
        return libelle;
    }

    public void setLibelle(String libelle)
    {
        this.libelle = libelle;
    }

    public Double getMontant()
    {
        return montant;
    }

    public void setMontant(Double montant)
    {
        this.montant = montant;
    }
    
    
}
