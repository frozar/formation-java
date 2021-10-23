package fr.gouv.finances.dgfip.banque.v2.entites;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CarteBancaire {
  @Id
  @GeneratedValue
  private UUID id;

  /********************************/
  private String codePin;
  private String numCarte;
  private Date dateExpiration;

  /********************************/
  @ManyToOne
  private Banque banque;
  @ManyToOne
  private Personne titulaire;
  @ManyToOne
  private CompteCourant compteCourant;

  /********************************/
  public CarteBancaire() {
    this.codePin = "";
    this.numCarte = "";
    this.dateExpiration = new Date();
  }

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
