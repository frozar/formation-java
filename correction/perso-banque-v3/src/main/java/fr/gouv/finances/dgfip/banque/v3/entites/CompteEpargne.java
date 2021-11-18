package fr.gouv.finances.dgfip.banque.v3.entites;

import javax.persistence.Entity;

@Entity
public class CompteEpargne extends CompteBancaire {

  private Double txInteret;

  public CompteEpargne() {
    super();
    this.txInteret = 0.;
  }

  public CompteEpargne(String codeBanque, String codeGuichet, String numCompte,
      String cle, Double solde, Double txInteret) {
    super(codeBanque, codeGuichet, numCompte, cle, solde);
    this.txInteret = txInteret;
  }

  public Double calculerSolde() {
    Double solde = this.solde;
    for (int i = 0; i < this.operations.size(); i++) {
      solde += this.operations.get(i).getMontant();
    }
    return solde;
  }

  public Double calculerInterets() {
    return txInteret * calculerSolde();
  }

  public Double getTxInteret() {
    return txInteret;
  }

}
