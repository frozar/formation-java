package fr.gouv.finances.dgfip.banque.v1.entites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.gouv.finances.dgfip.banque.v1.services.PersonneServiceInterface;

@SpringBootTest
public class CarteBancaireTests
{
    @Autowired
    private PersonneServiceInterface personneService;
    
    @Test
    void testCreerCarteBancaire() {
        
        Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
        CarteBancaire carte = new CarteBancaire(paulette);
        assertEquals(paulette, carte.getTitulaire());
        assertTrue(carte.getComptes().isEmpty());
        assertNotNull(carte.getNumCarte());
        assertNotNull(carte.getDateExpiration());
        assertTrue(carte.getDateExpiration().isAfter(ZonedDateTime.now()));
    }
}
