package fr.gouv.finances.dgfip.banque.v1.entites;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.gouv.finances.dgfip.banque.v2.entites.Banque;

public class BanqueTests
{
    @Test
    void testCreerBanque() {
        Banque banque = new Banque("TEST");
        assertEquals("TEST", banque.getCodeBanque());
        assertEquals(0, banque.getNumCompte());
    }
}
