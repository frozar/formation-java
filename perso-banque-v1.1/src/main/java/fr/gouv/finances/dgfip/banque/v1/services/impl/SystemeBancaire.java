package fr.gouv.finances.dgfip.banque.v1.services.impl;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.SystemeBancaireInterface;

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

  @Override
  public List<Personne> listeAdherent(Banque banque) {
    Set<Personne> persons = banque.getMapCompteAPersonne().values().stream()
        .collect(toSet());
    for (Personne person : persons) {
      System.out.println(person);
    }
    return new ArrayList<>(persons);

  }

}
