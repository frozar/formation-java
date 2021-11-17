package fr.gouv.finances.dgfip.banque.v2.entites;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Personne {
  @Id
  @GeneratedValue
  private UUID id;

  /********************************/
  private final String nom;
  private final String prenom;

  /********************************/
  @OneToMany(mappedBy = "titulaire")
  private Set<CompteBancaire> setCompteBancaire = new HashSet<CompteBancaire>();
  @OneToMany(mappedBy = "titulaire")
  private Set<CarteBancaire> setCarte = new HashSet<CarteBancaire>();

  /********************************/
  public Personne() {
    this.nom = "";
    this.prenom = "";
  }

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

  @Override
  public String toString() {
    return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
  }

}
