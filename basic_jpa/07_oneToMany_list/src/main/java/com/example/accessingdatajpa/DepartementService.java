package com.example.accessingdatajpa;

public interface DepartementService {
  public Departement addCommune(String departement, String commune);

  public Departement addCommuneHead(String departement, String commune);

  public Departement deleteCommune(String departement, String commune);
}
