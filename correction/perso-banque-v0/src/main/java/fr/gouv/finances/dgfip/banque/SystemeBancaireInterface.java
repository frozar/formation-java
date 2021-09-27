package fr.gouv.finances.dgfip.banque;

import java.util.List;

public interface SystemeBancaireInterface {
  List<String> rechercheRIBCompteCarte(String numCarte) throws SystemeBancaireException;
  Integer creerOperation(String ribCompte, String libelle, Double montant) throws SystemeBancaireException;
  Boolean verifierCodePin(String numCarteCompte, String codePin) throws SystemeBancaireException;
}
