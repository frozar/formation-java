package fr.gouv.finances.dgfip.banque.v1.services;

import java.util.List;

import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;

public interface SystemeBancaireInterface
{
    public List<String> rechercherRIBCompteCarte(Banque banque, String numCarte) throws SystemeBancaireException;
    public boolean verifierCodePin(Banque banque, String numCarte, String codePin) throws SystemeBancaireException;
    public int creerOperation(Banque banque, String ribCompte, String libelle, Double montant) throws SystemeBancaireException;
}
