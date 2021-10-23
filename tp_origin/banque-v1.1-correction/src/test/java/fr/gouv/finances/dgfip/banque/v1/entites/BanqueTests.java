package fr.gouv.finances.dgfip.banque.v1.entites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BanqueTests
{
    @Test
    void testCreerBanque() {
        Banque banque = new Banque("TEST");
        assertTrue(banque.getComptes().isEmpty());
        assertTrue(banque.getCartes().isEmpty());
        assertEquals("TEST", banque.getCodeBanque());
        assertEquals(0, banque.getNumCompte());
    }
}
