package fr.gouv.finances.dgfip.banque.v2.entites;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class CarteBancaire
{
    @Id
    @GeneratedValue
    private UUID id;
    private String codePin;
    private String numCarte;
    private ZonedDateTime dateExpiration;
    @ManyToOne
    private Banque banque;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "cartes_comptes", 
        joinColumns = @JoinColumn(name = "carte_id"), 
        inverseJoinColumns = @JoinColumn(name = "compte_id"))
    private Set<CompteCourant> comptes;
    @ManyToOne
    private Personne titulaire;
    
    public CarteBancaire() {
        comptes = new HashSet<CompteCourant>();
    }
    
    public CarteBancaire(Personne titulaire) {
        this();
        Random random = new Random();
        this.dateExpiration = ZonedDateTime.now().plusYears(3L);
        this.codePin = String.format("%04d",  random.nextInt(9999));
        this.numCarte = String.format("%04d %04d %04d %04d",random.nextInt(9999),random.nextInt(9999),random.nextInt(9999),random.nextInt(9999));
        this.titulaire = titulaire;
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

    public Personne getTitulaire()
    {
        return titulaire;
    }

    public Banque getBanque()
    {
        return banque;
    }

    public void setBanque(Banque banque)
    {
        this.banque = banque;
    }

    public Set<CompteCourant> getComptes()
    {
        return comptes;
    }

    public void setComptes(Set<CompteCourant> comptes)
    {
        this.comptes = comptes;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }
}
