package fr.gouv.finances.dgfip.banque.v2.entites;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Personne
{
    @Id
    @GeneratedValue
    private UUID id;
    private String prenom;
    private String nom;
    
    public Personne() {
        
    }
    
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public String getPrenom()
    {
        return prenom;
    }
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }
    public String getNom()
    {
        return nom;
    }
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    @Override
    public String toString()
    {
        return nom + " " + prenom;
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
