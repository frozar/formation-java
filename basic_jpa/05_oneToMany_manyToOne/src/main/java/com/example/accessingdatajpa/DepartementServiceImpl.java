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

  public Departement addCommune(String departement, String commune) {
    Departement dep = departementRepository.findByNom(departement);
    if (dep == null)
      return null;
    Commune com = new Commune(commune);
    com.setDepartement(dep);
    communeRepository.save(com);
    dep.getCommunes().add(com);
    departementRepository.save(dep);
    return dep;
  }

  public Departement deleteCommune(String departement, String commune) {
    Departement dep = departementRepository.findByNom(departement);
    if (dep == null)
      return null;

    Set<Commune> res = dep.getCommunes().stream()
        .filter(c -> !c.getNom().equals(commune)).collect(Collectors.toSet());
    dep.setCommunes(res);

    departementRepository.save(dep);
    return dep;
  }
}
