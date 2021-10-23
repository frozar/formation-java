package fr.gouv.finances.dgfip.banque.v2.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v2.CompteException;
import fr.gouv.finances.dgfip.banque.v2.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v2.dao.CarteBancaireDao;
import fr.gouv.finances.dgfip.banque.v2.dao.CompteBancaireDao;
import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.Operation;
import fr.gouv.finances.dgfip.banque.v2.services.CompteBancaireServiceInterface;
import fr.gouv.finances.dgfip.banque.v2.services.SystemeBancaireInterface;

@Component
public class SystemeBancaire implements SystemeBancaireInterface
{
    private CompteBancaireServiceInterface compteBancaireService;
    private CarteBancaireDao carteBancaireDao;
    private CompteBancaireDao compteBancaireDao;
    
    @Override
    public List<String> rechercherRIBCompteCarte(Banque banque, String numCarte) throws SystemeBancaireException
    {
        
        CarteBancaire cb = carteBancaireDao.findByBanqueAndNumCarte(banque, numCarte);
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
        CarteBancaire cb = carteBancaireDao.findByBanqueAndNumCarte(banque, numCarte);
        if(cb == null)
            throw new SystemeBancaireException("Carte inconnue par le système bancaire");
        return cb.verifierPin(codePin);
    }

    @Override
    public int creerOperation(Banque banque, String ribCompte, String libelle, Double montant) throws SystemeBancaireException
    {
        CompteBancaire compte = null;
        for (CompteBancaire c: compteBancaireDao.findByBanque(banque)) {
            if(c.getRib().equals(ribCompte)) {
                compte = c;
                break;
            }
        }
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

    @Autowired
    public void setCarteBancaireDao(CarteBancaireDao carteBancaireDao)
    {
        this.carteBancaireDao = carteBancaireDao;
    }

    @Autowired
    public void setCompteBancaireDao(CompteBancaireDao compteBancaireDao)
    {
        this.compteBancaireDao = compteBancaireDao;
    }


}
