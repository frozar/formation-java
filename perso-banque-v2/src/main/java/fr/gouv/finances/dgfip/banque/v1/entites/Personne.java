package fr.gouv.finances.dgfip.banque.v1.entites;

import javax.validation.constraints.NotBlank;

public class Personne {

	  @NotBlank(message = "Entrer le nom svp")
	  private String nom;
	  @NotBlank(message = "Entrer le prénom svp")
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