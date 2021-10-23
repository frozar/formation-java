package fr.gouv.finances.dgfip.banque;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.gouv.finances.dgfip.gabier.SystemeBancaireInterface;
import fr.gouv.finances.dgfip.gabier.SystemeBancaireException;

public class Banque implements SystemeBancaireInterface
{
    private Map<String, CompteBancaire> comptes;
    private Map<String, CarteBancaire> cartes;
    private String codeBanque;
    private int numCompte;
    
    public Banque() {
        this.comptes = new HashMap<String, CompteBancaire>();
        this.cartes = new HashMap<String, CarteBancaire>();
        this.numCompte = 0;
    }

    
    public Banque(String codeBanque) {
        this();
        this.codeBanque = codeBanque;
    }
    
    private void initCompte(CompteBancaire compte) {
        comptes.put(compte.getRib(), compte);
        numCompte += 1;
    }
    
    public CompteCourant creerCompteCourant(Personne titulaire, String guichet, Double soldeInitial) throws CompteException {
        if(titulaire == null)
            throw new IllegalArgumentException("Le titulaire du compte est obligatoire");
        CompteCourant newCompte = new CompteCourant(titulaire, codeBanque, guichet, String.format("%010d", numCompte), "99");
        newCompte.creerOperation("SOLDE INITIAL", soldeInitial);
        initCompte(newCompte);
        return newCompte;
    }
    
    public CompteCourant creerCompteCourant(Personne titulaire, String guichet) throws CompteException {
        return creerCompteCourant(titulaire, guichet, 0.0);
    }

    public CompteEpargne creerCompteEpargne(Personne titulaire, String guichet, Double soldeInitial, Double taux) throws CompteException {
        if(titulaire == null)
            throw new IllegalArgumentException("Le titulaire du compte est obligatoire");
        CompteEpargne newCompte = new CompteEpargne(titulaire, codeBanque, guichet, String.format("%010d", numCompte), "99", taux);
        newCompte.creerOperation("SOLDE INITIAL", soldeInitial);
        initCompte(newCompte);
        return newCompte;
    }

    public CarteBancaire creerCarte(Personne titulaire, CompteCourant compte) throws CompteException {
        CarteBancaire cb = new CarteBancaire(titulaire);
        cb.lierCompte(compte);
        cartes.put(cb.getNumCarte(), cb);
        return cb;
    }
    
    public CarteBancaire lierCarteCompte(CarteBancaire cb, CompteCourant compte) {
        cb.lierCompte(compte);
        cartes.put(cb.getNumCarte(), cb);
        return cb;
    }
    
    public void afficherSyntheseComptes() {
        String leftAlignFormat = "| %-15s | %-20s | %-20s | %10.2f |%n";

        System.out.format("+-----------------+--------------------------+----------------------+------------+%n");
        System.out.format("| Type compte     | RIB                      | Titulaire            | Solde      |%n");
        System.out.format("+-----------------+--------------------------+----------------------+------------+%n");
        for (CompteBancaire compte : comptes.values()) {
            String typeCompte=null;
            if(compte instanceof CompteCourant)
                typeCompte = "Compte courant";
            else if(compte instanceof CompteEpargne)
                typeCompte = "Compte epargne";
            System.out.format(leftAlignFormat, typeCompte, compte.getRib(), compte.getTitulaire(), compte.getSolde());
        }
        System.out.format("+-----------------+--------------------------+----------------------+------------+%n");
    }

    public void afficherSyntheseCartes() {
        String leftAlignFormat = "| %-20s | %-20s | %-8s |%n";

        System.out.format("+----------------------+----------------------+----------+%n");
        System.out.format("| Titulaire            | Num. Carte           | Code PIN |%n");
        System.out.format("+----------------------+----------------------+----------+%n");
        for (CarteBancaire carte : cartes.values()) {
            System.out.format(leftAlignFormat, carte.getTitulaire(), carte.getNumCarte(), carte.getCodePin());
        }
        System.out.format("+----------------------+----------------------+----------+%n");
    }

    @Override
    public List<String> rechercherRIBCompteCarte(String numCarte) throws SystemeBancaireException
    {
        CarteBancaire cb = cartes.get(numCarte);
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
    public boolean verifierCodePin(String numCarte, String codePin) throws SystemeBancaireException
    {
        CarteBancaire cb = cartes.get(numCarte);
        if(cb == null)
            throw new SystemeBancaireException("Carte inconnue par le système bancaire");
        return cb.verifierPin(codePin);
    }

    @Override
    public int creerOperation(String ribCompte, String libelle, Double montant) throws SystemeBancaireException
    {
        CompteBancaire compte = comptes.get(ribCompte);
        if(!(compte instanceof CompteCourant))
            throw new SystemeBancaireException("Compte inconnu ou invalide");
        try {
            Operation ope = compte.creerOperation(libelle, -montant);
            return ope.getNumOperation();
        }
        catch(CompteException ce) {
            throw new SystemeBancaireException(ce.getMessage());
        }
    }

    public Collection<CompteBancaire> getComptes()
    {
        return comptes.values();
    }

    public String getCodeBanque()
    {
        return codeBanque;
    }

    public Collection<CarteBancaire> getCartes()
    {
        return cartes.values();
    }
}
