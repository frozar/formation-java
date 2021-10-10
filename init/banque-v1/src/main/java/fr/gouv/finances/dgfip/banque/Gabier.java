package fr.gouv.finances.dgfip.banque;

public class Gabier {

  private final SystemeBancaireInterface systemeBancaire;

  public Gabier(SystemeBancaireInterface systemeBancaire) {
    this.systemeBancaire = systemeBancaire;
  }

  String accesComptes(String numCarte, String codePin)
      throws SystemeBancaireException {
    if (this.systemeBancaire.verifierCodePin(numCarte, codePin)) {
      return this.systemeBancaire.rechercheRIBCompteCarte(numCarte);
    } else {
      throw new SystemeBancaireException("Carte ne peut pas être vérifiée");
    }
  }

  int retirerEspeces(String ribCompte, Double montant)
      throws SystemeBancaireException {
    return systemeBancaire.creerOperation(ribCompte, "Retrer gabier", montant);
  }
}
