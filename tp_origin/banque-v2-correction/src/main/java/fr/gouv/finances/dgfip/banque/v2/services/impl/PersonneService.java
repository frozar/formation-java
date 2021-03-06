package fr.gouv.finances.dgfip.banque.v2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v2.dao.PersonneDao;
import fr.gouv.finances.dgfip.banque.v2.entites.Personne;
import fr.gouv.finances.dgfip.banque.v2.services.PersonneServiceInterface;

@Service
public class PersonneService implements PersonneServiceInterface
{
    private PersonneDao personneDao;
    
    public Personne creerPersonne(String nom, String prenom) {
        return personneDao.save(new Personne(nom, prenom));
    }

    @Autowired
    public void setPersonneDao(PersonneDao personneDao)
    {
        this.personneDao = personneDao;
    }
    
}
