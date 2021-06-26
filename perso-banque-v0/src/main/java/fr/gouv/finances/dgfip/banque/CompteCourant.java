package fr.gouv.finances.dgfip.banque;

public class CompteCourant extends CompteBancaire {

  public CompteCourant(String codeBanque, String codeGuichet, String numCompte, String cle, Double solde) {
    super(codeBanque, codeGuichet, numCompte, cle, solde);
  }

  public Double calculerSolde() {
//  System.out.println("calculerSolde");
  Double solde = this.solde;
  for (int i = 0 ; i < this.operations.size(); i++) {
    solde += this.operations.get(i).getMontant();
  }
  return solde;
  }
}
