package fr.gouv.finances.dgfip.banque.v1;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.gouv.finances.dgfip.banque.v1.entites.Banque;
import fr.gouv.finances.dgfip.banque.v1.entites.Personne;

@Service
public interface SystemeBancaireInterface {
	List<Personne> listeAdherent(Banque banque);
}
