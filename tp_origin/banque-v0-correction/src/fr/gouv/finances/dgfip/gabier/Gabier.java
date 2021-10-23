/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés
 * 
 */
package fr.gouv.finances.dgfip.gabier;

import java.util.List;

public class Gabier
{
    private SystemeBancaireInterface systemeBancaire;

    // Constructeur
    public Gabier(SystemeBancaireInterface systemeBancaire)
    {
        this.systemeBancaire = systemeBancaire;
    }

    public List<String> accesComptes(String numCarte, String codePin) throws SystemeBancaireException
    {
        if (systemeBancaire.verifierCodePin(numCarte, codePin))
        {
            return systemeBancaire.rechercherRIBCompteCarte(numCarte);
        }
        else
        {
            throw new SystemeBancaireException("Accès refusé par le compte bancaire: Numéro de carte inconnu ou code PIN invalide");
        }
    }

    
    public int retirerEspeces(String ribCompte, Double montant) throws SystemeBancaireException {
        return systemeBancaire.creerOperation(ribCompte, "Retrait espèce Gabier", montant);
    }
    
    public SystemeBancaireInterface getSystemeBancaire()
    {
        return systemeBancaire;
    }

    public void setSystemeBancaire(SystemeBancaireInterface systemeBancaire)
    {
        this.systemeBancaire = systemeBancaire;
    }
}
