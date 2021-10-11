package com.example.accessingdatajpa;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartementServiceImpl implements DepartementService {

  @Autowired
  private CommuneRepository communeRepository;

  @Autowired
  private DepartementRepository departementRepository;

  public Departement addCommune(String departementNom, String communeNom) {
    Departement departement = departementRepository.findByNom(departementNom);
    if (departement == null)
      return null;
    Commune commune = new Commune(communeNom);
    communeRepository.save(commune);
    departement.getCommunes().add(commune);
    departementRepository.save(departement);
    return departement;
  }

  public Departement deleteCommune(String departementNom, String communeNom) {
    Departement dep = departementRepository.findByNom(departementNom);
    if (dep == null)
      return null;

    Set<Commune> res = dep.getCommunes().stream()
        .filter(c -> !c.getNom().equals(communeNom))
        .collect(Collectors.toSet());
    dep.setCommunes(res);

    departementRepository.save(dep);
    return dep;
  }
}
