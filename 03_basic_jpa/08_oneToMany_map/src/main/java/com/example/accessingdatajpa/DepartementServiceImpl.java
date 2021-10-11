package com.example.accessingdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartementServiceImpl implements DepartementService {

  @Autowired
  private CommuneRepository communeRepository;

  @Autowired
  private DepartementRepository departementRepository;

  public Departement addCommune(String departementNom, String communeNom,
      Integer codePostal) {
    Departement departement = departementRepository.findByNom(departementNom);
    if (departement == null)
      return null;
    Commune commune = new Commune(communeNom);
    communeRepository.save(commune);
    departement.getCommunes().put(codePostal, commune);
    departementRepository.save(departement);
    return departement;
  }
}
