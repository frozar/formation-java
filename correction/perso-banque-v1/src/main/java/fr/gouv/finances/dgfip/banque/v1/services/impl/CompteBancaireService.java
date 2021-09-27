package fr.gouv.finances.dgfip.banque.v1.services.impl;

import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v1.entites.Operation;
import fr.gouv.finances.dgfip.banque.v1.services.CompteBancaireServiceInterface;

@Component
public class CompteBancaireService implements CompteBancaireServiceInterface {

  @Override
  public Operation creerOperation(CompteBancaire compte, String libelle,
      Double montant) throws CompteException {
    return compte.creerOperation(libelle, montant);
  }

  @Override
  public void afficherSyntheseOperations(CompteBancaire compte) {
    compte.afficherSyntheseOperations();
  }

  @Override
  public double calculerInteret(CompteEpargne compte) {
    return compte.calculerInterets();
  }

}
