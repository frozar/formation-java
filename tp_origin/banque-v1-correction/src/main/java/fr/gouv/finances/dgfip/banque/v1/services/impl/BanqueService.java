package fr.gouv.finances.dgfip.banque.v1.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;
import fr.gouv.finances.dgfip.banque.v1.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v1.services.CompteBancaireServiceInterface;


@Component
public class BanqueService implements BanqueServiceInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BanqueService.class);
    private CompteBancaireServiceInterface compteBancaireService;
    
    public CompteCourant creerCompteCourant(Banque banque, Personne titulaire, String guichet, Double soldeInitial) throws CompteException {
        if(titulaire == null)
            throw new IllegalArgumentException("Le titulaire du compte est obligatoire");
        CompteCourant newCompte = new CompteCourant(titulaire, banque.getCodeBanque(), guichet, String.format("%010d", banque.getNumCompte()), "99");
        compteBancaireService.creerOperation(newCompte, "SOLDE INITIAL", soldeInitial);
        banque.ajouterCompte(newCompte);
        return newCompte;
    }
    
    public CompteCourant creerCompteCourant(Banque banque, Personne titulaire, String guichet) throws CompteException {
        return creerCompteCourant(banque, titulaire, guichet, 0.0);
    }

    public CompteEpargne creerCompteEpargne(Banque banque, Personne titulaire, String guichet, Double soldeInitial, Double taux) throws CompteException {
        if(titulaire == null)
            throw new IllegalArgumentException("Le titulaire du compte est obligatoire");
        CompteEpargne newCompte = new CompteEpargne(titulaire, banque.getCodeBanque(), guichet, String.format("%010d", banque.getNumCompte()), "99", taux);
        compteBancaireService.creerOperation(newCompte, "SOLDE INITIAL", soldeInitial);
        banque.ajouterCompte(newCompte);
        return newCompte;
    }

    public CarteBancaire creerCarte(Banque banque, Personne titulaire, CompteCourant compte) throws CompteException {
        CarteBancaire cb = new CarteBancaire(titulaire);
        cb.lierCompte(compte);
        banque.getCartes().put(cb.getNumCarte(), cb);
        return cb;
    }
    
    public CarteBancaire lierCarteCompte(Banque banque, CarteBancaire cb, CompteCourant compte) {
        cb.lierCompte(compte);
        banque.getCartes().put(cb.getNumCarte(), cb);
        return cb;
    }
    
    public void afficherSyntheseComptes(Banque banque) {

        String leftAlignFormat = "| %-15s | %-20s | %-20s | %10.2f |";

        LOGGER.info(String.format("+-----------------+--------------------------+----------------------+------------+"));
        LOGGER.info(String.format("| Type compte     | RIB                      | Titulaire            | Solde      |"));
        LOGGER.info(String.format("+-----------------+--------------------------+----------------------+------------+"));
        for (CompteBancaire compte : banque.getComptes().values()) {
            String typeCompte=null;
            if(compte instanceof CompteCourant)
                typeCompte = "Compte courant";
            else if(compte instanceof CompteEpargne)
                typeCompte = "Compte epargne";
            LOGGER.info(String.format(leftAlignFormat, typeCompte, compte.getRib(), compte.getTitulaire(), compte.getSolde()));
        }
        LOGGER.info(String.format("+-----------------+--------------------------+----------------------+------------+"));
    }

    public void afficherSyntheseCartes(Banque banque) {
        String leftAlignFormat = "| %-20s | %-20s | %-8s |";

        LOGGER.info(String.format("+----------------------+----------------------+----------+"));
        LOGGER.info(String.format("| Titulaire            | Num. Carte           | Code PIN |"));
        LOGGER.info(String.format("+----------------------+----------------------+----------+"));
        for (CarteBancaire carte : banque.getCartes().values()) {
            LOGGER.info(String.format(leftAlignFormat, carte.getTitulaire(), carte.getNumCarte(), carte.getCodePin()));
        }
        LOGGER.info(String.format("+----------------------+----------------------+----------+"));
    }

    @Autowired
    public void setCompteBancaireService(CompteBancaireServiceInterface compteBancaireService)
    {
        this.compteBancaireService = compteBancaireService;
    }
}
