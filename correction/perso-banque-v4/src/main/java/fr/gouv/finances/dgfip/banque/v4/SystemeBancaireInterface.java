package fr.gouv.finances.dgfip.banque.v4;

import fr.gouv.finances.dgfip.banque.v4.entites.Banque;
import fr.gouv.finances.dgfip.banque.v4.entites.Operation;

public interface SystemeBancaireInterface {
  String rechercheRIBCompteCarte(Banque banque, String numCarte)
      throws SystemeBancaireException;

  Operation creerOperation(Banque banque, String ribCompte, String libelle,
      Double montant) throws SystemeBancaireException;

  Boolean verifierCodePin(Banque banque, String numCarteCompte, String codePin)
      throws SystemeBancaireException;
}
