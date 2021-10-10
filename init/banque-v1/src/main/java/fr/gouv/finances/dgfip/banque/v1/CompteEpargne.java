package fr.gouv.finances.dgfip.banque.v1;

import java.util.Date;

public class CompteEpargne extends CompteBancaire {
  
  private Double txInteret;

  public CompteEpargne(String codeBanque, String codeGuichet, String numCompte, String cle, Double solde, Double txInteret) {
    super(codeBanque, codeGuichet, numCompte, cle, solde);
    this.txInteret = txInteret;
  }

  public Double calculerSolde() {
    Double solde = this.solde;
    for (int i = 0 ; i < this.operations.size(); i++) {
      solde += this.operations.get(i).getMontant();
    }
    return solde;
  }
  
  public Double calculerInterets() {
    return txInteret * calculerSolde();
  }
  
  public Integer creerOperation(String libelle, Double montant) throws CompteException {
    if( calculerSolde() + montant < 0) {
      throw new CompteException("Operation impossible : solde negatif");
    }
    else {
      return super.creerOperation(libelle, montant);
    }
  }
}
