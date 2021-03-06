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

  private String codeBanque;

  private HashMap<CompteBancaire, Personne> mapCompteAPersonne = new HashMap<CompteBancaire, Personne>();
  private HashMap<CarteBancaire, Pair<Personne, CompteBancaire>> mapCarteAPersonneCompteBancaire = new HashMap<CarteBancaire, Pair<Personne, CompteBancaire>>();

  public Banque(String codeBanque) {
    this.codeBanque = codeBanque;
  }

  public HashMap<CompteBancaire, Personne> getMapCompteAPersonne() {
    return mapCompteAPersonne;
  }

  public void setCodeBanque(String codeBanque) {
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
    if (soldeInitial < 0) {
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

  public CompteEpargne creerCompteEpargne(Personne titulaire,
      String codeGuichet, Double soldeInitial, Double taux)
      throws CompteException {
    if (soldeInitial < 0) {
      throw new CompteException("Solde initial négatif");
    }
    if (taux < 0) {
      throw new CompteException("Taux d'épargne négatif");
    }
    Integer numCompteInt = mapCompteAPersonne.size();
    String numComptePadded = Util.padLeftZeros(numCompteInt.toString(), 10);
    String cle = "99";
    CompteEpargne newCompteEpargne = new CompteEpargne(this.codeBanque,
        codeGuichet, numComptePadded, cle, soldeInitial, taux);
    mapCompteAPersonne.put(newCompteEpargne, titulaire);
    return newCompteEpargne;
  }

  public Boolean supprimerCompte(String codeGuichet, String numCompte) {
    Boolean res = false;
    for (CompteBancaire compte : mapCompteAPersonne.keySet()) {
      if (compte.codeGuichet.equals(codeGuichet)
          && compte.numCompte.equals(numCompte)) {
        mapCompteAPersonne.remove(compte);
        res = true;
        break;
      }
    }
    return res;
  }

  public CarteBancaire creerCarte(Personne titulaire, CompteBancaire compte) {
    String numCarte = "4328 1561 1140 6510";
    String codePin = "5613";
    Calendar calendar = new GregorianCalendar();
    int yearPlus3 = calendar.get(Calendar.YEAR) + 3;
    calendar.set(Calendar.YEAR, yearPlus3);
    Date dateExpiration = calendar.getTime();
    CarteBancaire newCarteBancaire = new CarteBancaire(codePin, numCarte,
        dateExpiration);
    Pair<Personne, CompteBancaire> titulaireCompte = Pair.with(titulaire,
        compte);
    mapCarteAPersonneCompteBancaire.put(newCarteBancaire, titulaireCompte);
    return newCarteBancaire;
  }

  public CarteBancaire lierCarte(CarteBancaire carte, CompteBancaire compte) {
    Personne personne = mapCompteAPersonne.get(compte);
    mapCarteAPersonneCompteBancaire.put(carte, Pair.with(personne, compte));
    return carte;
  }

  public void afficherSyntheseComptes() {
    System.out.println(
        "+-----------------+--------------------------+----------------------+------------+\n"
            + "| Type compte     | RIB                      | Titulaire            | Solde      |\n"
            + "+-----------------+--------------------------+----------------------+------------+");
    mapCompteAPersonne.forEach((compte, personne) -> {
      String compteType = compte.getType();
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

  public void afficherSyntheseCartes() {
    System.out
        .println("+----------------------+----------------------+----------+\n"
            + "| Titulaire            | Num. Carte           | Code PIN |\n"
            + "+----------------------+----------------------+----------+");
    mapCarteAPersonneCompteBancaire.forEach((carte, titulaireCompte) -> {
      Personne titulaire = titulaireCompte.getValue0();
      String fullName = titulaire.getNom() + " " + titulaire.getPrenom();
      String paddedFullName = Util.padRightSpaces(fullName, 21);
      String paddedNumCarte = Util.padRightSpaces(carte.getNumCarte(), 21);
      String paddedPin = Util.padRightSpaces(carte.getCodePin(), 8);
      ;
      System.out.println("| " + paddedFullName + "| " + paddedNumCarte + "| "
          + paddedPin + " |");
    });
    System.out
        .println("+----------------------+----------------------+----------+");
  }

  public List<String> rechercheRIBCompteCarte(String numCarte)
      throws SystemeBancaireException {
    Optional<CarteBancaire> foundCarte = this.mapCarteAPersonneCompteBancaire
        .keySet().stream().filter(carte -> carte.getNumCarte().equals(numCarte))
        .findFirst();
    if (foundCarte.isPresent()) {
      Pair<Personne, CompteBancaire> tc = mapCarteAPersonneCompteBancaire
          .get(foundCarte.get());
      return Arrays.asList(tc.getValue1().getRib());
    } else {
      throw new SystemeBancaireException("Carte pas trouvé");
    }
  }

  public Integer creerOperation(String ribCompte, String libelle,
      Double montant) throws SystemeBancaireException {
    Optional<CompteBancaire> foundCompte = mapCompteAPersonne.keySet().stream()
        .filter(compte -> compte.getRib().equals(ribCompte)).findFirst();
    if (foundCompte.isPresent()) {
      CompteBancaire compte = foundCompte.get();

      try {
        compte.creerOperation(libelle, montant);
        return compte.getNbOperation();
      } catch (CompteException e) {
        throw new SystemeBancaireException(String.format(
            "Operation impossible: rib %s ; montant %s", ribCompte, montant));
      }

    } else {
      throw new SystemeBancaireException(
          String.format("Compte pas trouvé: rib %s", ribCompte));
    }
  }

  public Boolean verifierCodePin(String numCarteCompte, String codePin)
      throws SystemeBancaireException {
    Optional<CarteBancaire> foundCarte = this.mapCarteAPersonneCompteBancaire
        .keySet().stream()
        .filter(carte -> carte.getNumCarte().equals(numCarteCompte))
        .findFirst();

    if (foundCarte.isPresent()) {
      return foundCarte.get().verifierPin(codePin);
    } else {
      throw new SystemeBancaireException("Carte pas trouvé");
    }
  }

}
