/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés
 * 
 */
package fr.gouv.finances.dgfip.banque.v1.entites;


public class CompteEpargne extends CompteBancaire
{
    private double txInteret;
    
    public CompteEpargne(Personne titulaire, String codeBanque, String codeGuichet, String numCompte, String cle, Double txInteret)
    {
        super(titulaire, codeBanque, codeGuichet, numCompte, cle);
        this.txInteret = txInteret;
    }    

    double calculerInteret(double taux)
    {
        return txInteret * getSolde();
    }

    public double getTxInteret()
    {
        return txInteret;
    }

    public void setTxInteret(double txInteret)
    {
        this.txInteret = txInteret;
    }
}
