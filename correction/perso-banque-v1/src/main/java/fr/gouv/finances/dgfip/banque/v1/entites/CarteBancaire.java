package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.Date;

public class CarteBancaire {
  /********************************/
  private final String codePin;
  private final String numCarte;
  private final Date dateExpiration;

  /********************************/
  private Banque banque;
  private Personne titulaire;
  private CompteCourant compteCourant;

  /********************************/
  public CarteBancaire(String codePin, String numCarte, Date dateExpiration) {
    this.codePin = codePin;
    this.numCarte = numCarte;
    this.dateExpiration = dateExpiration;
  }

  /********************************/
  public String getCodePin() {
    return codePin;
  }

  public String getNumCarte() {
    return numCarte;
  }

  public Date getDateExpiration() {
    return dateExpiration;
  }

  public Boolean verifierPin(String pin) {
    return this.codePin.equals(pin);
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

  public void setCompteCourant(CompteCourant compteCourant) {
    this.compteCourant = compteCourant;
  }

  public CompteCourant getCompteCourant() {
    return compteCourant;
  }

  public void setTitulaire(Personne titulaire) {
    this.titulaire = titulaire;
  }

}
