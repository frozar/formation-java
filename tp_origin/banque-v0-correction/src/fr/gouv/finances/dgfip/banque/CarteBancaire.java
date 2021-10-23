package fr.gouv.finances.dgfip.banque;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CarteBancaire
{
    private String codePin;
    private String numCarte;
    private ZonedDateTime dateExpiration;
    private Map<String, CompteCourant> comptes;
    private Personne titulaire;
    
    public CarteBancaire(Personne titulaire) {
        Random random = new Random();
        this.dateExpiration = ZonedDateTime.now().plusYears(3L);
        this.codePin = String.format("%04d",  random.nextInt(9999));
        this.numCarte = String.format("%04d %04d %04d %04d",random.nextInt(9999),random.nextInt(9999),random.nextInt(9999),random.nextInt(9999));
        this.titulaire = titulaire;
        this.comptes = new HashMap<String, CompteCourant>();
    }

    public boolean verifierPin(String codePin) {
        return this.codePin.equals(codePin);
    }
    
    public String getCodePin()
    {
        return codePin;
    }

    public String getNumCarte()
    {
        return numCarte;
    }

    public ZonedDateTime getDateExpiration()
    {
        return dateExpiration;
    }

    public Collection<CompteCourant> getComptes()
    {
        return comptes.values();
    }

    public void lierCompte(CompteCourant compte)
    {
        comptes.put(compte.getRib(), compte);
    }

    public Personne getTitulaire()
    {
        return titulaire;
    }
}
