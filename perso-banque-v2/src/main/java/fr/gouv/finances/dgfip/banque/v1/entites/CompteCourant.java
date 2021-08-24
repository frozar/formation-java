package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.Date;

import fr.gouv.finances.dgfip.banque.v1.CompteException;

public class CompteCourant extends CompteBancaire {

  public CompteCourant(String codeBanque, String codeGuichet, String numCompte,
      String cle, Double solde) {
    super(codeBanque, codeGuichet, numCompte, cle, solde);
  }
  
  @Override
public String toString() {
	return "CompteCourant [codeBanque=" + codeBanque + ", codeGuichet=" + codeGuichet + ", numCompte=" + numCompte
			+ ", cle=" + cle + ", solde=" + solde + "]";
}



public CompteCourant() {
	    super();
	  }

@Override
public Double calculerSolde() {
	// TODO Auto-generated method stub
	return null;
}
}
