package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.Date;

import fr.gouv.finances.dgfip.banque.v1.CompteException;

public class CompteCourant extends CompteBancaire {

  public CompteCourant(String codeBanque, String codeGuichet, String numCompte,
      String cle, Double solde) {
    super(codeBanque, codeGuichet, numCompte, cle, solde);
  }

  @Override
  public Double calculerSolde() {
//  System.out.println("calculerSolde");
    Double solde = this.solde;
    for (int i = 0; i < this.operations.size(); i++) {
      solde += this.operations.get(i).getMontant();
    }
    return solde;
  }

  @Override
  public Operation creerOperation(String libelle, Double montant)
      throws CompteException {
    Operation newOperation = new Operation(operations.size(), new Date(),
        libelle, montant);
    operations.add(newOperation);
    return newOperation;
  }

  @Override
  public String getType() {
    return "Compte courant";
  }
}
