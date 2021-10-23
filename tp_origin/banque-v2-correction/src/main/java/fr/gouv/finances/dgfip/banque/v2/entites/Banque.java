package fr.gouv.finances.dgfip.banque.v2.entites;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Banque
{
    @Id
    @GeneratedValue
    private UUID id;

    private String codeBanque;
    private int numCompte;

    public Banque() {
        this.numCompte = 0;
    }

    public Banque(String codeBanque) {
        this();
        this.codeBanque = codeBanque;
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

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }
}
