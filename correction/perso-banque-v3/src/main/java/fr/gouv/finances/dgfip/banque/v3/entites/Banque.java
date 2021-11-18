package fr.gouv.finances.dgfip.banque.v3.entites;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banque {
  @Id
  @GeneratedValue
  private UUID id;

  /********************************/
  @Column(unique = true)
  private final String codeBanque;

  /********************************/
  @OneToMany(mappedBy = "banque")
  private Set<CompteBancaire> setCompteBancaire = new HashSet<CompteBancaire>();
  @OneToMany(mappedBy = "banque")
  private Set<CarteBancaire> setCarte = new HashSet<CarteBancaire>();

  private int numeroCompte = 0;

  /********************************/
  public Banque() {
    this.codeBanque = "";
  }

  public Banque(String codeBanque) {
    this.codeBanque = codeBanque;
  }

  /********************************/
  public UUID getId() {
    return id;
  }

  public int getNumeroCompte() {
    return numeroCompte;
  }

  public String getCodeBanque() {
    return codeBanque;
  }

  public void setNumeroCompte(int numeroCompte) {
    this.numeroCompte = numeroCompte;
  }

  /********************************/
  public void addCompteBancaire(CompteBancaire c) {
    this.setCompteBancaire.add(c);
  }

  public void addCarte(CarteBancaire newCarteBancaire) {
    setCarte.add(newCarteBancaire);
  }

  public Set<CarteBancaire> getSetCarte() {
    return setCarte;
  }

  public Set<CompteBancaire> getSetCompteBancaire() {
    return setCompteBancaire;
  }

  public Set<Personne> getAdherents() {
    return setCompteBancaire.stream()
        .map((CompteBancaire c) -> c.getTitulaire())
        .collect(Collectors.toSet());
  }

  @Override
  public String toString() {
    return "Banque [id=" + id + ", codeBanque=" + codeBanque + ", setCarte="
        + setCarte + "]";
  }

}
