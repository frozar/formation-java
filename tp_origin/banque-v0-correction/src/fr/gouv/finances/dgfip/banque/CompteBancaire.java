/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés7
 * 
 */
package fr.gouv.finances.dgfip.banque;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    
    public Operation creerOperation(String libelle, Double montant) throws CompteException {
        Operation ope = new Operation(numOperation, libelle, montant);
        this.numOperation += 1;
        this.operations.add(ope);
        this.solde = calculerSolde();
        return ope;
    }

    private double calculerSolde() {
        double s = 0;
        for(Operation ope: operations) {
            s += ope.getMontant();
        }
        return s;
    }
    
    public String getRib() {
        return String.format("%s %s %s %s", codeBanque, codeGuichet, numCompte, cle);
    }
    
    public void afficherSyntheseOperations() {
        String leftAlignFormat = "| %7d | %-23s | %-23s | %10.2f |%n";

        System.out.format("Synthèse du compte: %s%n", getRib());
        System.out.format("Titulaire: %s%n", titulaire);
        System.out.format("+---------+-------------------------+-------------------------+------------+%n");
        System.out.format("| Num opé | Date opération          | Libellé                 | Montant    |%n");
        System.out.format("+---------+-------------------------+-------------------------+------------+%n");
        for (Operation ope : operations) {
            System.out.format(leftAlignFormat, ope.getNumOperation(), ope.getDateOperation().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), ope.getLibelle(), ope.getMontant());
        }
        System.out.format("+---------+-------------------------+-------------------------+------------+%n");
        System.out.format("Solde: %10.2f%n",  solde);
    }

    
    // Getter pour solde
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

}
