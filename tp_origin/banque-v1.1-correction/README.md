# TP de formation Java - V1.1

Ce TP a pour objectif de développer une gestion *basique* d'un système bancaire. La version *V1.1* permet d'aborder la mise en oeuvre des 
tests unitaires.

Le travail consiste à reprendre de travail de la *V1* et d'implémenter les classes et les scénarios de tests unitaires. A minima, les scénarios
de test doivent vérifier:

- les scénarios nominaux d'utilisation des différentes méthodes de la couche service
- les scénarios d'exception de la couche service

Des cas de tests plus fin pourront être rédigés pour tester la création des objets et les règles de gestion. Ci-dessous une exemple permettant 
de vérifier la création correcte d'une banque:

```
public class BanqueTests
{
    @Test
    void testCreerBanque() {
        Banque banque = new Banque("TEST");
        assertTrue(banque.getComptes().isEmpty());
        assertTrue(banque.getCartes().isEmpty());
        assertEquals(banque.getCodeBanque(), "TEST");
        assertEquals(banque.getNumCompte(), 0);
    }
}
```


**Bon courage**