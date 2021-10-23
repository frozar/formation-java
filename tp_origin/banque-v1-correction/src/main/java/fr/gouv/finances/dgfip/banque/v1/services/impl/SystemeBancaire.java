package fr.gouv.finances.dgfip.banque.v1.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.Operation;
import fr.gouv.finances.dgfip.banque.v1.services.CompteBancaireServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.SystemeBancaireInterface;

@Component
public class SystemeBancaire implements SystemeBancaireInterface
{
    private CompteBancaireServiceInterface compteBancaireService;
    
    @Override
    public List<String> rechercherRIBCompteCarte(Banque banque, String numCarte) throws SystemeBancaireException
    {
        CarteBancaire cb = banque.getCartes().get(numCarte);
        if(cb == null)
            throw new SystemeBancaireException("Carte inconnue par le système bancaire");
        return cb.getComptes().stream().map( c -> c.getRib()).collect(Collectors.toList());
        /*
        La ligne précédente est équivalent au code suivant :
            List<String> listeRIB = new ArrayList<String>();
            for(CompteCourant compte: cb.getComptes()) {
                listeRIB.add(compte.getRib());
            }
            return listeRIB;
        */
    }

    @Override
    public boolean verifierCodePin(Banque banque, String numCarte, String codePin) throws SystemeBancaireException
    {
        CarteBancaire cb = banque.getCartes().get(numCarte);
        if(cb == null)
            throw new SystemeBancaireException("Carte inconnue par le système bancaire");
        return cb.verifierPin(codePin);
    }

    @Override
    public int creerOperation(Banque banque, String ribCompte, String libelle, Double montant) throws SystemeBancaireException
    {
        CompteBancaire compte = banque.getComptes().get(ribCompte);
        if(!(compte instanceof CompteCourant))
            throw new SystemeBancaireException("Compte inconnu ou invalide");
        try {
            Operation ope = compteBancaireService.creerOperation(compte, libelle, -montant);
            return ope.getNumOperation();
        }
        catch(CompteException ce) {
            throw new SystemeBancaireException(ce.getMessage());
        }
    }

    @Autowired
    public void setCompteBancaireService(CompteBancaireServiceInterface compteBancaireService)
    {
        this.compteBancaireService = compteBancaireService;
    }


}
