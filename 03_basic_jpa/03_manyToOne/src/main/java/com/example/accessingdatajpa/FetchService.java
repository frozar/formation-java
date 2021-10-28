package com.example.accessingdatajpa;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Documentation link:
// https://stackoverflow.com/questions/53836776/lazyinitializationexception-spring-boot

@Service
public class FetchService implements FetchServiceInterface {

  @Autowired
  private DepartementRepository departementRepository;

  @Autowired
  private CommuneRepository communeRepository;

  private static final Logger log = LoggerFactory.getLogger(FetchService.class);

  @Override
  @Transactional
  public void fetchBehavior() {
    log.info("@Transactional context to support a fetch LAZY policy");
    Commune c = communeRepository.findById(2L);
    System.out.println("Commune " + c);
  }

  @Override
  @Transactional
  public void displayDB() {
    Iterable<Commune> communes = communeRepository.findAll();
    Iterable<Departement> departements = departementRepository.findAll();
    log.info("Liste des departements");
    for (Departement d : departements) {
      log.info(" - " + d);
    }
    log.info("Liste des communes");
    for (Commune c : communes) {
      log.info(" - " + c.toString());
    }
    log.info("");
  }

}
