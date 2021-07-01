package fr.gouv.finances.dgfip.banque.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.services.SystemeBancaireInterface;

@Component
public class Gabier {

  private SystemeBancaireInterface systemeBancaire;
  
  @Autowired
  public Gabier(SystemeBancaireInterface systemeBancaire) {
    this.systemeBancaire = systemeBancaire;
  }
  
  List<String> accesComptes(Banque banque, String numCarte, String codePin) throws SystemeBancaireException {
    if(systemeBancaire.verifierCodePin(banque, numCarte, codePin)) {      
      return systemeBancaire.rechercheRIBCompteCarte(banque, numCarte);
    }
    else {
      throw new SystemeBancaireException("Carte ne peut pas être vérifiée");
    }
  }

  int retirerEspeces(Banque banque, String ribCompte, Double montant) throws SystemeBancaireException {
    return systemeBancaire.creerOperation(banque, ribCompte, "Retrer gabier", montant);
  }
}
