/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés
 * 
 */
package fr.gouv.finances.dgfip.banque.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.services.SystemeBancaireInterface;

@Component
public class Gabier
{
    private SystemeBancaireInterface systemeBancaire;

    // Constructeur
    public Gabier()
    {
    }

    public List<String> accesComptes(Banque banque, String numCarte, String codePin) throws SystemeBancaireException
    {
        if (systemeBancaire.verifierCodePin(banque, numCarte, codePin))
        {
            return systemeBancaire.rechercherRIBCompteCarte(banque, numCarte);
        }
        else
        {
            throw new SystemeBancaireException("Accès refusé par le compte bancaire: Numéro de carte inconnu ou code PIN invalide");
        }
    }

    
    public int retirerEspeces(Banque banque, String ribCompte, Double montant) throws SystemeBancaireException {
        return systemeBancaire.creerOperation(banque, ribCompte, "Retrait espèce Gabier", montant);
    }
    
    public SystemeBancaireInterface getSystemeBancaire()
    {
        return systemeBancaire;
    }

    @Autowired
    public void setSystemeBancaire(SystemeBancaireInterface systemeBancaire)
    {
        this.systemeBancaire = systemeBancaire;
    }
}
