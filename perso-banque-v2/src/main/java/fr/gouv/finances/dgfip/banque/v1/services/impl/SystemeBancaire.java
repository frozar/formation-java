package fr.gouv.finances.dgfip.banque.v1.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireException;
import fr.gouv.finances.dgfip.banque.v1.SystemeBancaireInterface;
import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.CompteBancaire;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;

@Component
public class SystemeBancaire implements SystemeBancaireInterface {

@Override
public List<Personne> listeAdherent(Banque banque) {
	// TODO Auto-generated method stub
//	HashMap<CompteBancaire, Personne> map = banque.getMapCompteAPersonne();
//	
//	return null;

    return banque.getMapCompteAPersonne().values().stream()
        .collect(Collectors.toCollection(ArrayList::new));
}

}