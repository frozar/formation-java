package fr.gouv.finances.dgfip.banque.v3.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v3.dao.PersonneDao;
import fr.gouv.finances.dgfip.banque.v3.entites.Personne;
import fr.gouv.finances.dgfip.banque.v3.services.PersonneServiceInterface;

@Service
public class PersonneService implements PersonneServiceInterface {

  @Autowired
  private PersonneDao personneDao;

  @Override
  public Personne creerPersonne(String nom, String prenom) {
    return personneDao.save(new Personne(nom, prenom));
  }

  @Override
  public void deleteAllPersonne() {
    personneDao.deleteAll();
  }

}
