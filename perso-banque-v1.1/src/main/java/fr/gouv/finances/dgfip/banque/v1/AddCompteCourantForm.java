package fr.gouv.finances.dgfip.banque.v1;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class CompteCourantForm {
  @NotBlank(message = "Nom ?")
  private String nom;

  @NotBlank(message = "Prénom ?")
  private String prenom;

  @NotBlank(message = "Code guichet ?")
  @Max(value = 10000, message = "Doit être < 10000")
  private String codeGuichet;

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getCodeGuichet() {
    return codeGuichet;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setCodeGuichet(String codeGuichet) {
    this.codeGuichet = codeGuichet;
  }

  @Override
  public String toString() {
    return "CompteCourantForm [nom=" + nom + ", prenom=" + prenom
        + ", codeGuichet=" + codeGuichet + "]";
  }
}
