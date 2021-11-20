package fr.gouv.finances.dgfip.banque.v4.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import fr.gouv.finances.dgfip.banque.v4.entites.Banque;

public interface BanqueDao extends CrudRepository<Banque, UUID> {

  public Banque findByCodeBanque(String codeBanque);

}
