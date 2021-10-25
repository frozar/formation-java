package com.example.accessingdatajpa;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchService implements FetchServiceInterface {

  @Autowired
  private CommuneRepository communeRepository;

  private static final Logger log = LoggerFactory.getLogger(FetchService.class);

  @Override
  @Transactional
  public void fetchBehavior() {
    log.info("@Transactional context to support a fetch LAZY policy");
    Commune c = communeRepository.findById(2L);
    System.out.println("Commune" + c);
  }

}
