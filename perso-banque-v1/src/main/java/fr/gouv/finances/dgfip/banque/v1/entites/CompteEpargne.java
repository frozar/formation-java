package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.Date;

import fr.gouv.finances.dgfip.banque.v1.CompteException;

public class CompteEpargne extends CompteBancaire {
  
  private Double txInteret;

  public CompteEpargne(String codeBanque, String codeGuichet, String numCompte, String cle, Double solde, Double txInteret) {
    super(codeBanque, codeGuichet, numCompte, cle, solde);
    this.txInteret = txInteret;
  }

  @Override
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
  
  @Override
  public Operation creerOperation(String libelle, Double montant)
      throws CompteException {
    if(calculerSolde() + montant < 0) {
      throw new CompteException("Fond insuffisant");
    }
    else {
      Operation newOperation = new Operation(operations.size(), new Date(),
          libelle, montant);
      operations.add(newOperation);
      return newOperation;
    }
  }
}
