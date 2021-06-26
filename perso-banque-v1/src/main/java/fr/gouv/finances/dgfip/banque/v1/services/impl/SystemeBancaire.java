package fr.gouv.finances.dgfip.banque.v1.services.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireInterface;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;

@Component
public class SystemeBancaire implements SystemeBancaireInterface {

  @Override
  public List<String> rechercheRIBCompteCarte(Banque banque, String numCarte)
      throws SystemeBancaireException {
    return banque.rechercheRIBCompteCarte(numCarte);
  }

  @Override
  public Integer creerOperation(Banque banque, String ribCompte, String libelle,
      Double montant) throws SystemeBancaireException {
    return banque.creerOperation(ribCompte, libelle, montant);
  }

  @Override
  public Boolean verifierCodePin(Banque banque, String numCarte, String codePin)
      throws SystemeBancaireException {
    return banque.verifierCodePin(numCarte, codePin);
  }

}
