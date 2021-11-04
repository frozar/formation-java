package fr.gouv.finances.dgfip.banque.v2.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v2.CompteException;
import fr.gouv.finances.dgfip.banque.v2.dao.BanqueDao;
import fr.gouv.finances.dgfip.banque.v2.dao.CarteBancaireDao;
import fr.gouv.finances.dgfip.banque.v2.dao.CompteBancaireDao;
import fr.gouv.finances.dgfip.banque.v2.dao.PersonneDao;
import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;
import fr.gouv.finances.dgfip.banque.v2.services.BanqueServiceInterface;

@Service
public class BanqueService implements BanqueServiceInterface {

  @Autowired
  private BanqueDao banqueDao;
  @Autowired
  private CompteBancaireDao compteBancaireDao;
  @Autowired
  private CarteBancaireDao carteBancaireDao;
  @Autowired
  private PersonneDao personneDao;

  @Override
  public List<Banque> listeBanque() {
    return (List<Banque>) banqueDao.findAll();
  }

  @Override
  public Banque creerBanque(String nomBanque) {
    return banqueDao.save(new Banque(nomBanque));
  }

  @Override
  public Banque getBanque(String nomBanque) {
    Banque banqueInDB = banqueDao.findByCodeBanque(nomBanque);
    if (banqueInDB != null) {
      banqueInDB.getSetCompteBancaire();
      banqueInDB.getSetCarte();
    }
    return banqueInDB;
  }

  @Override
  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String codeGuichet, Double soldeInitial) throws CompteException {

    int numeroCompte = banque.getNumeroCompte();
    String numComptePadded = String.format("%010d", numeroCompte);
    banque.setNumeroCompte(numeroCompte + 1);
    String cle = "99";
    CompteCourant newCompteCourant = new CompteCourant(banque.getCodeBanque(),
        codeGuichet, numComptePadded, cle, soldeInitial);
    newCompteCourant.setBanque(banque);
    newCompteCourant.setTitulaire(titulaire);
    banque.addCompteBancaire(newCompteCourant);
    titulaire.addCompteBancaire(newCompteCourant);
    return compteBancaireDao.save(newCompteCourant);
  }

  @Override
  public CompteCourant creerCompteCourant(Banque banque, Personne titulaire,
      String guichet) throws CompteException {
    return this.creerCompteCourant(banque, titulaire, guichet, 0.0);
  }

  @Override
  public CompteEpargne creerCompteEpargne(Banque banque, Personne titulaire,
      String codeGuichet, Double soldeInitial, Double taux)
      throws CompteException {

    int numeroCompte = banque.getNumeroCompte();
    String numComptePadded = String.format("%010d", numeroCompte);
    numeroCompte += 1;
    String cle = "99";
    CompteEpargne newCompteEpargne = new CompteEpargne(banque.getCodeBanque(),
        codeGuichet, numComptePadded, cle, soldeInitial, taux);
    newCompteEpargne.setBanque(banque);
    newCompteEpargne.setTitulaire(titulaire);
    banque.addCompteBancaire(newCompteEpargne);
    titulaire.addCompteBancaire(newCompteEpargne);
    return compteBancaireDao.save(newCompteEpargne);
  }

  @Override
  public CompteBancaire getCompteBancaire(String numCompte) {
    return compteBancaireDao.findByNumCompte(numCompte);
  }

  @Override
  public CompteBancaire updateCompteBancaire(String numCompte,
      Personne titulaire) {

    Personne personneInDB = personneDao.findByNomAndPrenom(titulaire.getNom(),
        titulaire.getPrenom());
    if (personneInDB == null) {
      personneInDB = personneDao.save(titulaire);
    }

    CompteBancaire compteBancaire = getCompteBancaire(numCompte);
    compteBancaire.setTitulaire(personneInDB);
    return compteBancaireDao.save(compteBancaire);
  }

  @Override
  @Transactional
  public Banque deleteCompteBancaire(String numCompte) {

    CompteBancaire compteBancaire = getCompteBancaire(numCompte);
    if (compteBancaire != null) {
      if (compteBancaire instanceof CompteCourant) {
        CompteCourant compteCourant = (CompteCourant) compteBancaire;
        carteBancaireDao.deleteAll(compteCourant.getSetCarte());
      }
      Banque banque = compteBancaire.getBanque();
      compteBancaireDao.delete(compteBancaire);

      banque.getSetCompteBancaire();
      return banque;
    }
    return null;
  }

  @Override
  public CarteBancaire creerCarte(Banque banque, Personne titulaire,
      CompteCourant compteCourant) throws CompteException {

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
    newCarteBancaire.setBanque(banque);
    newCarteBancaire.setCompteCourant(compteCourant);
    newCarteBancaire.setTitulaire(titulaire);

    CarteBancaire carteBancaireInDB = carteBancaireDao.save(newCarteBancaire);

    titulaire.addCarte(carteBancaireInDB);
    compteCourant.addCarte(carteBancaireInDB);
    banque.addCarte(carteBancaireInDB);
    personneDao.save(titulaire);
    compteBancaireDao.save(compteCourant);
    banque = banqueDao.save(banque);

    return carteBancaireInDB;

  }

  @Override
  public void afficherSyntheseComptes(Banque banque) {

    System.out.println(
        "+-----------------+--------------------------+----------------------+------------+\n"
            + "| Type compte     | RIB                      | Titulaire            | Solde      |\n"
            + "+-----------------+--------------------------+----------------------+------------+");

    System.out.print(afficherSyntheseComptesString(banque));
    System.out.println(
        "+-----------------+--------------------------+----------------------+------------+");
  }

  public String afficherSyntheseComptesString(Banque banque) {
    String res = "";
    for (CompteBancaire compte : banque.getSetCompteBancaire()) {
      Personne titulaire = compte.getTitulaire();
      String compteType = compte instanceof CompteCourant ? "Compte courant"
          : "Compte epargne";
      String paddedCompteType = String.format("%-15s", compteType);
      String paddedRib = compte.getRib();
      String fullName = titulaire.getNom() + " " + titulaire.getPrenom();
      String paddedFullName = String.format("%-20s", fullName);
      String paddedSolde = String.format("%10.2f", compte.calculerSolde());
      res += "| " + paddedCompteType + " | " + paddedRib + " | "
          + paddedFullName + " | " + paddedSolde + " |\n";
    }
    return res;
  }

  @Override
  public void afficherSyntheseCartes(Banque banque) {
    System.out
        .println("+----------------------+----------------------+----------+\n"
            + "| Titulaire            | Num. Carte           | Code PIN |\n"
            + "+----------------------+----------------------+----------+");

    for (CarteBancaire carte : banque.getSetCarte()) {
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

  public CarteBancaire lierCarte(Banque banque, CarteBancaire carte,
      CompteCourant compteCourant) {
    Personne titulaire = compteCourant.getTitulaire();

    carte.setBanque(banque);
    carte.setCompteCourant(compteCourant);
    carte.setTitulaire(titulaire);

    titulaire.addCarte(carte);
    compteCourant.addCarte(carte);
    banque.addCarte(carte);
    return carte;
  }

  @Override
  public void deleteAllBanque() {
    banqueDao.deleteAll();

  }

  @Override
  public void deleteAllCompteBancaire() {
    compteBancaireDao.deleteAll();
  }

  @Override
  public void deleteAllCartebancaire() {
    carteBancaireDao.deleteAll();
  }

}
