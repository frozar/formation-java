package fr.gouv.finances.dgfip.banque.v1.entites;

import javax.validation.constraints.NotBlank;

public class Personne {

  @NotBlank(message = "Nom ?")
  private String nom;
  @NotBlank(message = "Prénom ?")
  private String prenom;

  public Personne() {
  }

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + nom.hashCode();
    result = prime * result + prenom.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Personne other = (Personne) obj;
    if (!nom.equals(other.nom)) {
      return false;
    }
    if (!prenom.equals(other.prenom)) {
      return false;
    }

    return true;
  }
}
