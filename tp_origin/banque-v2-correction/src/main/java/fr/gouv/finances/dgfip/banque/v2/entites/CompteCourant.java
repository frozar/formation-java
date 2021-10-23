/*
 * Copyright (c) 2021 DGFiP - Tous droits réservés
 * 
 */
package fr.gouv.finances.dgfip.banque.v2.entites;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class CompteCourant extends CompteBancaire
{
    @ManyToMany(mappedBy = "comptes")
    private Set<CarteBancaire> cartes;
    
    public CompteCourant() {
        super();
    }
    
    public CompteCourant(Personne titulaire, String codeBanque, String codeGuichet, String numCompte, String cle)
    {
        super(titulaire, codeBanque, codeGuichet, numCompte, cle);
    }
}
