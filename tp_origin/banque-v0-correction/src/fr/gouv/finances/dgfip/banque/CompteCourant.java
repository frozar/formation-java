/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés
 * 
 */
package fr.gouv.finances.dgfip.banque;

public class CompteCourant extends CompteBancaire
{

    public CompteCourant(Personne titulaire, String codeBanque, String codeGuichet, String numCompte, String cle)
    {
        super(titulaire, codeBanque, codeGuichet, numCompte, cle);
        
    }
}
