package fr.gouv.finances.dgfip.banque.v1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class Banque implements SystemeBancaireInterface {

  /********************************/
  private final String codeBanque;

  /********************************/
  private Set<CompteBancaire> setCompteBancaire = new HashSet<CompteBancaire>();
  private Set<CarteBancaire> setCarte = new HashSet<CarteBancaire>();

  private int numeroCompte = 0;

  /********************************/
  public Banque(String codeBanque) {
    this.codeBanque = codeBanque;
  }

  public CompteCourant creerCompteCourant(Personne titulaire,
      String codeGuichet, Double soldeInitial) throws CompteException {
    String numComptePadded = String.format("%010d", numeroCompte);
    numeroCompte += 1;
    String cle = "99";
    CompteCourant newCompteCourant = new CompteCourant(this.codeBanque,
        codeGuichet, numComptePadded, cle, soldeInitial);
    newCompteCourant.setBanque(this);
    newCompteCourant.setTitulaire(titulaire);
    setCompteBancaire.add(newCompteCourant);
    titulaire.addCompteBancaire(newCompteCourant);
    return newCompteCourant;
  }

  /********************************/
  public void addCarte(CarteBancaire newCarteBancaire) {
    setCarte.add(newCarteBancaire);
  }

  public CompteCourant creerCompteCourant(Personne titulaire,
      String codeGuichet) throws CompteException {
    return this.creerCompteCourant(titulaire, codeGuichet, 0.0);
  }

  public CompteEpargne creerCompteEpargne(Personne titulaire,
      String codeGuichet, Double soldeInitial, Double taux) {
    String numComptePadded = String.format("%010d", numeroCompte);
    numeroCompte += 1;
    String cle = "99";
    CompteEpargne newCompteEpargne = new CompteEpargne(this.codeBanque,
        codeGuichet, numComptePadded, cle, 0.0, taux);
    newCompteEpargne.setBanque(this);
    newCompteEpargne.setTitulaire(titulaire);
    setCompteBancaire.add(newCompteEpargne);
    titulaire.addCompteBancaire(newCompteEpargne);
    return newCompteEpargne;
  }

  public CarteBancaire creerCarte(Personne titulaire,
      CompteCourant compteCourant) {
    List<String> randStr = new ArrayList<String>();
    for (int nb : new Random().ints(4, 0, 10000).toArray()) {
      randStr.add(String.format("%04d", nb));
    }
    String numCarte = String.join(" ", randStr);
    String codePin = "5613";
    Calendar calendar = new GregorianCalendar();
    int yearPlus3 = calendar.get(Calendar.YEAR) + 3;
    calendar.set(Calendar.YEAR, yearPlus3);
    Date dateExpiration = calendar.getTime();
    CarteBancaire newCarteBancaire = new CarteBancaire(codePin, numCarte,
        dateExpiration);
    newCarteBancaire.setBanque(this);
    newCarteBancaire.setCompteCourant(compteCourant);
    newCarteBancaire.setTitulaire(titulaire);

    titulaire.addCarte(newCarteBancaire);
    compteCourant.addCarte(newCarteBancaire);
    this.addCarte(newCarteBancaire);
    return newCarteBancaire;
  }

  public CarteBancaire lierCarte(CarteBancaire carte,
      CompteCourant compteCourant) {
    Personne titulaire = compteCourant.getTitulaire();

    carte.setBanque(this);
    carte.setCompteCourant(compteCourant);
    carte.setTitulaire(titulaire);

    titulaire.addCarte(carte);
    compteCourant.addCarte(carte);
    this.addCarte(carte);
    return carte;
  }

  /********************************/
  public void afficherSyntheseComptes() {
    System.out.println(
        "+-----------------+--------------------------+----------------------+------------+\n"
            + "| Type compte     | RIB                      | Titulaire            | Solde      |\n"
            + "+-----------------+--------------------------+----------------------+------------+");

    for (CompteBancaire compte : setCompteBancaire) {
      Personne titulaire = compte.getTitulaire();
      String compteType = compte instanceof CompteCourant ? "Compte courant"
          : "Compte epargne";
      String paddedCompteType = String.format("%-15s", compteType);
      String paddedRib = compte.getRib();
      String fullName = titulaire.getNom() + " " + titulaire.getPrenom();
      String paddedFullName = String.format("%-20s", fullName);
      String paddedSolde = String.format("%10.2f", compte.calculerSolde());
      System.out.println("| " + paddedCompteType + " | " + paddedRib + " | "
          + paddedFullName + " | " + paddedSolde + " |");
    }
    System.out.println(
        "+-----------------+--------------------------+----------------------+------------+");
  }

  public void afficherSyntheseCartes() {
    System.out
        .println("+----------------------+----------------------+----------+\n"
            + "| Titulaire            | Num. Carte           | Code PIN |\n"
            + "+----------------------+----------------------+----------+");

    for (CarteBancaire carte : setCarte) {
      Personne titulaire = carte.getTitulaire();
      String fullName = titulaire.getNom() + " " + titulaire.getPrenom();
      String paddedFullName = String.format("%-21s", fullName);
      String paddedNumCarte = String.format("%-21s", carte.getNumCarte());
      String paddedPin = String.format("%-8s", carte.getCodePin());

      System.out.println("| " + paddedFullName + "| " + paddedNumCarte + "| "
          + paddedPin + " |");
    }
    System.out
        .println("+----------------------+----------------------+----------+");
  }

  /********************************/
  public String rechercheRIBCompteCarte(String numCarte)
      throws SystemeBancaireException {
    Optional<CarteBancaire> foundCarte = this.setCarte.stream()
        .filter(carte -> carte.getNumCarte().equals(numCarte)).findFirst();
    if (foundCarte.isPresent()) {
      String rib = foundCarte.get().getCompteCourant().getRib();
      return rib;
    } else {
      throw new SystemeBancaireException("Carte pas trouv??");
    }
  }

  public Integer creerOperation(String ribCompte, String libelle,
      Double montant) throws SystemeBancaireException {
    Optional<CompteBancaire> foundCompte = this.setCompteBancaire.stream()
        .filter(compte -> compte.getRib().equals(ribCompte)).findFirst();
    if (foundCompte.isPresent()) {
      CompteBancaire compte = foundCompte.get();

      try {
        return compte.creerOperation(libelle, montant);
      } catch (CompteException e) {
        throw new SystemeBancaireException(String.format(
            "Operation impossible: rib %s ; montant %s", ribCompte, montant));
      }

    } else {
      throw new SystemeBancaireException(
          String.format("Compte pas trouv??: rib %s", ribCompte));
    }
  }

  public Boolean verifierCodePin(String numCarte, String codePin)
      throws SystemeBancaireException {
    Optional<CarteBancaire> foundCarte = this.setCarte.stream()
        .filter(carte -> carte.getNumCarte().equals(numCarte)).findFirst();
    if (foundCarte.isPresent()) {
      return foundCarte.get().verifierPin(codePin);
    } else {
      throw new SystemeBancaireException("Carte pas trouv??");
    }
  }
}
