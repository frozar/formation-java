package fr.gouv.finances.dgfip.banque.v1.entites;

public class Personne
{
    private String prenom;
    private String nom;
    
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
    
    
}
