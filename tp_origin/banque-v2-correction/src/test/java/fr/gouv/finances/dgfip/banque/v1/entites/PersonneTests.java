package fr.gouv.finances.dgfip.banque.v1.entites;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.gouv.finances.dgfip.banque.v2.entites.Personne;

public class PersonneTests
{
    @Test
    void testCreerPersonne() {
        Personne p = new Personne("Nom", "Prénom");
        assertEquals("Nom", p.getNom());
        assertEquals("Prénom", p.getPrenom());
    }
}
