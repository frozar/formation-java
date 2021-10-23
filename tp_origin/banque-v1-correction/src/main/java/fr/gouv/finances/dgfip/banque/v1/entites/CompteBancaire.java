/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés7
 * 
 */
package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.ArrayList;
import java.util.List;

public abstract class CompteBancaire
{
    private String codeBanque;
    private String codeGuichet;
    private String numCompte;
    private String cle;
    private double solde;
    private Personne titulaire;
    private List<Operation> operations;
    private int numOperation;
    
    public CompteBancaire(Personne titulaire, String codeBanque, String codeGuichet, String numCompte, String cle) {
        this.titulaire = titulaire;
        this.codeBanque = codeBanque;
        this.codeGuichet = codeGuichet;
        this.numCompte = numCompte;
        this.cle = cle;
        this.operations = new ArrayList<>();
        this.numOperation = 0;
    }
    
    public void ajouterOperation(Operation ope) {
        operations.add(ope);
        this.numOperation += 1;
    }

    public String getRib() {
        return String.format("%s %s %s %s", codeBanque, codeGuichet, numCompte, cle);
    }
    
    public double getSolde()
    {
        return solde;
    }

    public String getCodeBanque()
    {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque)
    {
        this.codeBanque = codeBanque;
    }

    public String getCodeGuichet()
    {
        return codeGuichet;
    }

    public void setCodeGuichet(String codeGuichet)
    {
        this.codeGuichet = codeGuichet;
    }

    public String getNumCompte()
    {
        return numCompte;
    }

    public void setNumCompte(String numCompte)
    {
        this.numCompte = numCompte;
    }

    public String getCle()
    {
        return cle;
    }

    public void setCle(String cle)
    {
        this.cle = cle;
    }

    public Personne getTitulaire()
    {
        return titulaire;
    }

    public void setTitulaire(Personne titulaire)
    {
        this.titulaire = titulaire;
    }

    public List<Operation> getOperations()
    {
        return operations;
    }

    public int getNumOperation()
    {
        return numOperation;
    }

    public void setNumOperation(int numOperation)
    {
        this.numOperation = numOperation;
    }

    public void setSolde(double solde)
    {
        this.solde = solde;
    }

}
