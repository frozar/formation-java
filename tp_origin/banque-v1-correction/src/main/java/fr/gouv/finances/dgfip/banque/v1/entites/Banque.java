package fr.gouv.finances.dgfip.banque.v1.entites;

import java.util.HashMap;
import java.util.Map;

public class Banque
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

    public void ajouterCompte(CompteBancaire compte) {
        comptes.put(compte.getRib(), compte);
        numCompte += 1;
    }
    
    public Banque(String codeBanque) {
        this();
        this.codeBanque = codeBanque;
    }

    public Map<String, CompteBancaire> getComptes()
    {
        return comptes;
    }

    public void setComptes(Map<String, CompteBancaire> comptes)
    {
        this.comptes = comptes;
    }

    public Map<String, CarteBancaire> getCartes()
    {
        return cartes;
    }

    public void setCartes(Map<String, CarteBancaire> cartes)
    {
        this.cartes = cartes;
    }

    public String getCodeBanque()
    {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque)
    {
        this.codeBanque = codeBanque;
    }

    public int getNumCompte()
    {
        return numCompte;
    }

    public void setNumCompte(int numCompte)
    {
        this.numCompte = numCompte;
    }
}
