package fr.gouv.finances.dgfip.banque;

import java.util.HashSet;
import java.util.Set;

public class Personne {
  /********************************/
  private final String nom;
  private final String prenom;

  /********************************/
  private Set<CompteBancaire> setCompteBancaire = new HashSet<CompteBancaire>();
  private Set<CarteBancaire> setCarte = new HashSet<CarteBancaire>();

  /********************************/
  public Personne(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
  }

  /********************************/
  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  /********************************/
  public void addCompteBancaire(CompteBancaire newCompteBancaire) {
    setCompteBancaire.add(newCompteBancaire);
  }

  public void addCarte(CarteBancaire newCarteBancaire) {
    setCarte.add(newCarteBancaire);
  }
}
