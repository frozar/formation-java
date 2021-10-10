# TP de formation Java - V1

Ce TP a pour objectif de développer une gestion *basique* d'un système bancaire. La version *V1* permet d'aborder le framework spring et 
les concepts d'injection des dépendances. Par rapport à la [version V0](https://forge.dgfip.finances.rie.gouv.fr/dgfip/esi974/formation-java/banque-v0) de ce projet, l'objectif est également de mettre en place une première ébauche de modèle en couches.

Le système bancaire à développer est représenté sur le modèle de classe suivant. Le travail consiste donc à implémenter les classes de ce
modèles (les *entités*), les liaisons entre ces classes ainsi que les différences composants *métier*.

![Modele de classe](./modele.png)

La branche `master`contient le squelette du projet. La branche `correction` fournit une correction type. Le squelette contient un fichier de configuration Maven nécessaire à la compilation de ce projet. Il n'est pas nécessaire d'intervenir sur ce fichier.

Pour les tests, la classe `BanqueV1Application` contient une fonction `main()` qui teste un certain nombre de cas d'utilisation.


## Exemples de cas d'utilisation

### Récupération d'une instance à un composant métier

Les composants métiers (`BanqueService`, `CompteBancaireService`, `PersonneService` et `SystemeBancaire`) sont développés
sous forme de composants au sens Spring, c'est-à-dire annoté avec l'annotation `@Component`. Ceci permet à Spring de gérer le cycle
de vie de ces composants et de les injecter partout où il fait à référence à une interface implémentée par ces composants.

En dehors du framework Spring (dans la fonction `main()` par exemple, l'accès à ces composants se fait par des appels direct au framework:

```
ApplicationContext context = SpringApplication.run(BanqueV1Application.class, args);
BanqueServiceInterface banqueService = context.getBean(BanqueServiceInterface.class);
PersonneServiceInterface personneService = context.getBean(PersonneServiceInterface.class);
CompteBancaireServiceInterface compteBancaireService = context.getBean(CompteBancaireServiceInterface.class);
```


### Création d'une banque, d'un titulaire et d'un compte courant

On créé une banque avec le code banque `DGFiP`, une personne titulaire et on lui créé un compte courant dans le guiche bancaire `1234`:

```
Banque maBanque = new Banque("DGFIP");
Personne paulette = personneService.creerPersonne("Blanchard", "Paulette");
CompteCourant ccPaulette = banqueService.creerCompteCourant(maBanque, paulette, "1234");
```

### Réalisation d'une opération de débit d'un compte

```
compteBancaireService.creerOperation(ccPaulette, "Dépôt chèque", 100.0); // Crédit
compteBancaireService.creerOperation(ccPaulette, "Intérêts débiteurs", -3.54); // Débit (montant négatif)
```

**Attention** : pour les comptes d'épargne (classe `CompteEpargne`) il n'est pas permis d'avoir un solde négatif sur le compte. Il s'agit
d'une règle de gestion à implémenter.

### Attributation d'une carte bancaire

```
CarteBancaire cbPaulette = banqueService.creerCarte(maBanque, paulette, ccPaulette);
```

### Utilisation du Gabier

Le `Gabier` est considéré comme un élément externe. Il n'interagit pas directement avec les classes du système bancaire mais propose une
interface `SystemeBancaireInterface`. Cette interface doit donc être implémenté par notre système bancaire afin de pouvoir s'interfacer
avec le Gabier.
La méthode `accesComptes` permet au gabier d'accéder aux comptes bancaires associés à la carte dont le numéro est fournit en argument.
L'accès n'est autorisé par le système bancaire uniquement si le codePIN fournit en argument correspond au code PIN de la carte.

```
Gabier gabier1 = context.getBean(Gabier.class); //Les dépendances nécessaires sont gérées par Spring

//Retrait de 314 sur le compte courant de Paulette
try
{
    List<String> comptesPaulette = gabier1.accesComptes(cbPaulette.getNumCarte(), cbPaulette.getCodePin());
    System.out.println("*** Liste de comptes de Paulette:");
    for(String rib: comptesPaulette) {
        System.out.println("  - " + rib);
    }
    int numOperation = gabier1.retirerEspeces(comptesPaulette.get(0), 314.0);
    System.out.format("*** Opération réalisée: %d  ***", numOperation);
    compteBancaireService.afficherSyntheseOperations(ccPaulette);
}
catch (SystemeBancaireException e)
{
    System.err.println("Accès impossible aux comptes de Paulette: " + e.getMessage());
}
```

## Affichage des synthèse

Afin de vérifier le bon fonctionnement du système bancaire, l'interface `BanqueServiceInterface` propose 2 méthodes permettant d'afficher la synthèse
des comptes et des cartes gérées par la banque. Ces méthodes doivent être implémentées par le composant ``BanqueService``.

### Exemple d'affichage de la synthèse des comptes `afficherSyntheseComptes()`

```
+-----------------+--------------------------+----------------------+------------+
| Type compte     | RIB                      | Titulaire            | Solde      |
+-----------------+--------------------------+----------------------+------------+
| Compte epargne  | DGFIP 5678 0000000003 99 | Labbe André          |   10000,00 |
| Compte courant  | DGFIP 5678 0000000001 99 | Guibert Dominique    |    -100,00 |
| Compte epargne  | DGFIP 1234 0000000002 99 | Guillou Thibault     |    4000,00 |
| Compte courant  | DGFIP 1234 0000000000 99 | Blanchard Paulette   |      50,00 |
+-----------------+--------------------------+----------------------+------------+
```

### Exemple d'affichage de la synthèse des cartes `afficherSyntheseCartes()`

```
+----------------------+----------------------+----------+
| Titulaire            | Num. Carte           | Code PIN |
+----------------------+----------------------+----------+
| Guibert Dominique    | 4328 1561 1140 6510  | 5613     |
| Blanchard Paulette   | 5870 7649 3910 6948  | 5088     |
+----------------------+----------------------+----------+
```

Sur le même principe l'interface `CompteBancaireServiceInterface` propose une méthode permettant de lister les opérations du compte.
Cette méthode doit être implémentée par le composant `CompteBancaireService`.

### Exemple d'affichage de la synthèse des cartes `afficherSyntheseOperations()`

```
Synthèse du compte: DGFIP 5678 0000000001 99
Titulaire: Guibert Dominique
+---------+-------------------------+-------------------------+------------+
| Num opé | Date opération          | Libellé                 | Montant    |
+---------+-------------------------+-------------------------+------------+
|       0 | 2021-05-23T09:33:19     | SOLDE INITIAL           |       0,00 |
|       1 | 2021-05-23T09:33:19     | Dépôt espèces           |     200,00 |
|       2 | 2021-05-23T09:33:19     | Débit                   |    -300,00 |
+---------+-------------------------+-------------------------+------------+
Solde:    -100,00
```


**Bon courage**