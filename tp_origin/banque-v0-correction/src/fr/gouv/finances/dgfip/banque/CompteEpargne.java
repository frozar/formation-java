/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés
 * 
 */
package fr.gouv.finances.dgfip.banque;

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

    @Override
    public Operation creerOperation(String libelle, Double montant) throws CompteException
    {
        if(getSolde() + montant < 0)
            throw new CompteException(String.format("Solde (%.2f) insuffisant pour une opération de %.2f", getSolde(), montant));
        else
            return super.creerOperation(libelle, montant);
    }
}
