package fr.gouv.finances.dgfip.banque.v1;

public interface SystemeBancaireInterface {
  String rechercheRIBCompteCarte(String numCarte)
      throws SystemeBancaireException;

  Integer creerOperation(String ribCompte, String libelle, Double montant)
      throws SystemeBancaireException;

  Boolean verifierCodePin(String numCarteCompte, String codePin)
      throws SystemeBancaireException;
}
