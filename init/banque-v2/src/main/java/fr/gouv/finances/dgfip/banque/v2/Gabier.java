//package fr.gouv.finances.dgfip.banque.v1;
//
//public class Gabier {
//
//  private final SystemeBancaireInterface systemeBancaire;
//
//  public Gabier(SystemeBancaireInterface systemeBancaire) {
//    this.systemeBancaire = systemeBancaire;
//  }
//
//  String accesComptes(String numCarte, String codePin)
//      throws SystemeBancaireException {
//    if (this.systemeBancaire.verifierCodePin(numCarte, codePin)) {
//      return this.systemeBancaire.rechercheRIBCompteCarte(numCarte);
//    } else {
//      throw new SystemeBancaireException("Carte ne peut pas être vérifiée");
//    }
//  }
//
//  int retirerEspeces(String ribCompte, Double montant)
//      throws SystemeBancaireException {
//    return systemeBancaire.creerOperation(ribCompte, "Retrer gabier", montant);
//  }
//}

package fr.gouv.finances.dgfip.banque.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.Operation;

@Component
public class Gabier {

  private SystemeBancaireInterface systemeBancaire;

  @Autowired
  public Gabier(SystemeBancaireInterface systemeBancaire) {
    this.systemeBancaire = systemeBancaire;
  }

  public String accesComptes(Banque banque, String numCarte, String codePin)
      throws SystemeBancaireException {
    if (systemeBancaire.verifierCodePin(banque, numCarte, codePin)) {
      return systemeBancaire.rechercheRIBCompteCarte(banque, numCarte);
    } else {
      throw new SystemeBancaireException("Carte ne peut pas être vérifiée");
    }
  }

  public Operation retirerEspeces(Banque banque, String ribCompte,
      Double montant) throws SystemeBancaireException {
    if (0 <= montant) {
      return systemeBancaire.creerOperation(banque, ribCompte, "Retrait gabier",
          -montant);
    } else {
      throw new SystemeBancaireException("Montant du retrait négatif");
    }
  }
}