package fr.gouv.finances.dgfip.banque.v1.entites;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

//@NotEmpty
//@NotNull
//@NotBlank

public class Personne {

  @NotBlank(message = "Nom ?")
  private String nom;
  @NotBlank(message = "Prénom ?")
  private String prenom;
  
  public Personne() {}
  
  public Personne(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  @Override
  public String toString() {
    return "Personne [nom=" + nom + ", prenom=" + prenom + "]";
  }
}
