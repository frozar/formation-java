package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.ArrayList;
import java.util.List;

public abstract class CompteBancaire {
  protected final String codeBanque;
  protected final String codeGuichet;
  protected final String numCompte;
  protected final String cle;
  protected final Double solde;

  /********************************/
  private Banque banque;
  private Personne titulaire;

  /********************************/
  protected List<Operation> operations = new ArrayList<Operation>();

  private int numeroOperation = 1;

  /********************************/
  public CompteBancaire(String codeBanque, String codeGuichet, String numCompte,
      String cle, Double solde) {
    this.codeBanque = codeBanque;
    this.codeGuichet = codeGuichet;
    this.numCompte = numCompte;
    this.cle = cle;
    this.solde = solde;
  }

  /********************************/
  public Banque getBanque() {
    return banque;
  }

  public void setBanque(Banque banque) {
    this.banque = banque;
  }

  public Personne getTitulaire() {
    return titulaire;
  }

  public void setTitulaire(Personne titulaire) {
    this.titulaire = titulaire;
  }

  public int getNumeroOperation() {
    return numeroOperation;
  }

  public void setNumeroOperation(int numeroOperation) {
    this.numeroOperation = numeroOperation;
  }

  public String getCodeBanque() {
    return codeBanque;
  }

  public String getCodeGuichet() {
    return codeGuichet;
  }

  public String getNumCompte() {
    return numCompte;
  }

  public String getCle() {
    return cle;
  }

  public Double getSolde() {
    return solde;
  }

  /********************************/
  public List<Operation> getOperations() {
    return operations;
  }

  public void addOperation(Operation operation) {
    operations.add(operation);
  }

  /********************************/
  public abstract Double calculerSolde();

//  public void afficherSyntheseOperations() {
//    System.out.println("");
//    System.out.println("Synthèse du compte: " + this.codeBanque + " "
//        + this.codeGuichet + " " + this.numCompte + " " + this.cle);
//    Personne titulaire = this.titulaire;
//    System.out.println(
//        "Titulaire: " + titulaire.getNom() + " " + titulaire.getPrenom());
//
//    System.out.println(
//        "+---------+-------------------------+-------------------------+------------+\n"
//            + "| Num opé | Date opération          | Libellé                 | Montant    |\n"
//            + "+---------+-------------------------+-------------------------+------------+");
//
//    afficherSyntheseOperation(0, new Date(), "SOLDE INITIAL", this.solde);
//
//    for (Operation op : operations) {
//      Integer numOperation = op.getNumOperation();
//      Date dateOperation = op.getDateOperation();
//      String libelle = op.getLibelle();
//      Double montant = op.getMontant();
//
//      afficherSyntheseOperation(numOperation, dateOperation, libelle, montant);
//    }
//    System.out.println(
//        "+---------+-------------------------+-------------------------+------------+");
//  }
//
//  private void afficherSyntheseOperation(Integer numOperation,
//      Date dateOperation, String libelle, Double montant) {
//    String paddedNumOperation = String.format("%7d", numOperation);
//    String date = new SimpleDateFormat("yyyy-MM-dd").format(dateOperation) + "T"
//        + new SimpleDateFormat("HH:mm:ss").format(dateOperation);
//    String paddedDate = String.format("%-23s", date);
//    String paddedLibelle = String.format("%-23s", libelle);
//    String paddedMontant = String.format("%10s", montant);
//    System.out.println("| " + paddedNumOperation + " | " + paddedDate + " | "
//        + paddedLibelle + " | " + paddedMontant + " |");
//  }

//  public Operation creerOperation(String libelle, Double montant)
//      throws CompteException {
//    Operation operation = new Operation(numeroOperation, new Date(), libelle,
//        montant);
//    operations.add(operation);
//    numeroOperation += 1;
//    return operation;
//  }

  public String getRib() {
    return String.format("%s %s %s %s", this.codeBanque, this.codeGuichet,
        this.numCompte, this.cle);
  }

}
