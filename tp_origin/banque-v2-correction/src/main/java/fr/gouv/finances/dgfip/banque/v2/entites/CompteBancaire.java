/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés7
 * 
 */
package fr.gouv.finances.dgfip.banque.v2.entites;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class CompteBancaire
{
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    protected Banque banque;
    
    private String codeBanque;
    private String codeGuichet;
    private String numCompte;
    private String cle;
    private double solde;
    @ManyToOne
    private Personne titulaire;
    private int numOperation;
    
    public CompteBancaire() {
        
    }
    
    public CompteBancaire(Personne titulaire, String codeBanque, String codeGuichet, String numCompte, String cle) {
        this();
        this.titulaire = titulaire;
        this.codeBanque = codeBanque;
        this.codeGuichet = codeGuichet;
        this.numCompte = numCompte;
        this.cle = cle;
        this.numOperation = 0;
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

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public Banque getBanque()
    {
        return banque;
    }

    public void setBanque(Banque banque)
    {
        this.banque = banque;
    }

}
