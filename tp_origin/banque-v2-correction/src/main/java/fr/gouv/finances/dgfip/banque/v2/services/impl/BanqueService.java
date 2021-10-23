package fr.gouv.finances.dgfip.banque.v2.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v2.CompteException;
import fr.gouv.finances.dgfip.banque.v2.dao.BanqueDao;
import fr.gouv.finances.dgfip.banque.v2.dao.CarteBancaireDao;
import fr.gouv.finances.dgfip.banque.v2.dao.CompteBancaireDao;
import fr.gouv.finances.dgfip.banque.v2.entites.Banque;
import fr.gouv.finances.dgfip.banque.v2.entites.CarteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteCourant;
import fr.gouv.finances.dgfip.banque.v2.entites.CompteEpargne;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;
import fr.gouv.finances.dgfip.banque.v2.services.BanqueServiceInterface;
import fr.gouv.finances.dgfip.banque.v2.services.CompteBancaireServiceInterface;


@Component
public class BanqueService implements BanqueServiceInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BanqueService.class);
    private CompteBancaireServiceInterface compteBancaireService;
    private BanqueDao banqueDao;
    private CompteBancaireDao compteBancaireDao;
    private CarteBancaireDao carteBancaireDao;
    
    public Banque creerBanque(String nomBanque) {
        return banqueDao.save(new Banque(nomBanque));
    }
    
    private Banque majNumCompte(Banque banque) {
        banque.setNumCompte(banque.getNumCompte() + 1);
        banqueDao.save(banque);
        return banque;
    }
    
    @Transactional
    public CompteCourant creerCompteCourant(Banque banque, Personne titulaire, String guichet, Double soldeInitial) throws CompteException {
        if(titulaire == null)
            throw new IllegalArgumentException("Le titulaire du compte est obligatoire");
        
        CompteCourant newCompte = new CompteCourant(titulaire, banque.getCodeBanque(), guichet, String.format("%010d", banque.getNumCompte()), "99");
        newCompte.setBanque(banque);
        compteBancaireDao.save(newCompte);
        majNumCompte(banque);
        compteBancaireService.creerOperation(newCompte, "SOLDE INITIAL", soldeInitial);
        return newCompte;
    }
    
    public CompteCourant creerCompteCourant(Banque banque, Personne titulaire, String guichet) throws CompteException {
        return creerCompteCourant(banque, titulaire, guichet, 0.0);
    }

    @Transactional
    public CompteEpargne creerCompteEpargne(Banque banque, Personne titulaire, String guichet, Double soldeInitial, Double taux) throws CompteException {
        if(titulaire == null)
            throw new IllegalArgumentException("Le titulaire du compte est obligatoire");
        CompteEpargne newCompte = new CompteEpargne(titulaire, banque.getCodeBanque(), guichet, String.format("%010d", banque.getNumCompte()), "99", taux);
        newCompte.setBanque(banque);
        compteBancaireDao.save(newCompte);
        majNumCompte(banque);
        compteBancaireService.creerOperation(newCompte, "SOLDE INITIAL", soldeInitial);
        return newCompte;
    }

    @Transactional
    public CarteBancaire creerCarte(Banque banque, Personne titulaire, CompteCourant compte) throws CompteException {
        Iterable<CompteBancaire> comptesIterator = compteBancaireDao.findByBanque(banque);
        List<CompteBancaire> comptes = StreamSupport.stream(comptesIterator.spliterator(), false)
            .filter(c -> c.getRib().equals(compte.getRib())).
            collect(Collectors.toList());
        if(comptes.isEmpty())
            throw new CompteException("Le compte " + compte.getRib() + " est inconnu dans la banque");

        CarteBancaire cb = new CarteBancaire(titulaire);
        cb.getComptes().add(compte);
        cb.setBanque(banque);
        carteBancaireDao.save(cb);
        return cb;
    }
    
    public void afficherSyntheseComptes(Banque banque) {

        String leftAlignFormat = "| %-15s | %-20s | %-20s | %10.2f |";

        LOGGER.info(String.format("+-----------------+--------------------------+----------------------+------------+"));
        LOGGER.info(String.format("| Type compte     | RIB                      | Titulaire            | Solde      |"));
        LOGGER.info(String.format("+-----------------+--------------------------+----------------------+------------+"));
        for (CompteBancaire compte : compteBancaireDao.findByBanque(banque)) {
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
        for (CarteBancaire carte : carteBancaireDao.findByBanque(banque)) {
            LOGGER.info(String.format(leftAlignFormat, carte.getTitulaire(), carte.getNumCarte(), carte.getCodePin()));
        }
        LOGGER.info(String.format("+----------------------+----------------------+----------+"));
    }

    @Autowired
    public void setCompteBancaireService(CompteBancaireServiceInterface compteBancaireService)
    {
        this.compteBancaireService = compteBancaireService;
    }

    @Autowired
    public void setBanqueDao(BanqueDao banqueDao)
    {
        this.banqueDao = banqueDao;
    }

    @Autowired
    public void setCompteBancaireDao(CompteBancaireDao compteBancaireDao)
    {
        this.compteBancaireDao = compteBancaireDao;
    }

    @Autowired
    public void setCarteBancaireDao(CarteBancaireDao carteBancaireDao)
    {
        this.carteBancaireDao = carteBancaireDao;
    }
}
