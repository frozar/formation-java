package fr.gouv.finances.dgfip.banque.v1.entites;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.Util;

public abstract class CompteBancaire {
//  @NotNull
  protected String codeBanque;

  @NotBlank(message = "Code guichet ?")
  protected String codeGuichet;

  @NotBlank(message = "Numero de compte ?")
  protected String numCompte;

  @NotBlank(message = "Clé ?")
  protected String cle;

  @Min(value = 0, message = "Solde positif ?")
  protected Double solde;

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

  public void setCodeBanque(String codeBanque) {
    this.codeBanque = codeBanque;
  }

  public void setCodeGuichet(String codeGuichet) {
    this.codeGuichet = codeGuichet;
  }

  public void setNumCompte(String numCompte) {
    this.numCompte = numCompte;
  }

  public void setCle(String cle) {
    this.cle = cle;
  }

  public void setSolde(Double solde) {
    this.solde = solde;
  }

  protected List<Operation> operations = new ArrayList<Operation>();

  public CompteBancaire(String codeBanque, String codeGuichet, String numCompte,
      String cle, Double solde) {
    this.codeBanque = codeBanque;
    this.codeGuichet = codeGuichet;
    this.numCompte = numCompte;
    this.cle = cle;
    this.solde = solde;
  }

  public CompteBancaire() {
  }

  public abstract Double calculerSolde();

  public abstract String getType();

  public void afficherSyntheseOperations() {
    System.out.println("Synthèse du compte: " + this.codeBanque + " "
        + this.codeGuichet + " " + this.numCompte + " " + this.cle);
    Personne titulaire = this.banque.getTitulaire(this);
    System.out.println(
        "Titulaire: " + titulaire.getNom() + " " + titulaire.getPrenom());

    System.out.println(
        "+---------+-------------------------+-------------------------+------------+");
    System.out.println(
        "| Num opé | Date opération          | Libellé                 | Montant    |");
    System.out.println(
        "+---------+-------------------------+-------------------------+------------+");

    String paddedIndex0 = Util.padLeftSpaces(String.valueOf(0), 7);
    Date now = new Date();
    String dateCalendar0 = new SimpleDateFormat("yyyy-MM-dd").format(now);
    String dateHours0 = new SimpleDateFormat("HH:mm:ss").format(now);
    String date0 = dateCalendar0 + "T" + dateHours0;
    String paddedDate0 = Util.padRightSpaces(date0, 23);
    String paddedLibelle0 = Util.padRightSpaces("SOLDE INITIAL", 23);
    String paddedMontant0 = Util.padLeftSpaces(this.solde.toString(), 10);
    System.out.println("| " + paddedIndex0 + " | " + paddedDate0 + " | "
        + paddedLibelle0 + " | " + paddedMontant0 + " |");

    ListIterator<Operation> it = operations.listIterator();
    while (it.hasNext()) {
      Operation op = it.next();
      String paddedIndex = Util.padLeftSpaces(String.valueOf(it.nextIndex()),
          7);
      String dateCalendar = new SimpleDateFormat("yyyy-MM-dd")
          .format(op.getDateOperation());
      String dateHours = new SimpleDateFormat("HH:mm:ss")
          .format(op.getDateOperation());
      String date = dateCalendar + "T" + dateHours;
      String paddedDate = Util.padRightSpaces(date, 23);
      String paddedLibelle = Util.padRightSpaces(op.getLibelle(), 23);
      String paddedMontant = Util.padLeftSpaces(op.getMontant().toString(), 10);
      System.out.println("| " + paddedIndex + " | " + paddedDate + " | "
          + paddedLibelle + " | " + paddedMontant + " |");
    }
    System.out.println(
        "+---------+-------------------------+-------------------------+------------+");
  }

  public abstract Operation creerOperation(String libelle, Double montant)
      throws CompteException;

  public String getRib() {
    return String.format("%s %s %s %s", this.codeBanque, this.codeGuichet,
        this.numCompte, this.cle);
  }

  public Integer getNbOperation() {
    return operations.size();
  }

  /********************************/
  private Banque banque;

  public Banque getBanque() {
    return banque;
  }

  public void setBanque(Banque banque) {
    this.banque = banque;
  }

}
