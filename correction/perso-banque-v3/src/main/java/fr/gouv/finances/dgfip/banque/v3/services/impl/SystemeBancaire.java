package fr.gouv.finances.dgfip.banque.v3.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v3.CompteException;
import fr.gouv.finances.dgfip.banque.v3.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v3.SystemeBancaireInterface;
import fr.gouv.finances.dgfip.banque.v3.entites.Banque;
import fr.gouv.finances.dgfip.banque.v3.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v3.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v3.entites.Operation;
import fr.gouv.finances.dgfip.banque.v3.services.CompteBancaireServiceInterface;

@Service
public class SystemeBancaire implements SystemeBancaireInterface {

  @Autowired
  private CompteBancaireServiceInterface compteBancaireService;

  @Override
  public String rechercheRIBCompteCarte(Banque banque, String numCarte)
      throws SystemeBancaireException {
    Optional<CarteBancaire> foundCarte = banque.getSetCarte().stream()
        .filter(carte -> carte.getNumCarte().equals(numCarte)).findFirst();
    if (foundCarte.isPresent()) {
      String rib = foundCarte.get().getCompteCourant().getRib();
      return rib;
    } else {
      throw new SystemeBancaireException("Carte pas trouvé");
    }
  }

  @Override
  public Operation creerOperation(Banque banque, String ribCompte,
      String libelle, Double montant) throws SystemeBancaireException {
    Optional<CompteBancaire> foundCompte = banque.getSetCompteBancaire()
        .stream().filter(compte -> compte.getRib().equals(ribCompte))
        .findFirst();
    if (foundCompte.isPresent()) {
      CompteBancaire compte = foundCompte.get();

      try {
        return compteBancaireService.creerOperation(compte, libelle, montant);
      } catch (CompteException e) {
        throw new SystemeBancaireException(String.format(
            "Operation impossible: rib %s ; montant %s", ribCompte, montant));
      }

    } else {
      throw new SystemeBancaireException(
          String.format("Compte pas trouvé: rib %s", ribCompte));
    }
  }

  @Override
  public Boolean verifierCodePin(Banque banque, String numCarte, String codePin)
      throws SystemeBancaireException {
    Optional<CarteBancaire> foundCarte = banque.getSetCarte().stream()
        .filter(carte -> carte.getNumCarte().equals(numCarte)).findFirst();
    if (foundCarte.isPresent()) {
      return foundCarte.get().verifierPin(codePin);
    } else {
      throw new SystemeBancaireException("Carte pas trouvé");
    }
  }

}
