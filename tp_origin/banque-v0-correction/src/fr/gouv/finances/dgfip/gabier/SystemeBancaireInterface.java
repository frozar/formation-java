package fr.gouv.finances.dgfip.gabier;

import java.util.List;

public interface SystemeBancaireInterface
{
    public List<String> rechercherRIBCompteCarte(String numCarte) throws SystemeBancaireException;
    public boolean verifierCodePin(String numCarte, String codePin) throws SystemeBancaireException;
    public int creerOperation(String ribCompte, String libelle, Double montant) throws SystemeBancaireException;
}
