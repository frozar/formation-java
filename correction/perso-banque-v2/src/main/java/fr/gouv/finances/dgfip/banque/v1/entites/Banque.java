package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.javatuples.Pair;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v1.Util;

public class Banque {

  private final String codeBanque;

  private HashMap<CompteBancaire, Personne> mapCompteAPersonne = new HashMap<CompteBancaire, Personne>();
  
  public HashMap<CompteBancaire, Personne> getMapCompteAPersonne() {
	  return this.mapCompteAPersonne;
  }
  
  public Banque(String codeBanque) {
    this.codeBanque = codeBanque;
  }

  public String getCodeBanque() {
    return codeBanque;
  }

  public Personne getTitulaire(CompteBancaire compte) {
    return this.mapCompteAPersonne.get(compte);
  }

  public CompteCourant creerCompteCourant(Personne titulaire,
      String codeGuichet, Double soldeInitial) throws CompteException {
    if(soldeInitial < 0) {
      throw new CompteException("Solde initial négatif");
    }
    Integer numCompteInt = mapCompteAPersonne.size();
    String numComptePadded = Util.padLeftZeros(numCompteInt.toString(), 10);
    String cle = "99";
    CompteCourant newCompteCourant = new CompteCourant(this.codeBanque,
        codeGuichet, numComptePadded, cle, soldeInitial);
    newCompteCourant.setBanque(this);
    mapCompteAPersonne.put(newCompteCourant, titulaire);
    return newCompteCourant;
  }

  public CompteCourant creerCompteCourant(Personne titulaire,
      String codeGuichet) throws CompteException {
    return this.creerCompteCourant(titulaire, codeGuichet, 0.0);
  }

  @Override
  public String toString() {
    return "Banque [codeBanque=" + codeBanque + "]";
  }




  public void afficherSyntheseComptes() {
    System.out.println(
        "+-----------------+--------------------------+----------------------+------------+\n"
            + "| Type compte     | RIB                      | Titulaire            | Solde      |\n"
            + "+-----------------+--------------------------+----------------------+------------+");
    mapCompteAPersonne.forEach((compte, personne) -> {
      String compteType = compte.getClass()
          .toString().equals( "class fr.gouv.finances.dgfip.banque.CompteEpargne")
              ? "Compte epargne"
              : "Courant courant";
      String fullName = String.format("%s %s", personne.getNom(),
          personne.getPrenom());
      String paddedCompteType = Util.padRightSpaces(compteType, 15);
      String paddedRib = compte.getRib();
      String paddedFullName = Util.padRightSpaces(fullName, 20);
      String paddedSolde = Util.padLeftSpaces(compte.calculerSolde().toString(),
          10);
      System.out.println("| " + paddedCompteType + " | " + paddedRib + " | "
          + paddedFullName + " | " + paddedSolde + " |");
    });
    System.out.println(
        "+-----------------+--------------------------+----------------------+------------+");
  }
}
