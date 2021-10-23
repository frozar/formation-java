package fr.gouv.finances.dgfip.banque.v2.services;

import java.util.List;

import fr.gouv.finances.dgfip.banque.v2.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v2.entites.Banque;

public interface SystemeBancaireInterface
{
    public List<String> rechercherRIBCompteCarte(Banque banque, String numCarte) throws SystemeBancaireException;
    public boolean verifierCodePin(Banque banque, String numCarte, String codePin) throws SystemeBancaireException;
    public int creerOperation(Banque banque, String ribCompte, String libelle, Double montant) throws SystemeBancaireException;
}
