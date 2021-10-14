package fr.gouv.finances.dgfip.banque.v1.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v1.entites.Operation;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.CompteBancaireServiceInterface;

@Service
public class CompteBancaireService implements CompteBancaireServiceInterface {

  @Override
  public Operation creerOperation(CompteBancaire compte, String libelle,
      Double montant) throws CompteException {
    if (compte instanceof CompteEpargne) {
      if (compte.calculerSolde() + montant < 0) {
        throw new CompteException("Operation impossible : solde negatif");
      }
    }
    int numeroOperation = compte.getNumeroOperation();
    Operation operation = new Operation(numeroOperation, new Date(), libelle,
        montant);
    compte.addOperation(operation);
    compte.setNumeroOperation(numeroOperation + 1);
    return operation;
  }

  @Override
  public void afficherSyntheseOperations(CompteBancaire compte) {
//    compte.afficherSyntheseOperations();

    System.out.println("");
    System.out.println("Synthèse du compte: " + compte.getCodeBanque() + " "
        + compte.getCodeGuichet() + " " + compte.getNumCompte() + " "
        + compte.getCle());
    Personne titulaire = compte.getTitulaire();
    System.out.println(
        "Titulaire: " + titulaire.getNom() + " " + titulaire.getPrenom());

    System.out.println(
        "+---------+-------------------------+-------------------------+------------+\n"
            + "| Num opé | Date opération          | Libellé                 | Montant    |\n"
            + "+---------+-------------------------+-------------------------+------------+");

    afficherSyntheseOperation(0, new Date(), "SOLDE INITIAL",
        compte.getSolde());

    for (Operation op : compte.getOperations()) {
      Integer numOperation = op.getNumOperation();
      Date dateOperation = op.getDateOperation();
      String libelle = op.getLibelle();
      Double montant = op.getMontant();

      afficherSyntheseOperation(numOperation, dateOperation, libelle, montant);
    }
    System.out.println(
        "+---------+-------------------------+-------------------------+------------+");
  }

  private void afficherSyntheseOperation(Integer numOperation,
      Date dateOperation, String libelle, Double montant) {
    String paddedNumOperation = String.format("%7d", numOperation);
    String date = new SimpleDateFormat("yyyy-MM-dd").format(dateOperation) + "T"
        + new SimpleDateFormat("HH:mm:ss").format(dateOperation);
    String paddedDate = String.format("%-23s", date);
    String paddedLibelle = String.format("%-23s", libelle);
    String paddedMontant = String.format("%10s", montant);
    System.out.println("| " + paddedNumOperation + " | " + paddedDate + " | "
        + paddedLibelle + " | " + paddedMontant + " |");
  }

  @Override
  public double calculerInteret(CompteEpargne compte) {
    return compte.calculerInterets();
  }

}
