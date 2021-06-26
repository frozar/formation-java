package fr.gouv.finances.dgfip.banque.v1;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v1.entites.Banque;

@Service
public interface SystemeBancaireInterface {
  List<String> rechercheRIBCompteCarte(Banque banque, String numCarte)
      throws SystemeBancaireException;

  Integer creerOperation(Banque banque, String ribCompte, String libelle,
      Double montant) throws SystemeBancaireException;

  Boolean verifierCodePin(Banque banque, String numCarte, String codePin)
      throws SystemeBancaireException;
}
