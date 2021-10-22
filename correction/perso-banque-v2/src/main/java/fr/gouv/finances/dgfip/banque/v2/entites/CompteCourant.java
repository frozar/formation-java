package fr.gouv.finances.dgfip.banque.v2.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class CompteCourant extends CompteBancaire {
  /********************************/
  @OneToMany
  private Set<CarteBancaire> setCarte = new HashSet<CarteBancaire>();

  /********************************/
  public CompteCourant() {
    super();
  }

  public CompteCourant(String codeBanque, String codeGuichet, String numCompte,
      String cle, Double solde) {
    super(codeBanque, codeGuichet, numCompte, cle, solde);
  }

  public Double calculerSolde() {
    Double solde = this.solde;
    for (int i = 0; i < this.operations.size(); i++) {
      solde += this.operations.get(i).getMontant();
    }
    return solde;
  }

  /********************************/
  public void addCarte(CarteBancaire newCarteBancaire) {
    setCarte.add(newCarteBancaire);
  }
}
