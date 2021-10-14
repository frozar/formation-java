# TP de formation Java - V1.1

Ce TP a pour objectif de développer une gestion *basique* d'un système bancaire. La version *V1.1* permet d'aborder la mise en oeuvre des 
tests unitaires. Pour ce TP, il est également demandé d'initier l'implémentation de la couche de présentation (IHM).

## Implémentation des tests unitaires

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

Les tests unitaires peuvent être exécutés :
- depuis eclipse : clic-droit sur le projet > Run as > Maven test
- depuis la ligne de commande maven : `mvn test`


## Couche de présentation

Pour ce TP, il est demandé a minima de coder des pages JSP permettant d'obtenir des versions HTML des tableaux de synthèses.
Il est également possible de poursuivre l'implémentation en développant les IHM permettant de :
 - créer un compte courant ou un compte d'épargne
 - d'ajouter une opération à un compte existant
 - d'affiche la synthèse des comptes et des cartes

**Bon courage**