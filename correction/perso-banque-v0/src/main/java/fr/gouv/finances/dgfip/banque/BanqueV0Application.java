package fr.gouv.finances.dgfip.banque;

public class BanqueV0Application {
  public static void main(String[] args) throws Throwable {
    Banque maBanque = new Banque("DGFIP");
    Personne paulette = new Personne("Blanchard", "Paulette");
    Personne dominique = new Personne("Guibert", "Dominique");
    Personne thibault = new Personne("Guillou", "Thibault");
    Personne andre = new Personne("Labbe", "André");

    System.out
        .println("*********************************************************");
    System.out
        .println("*** Ouverture d'un compte courant Paulette Blanchard  ***");
    CompteCourant ccPaulette = maBanque.creerCompteCourant(paulette, "1234");
    System.out
        .println("*** Dépôt d'un chèque de 100                          ***");
    ccPaulette.creerOperation("Dépôt chèque", 100.0);
    ccPaulette.afficherSyntheseOperations();
    System.out
        .println("*********************************************************\n");

    System.out
        .println("*********************************************************");
    System.out
        .println("*** Ouverture d'un compte courant Dominique Guibert   ***");
    CompteCourant ccDominique = maBanque.creerCompteCourant(dominique, "5678");
    System.out
        .println("*** Dépôt d'un chèque de 200                          ***");
    ccDominique.creerOperation("Dépôt espèces", 200.0);
    ccDominique.afficherSyntheseOperations();
    System.out
        .println("*********************************************************\n");

    System.out
        .println("*********************************************************");
    System.out
        .println("*** Débit de 50 sur le compte     Paulette Blanchard  ***");
    ccPaulette.creerOperation("Débit", -50.0);
    System.out
        .println("*** Débit de 300 sur le compte    Dominique Guibert   ***");
    ccDominique.creerOperation("Débit", -300.0);
    System.out
        .println("*********************************************************\n");

    ccPaulette.afficherSyntheseOperations();
    System.out.println("\n");
    ccDominique.afficherSyntheseOperations();

    System.out
        .println("*********************************************************");
    System.out
        .println("*** Ouverture d'un compte épargne Thibault Guillou    ***");
    System.out
        .println("*** montant initial=3000, taux=2%                     ***");
    CompteEpargne ceThibault = maBanque.creerCompteEpargne(thibault, "1234",
        3000.0, 0.02);
    System.out
        .println("*** Crédit de 1000 sur le compte Thibaud Guillou      ***");
    ceThibault.creerOperation("Dépôt", 1000.0);
    try {
      System.out
          .println("*** Débit de 10000 sur le compte Thibaud Guillou      ***");
      ceThibault.creerOperation("Retrait", -10000.0);
    } catch (CompteException ce) {
      System.err.println(
          "*** Impossible de débiter le ceThibault: " + ce.getMessage());
    }
    System.out
        .println("*********************************************************\n");

    System.out
        .println("*********************************************************");
    System.out
        .println("*** Ouverture d'un compte épargne André Labbe         ***");
    System.out
        .println("*** montant initial=10000, taux=2%                    ***");
    maBanque.creerCompteEpargne(andre, "5678", 10000.0, 0.02);
    System.out
        .println("*********************************************************\n");

    System.out
        .println("*********************************************************");
    System.out
        .println("*** Synthèse banque                                   ***");
    maBanque.afficherSyntheseComptes();
    System.out
        .println("*********************************************************\n");

    System.out
        .println("*********************************************************");
    System.out
        .println("*** Création CB Paulette                              ***");
    CarteBancaire cbPaulette = maBanque.creerCarte(paulette, ccPaulette);
    System.out
        .println("*** Création CB Dominique                             ***");
    CarteBancaire cbDominique = maBanque.creerCarte(dominique, ccDominique);
    System.out
        .println("*********************************************************\n");

    System.out
        .println("*********************************************************");
    System.out
        .println("*** Synthèse cartes                                   ***");
    maBanque.afficherSyntheseCartes();
    System.out
        .println("*********************************************************\n");

    Gabier gabier1 = new Gabier(maBanque);
    System.out
        .println("*********************************************************");
    System.out
        .println("*** Retrait de 314 sur le compte courant de Paulette  ***");
    try {
      String ribComptePaulette = gabier1.accesComptes(cbPaulette.getNumCarte(),
          cbPaulette.getCodePin());
      System.out.println("*** Liste de comptes de Paulette:");
      System.out.println("***  - " + ribComptePaulette);
      int numOperation = gabier1.retirerEspeces(ribComptePaulette, 314.0);
      System.out.format("*** Opération réalisée: %d  ***\n", numOperation);
      ccPaulette.afficherSyntheseOperations();
    } catch (SystemeBancaireException e) {
      System.err.println(
          "Accès impossible aux comptes de Paulette: " + e.getMessage());
    }
    System.out
        .println("*********************************************************\n");

    System.out
        .println("***********************************************************");
    System.out
        .println("*** Tentative retrait de 567 sur le compte de Dominique ***");
    try {
      String ribCompteDominique = gabier1
          .accesComptes(cbDominique.getNumCarte(), "CODE PIN FAUX");
      System.out.println("*** Liste de comptes de Dominique:");
      System.out.println("***  - " + ribCompteDominique);
      int numOperation = gabier1.retirerEspeces(ribCompteDominique, 567.0);
      System.out.format("*** Opération réalisée: %d  ***", numOperation);
      ccDominique.afficherSyntheseOperations();
    } catch (SystemeBancaireException e) {
      System.err.println(
          "Accès impossible aux comptes de Dominique: " + e.getMessage());
      ccDominique.afficherSyntheseOperations();
    }
    System.out
        .println("*********************************************************");

  }
}
