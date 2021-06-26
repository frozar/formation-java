package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.Date;

public class CarteBancaire {
  private final String codePin;
  private final String numCarte;
  private final Date dateExpiration;

  public CarteBancaire(String codePin, String numCarte, Date dateExpiration) {
    this.codePin = codePin;
    this.numCarte = numCarte;
    this.dateExpiration = dateExpiration;
  }

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
  private Banque banque;

  public Banque getBanque() {
    return banque;
  }

  public void setBanque(Banque banque) {
    this.banque = banque;
  }
  
}
