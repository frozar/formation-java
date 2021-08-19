# Lien de documentation

Exemple d'utilisation de JPA et h2, configuration de la DB h2, login/password :
[https://spring.io/guides/gs/accessing-data-jpa/](https://spring.io/guides/gs/accessing-data-jpa/)

Mettre en place l'API REST :  
[https://spring.io/guides/gs/accessing-data-rest/](https://spring.io/guides/gs/accessing-data-rest/)

Documentation sur JPA :
[https://www.jmdoudoux.fr/java/dej/chap-jpa.htm](https://www.jmdoudoux.fr/java/dej/chap-jpa.htm)

Vidéo sur JPA :
[https://www.youtube.com/watch?v=WyF4CIHsHfA&list=PLzzeuFUy_CnhVfJIKyc3okTiiCc0anutx&ab_channel=cours-en-ligne](https://www.youtube.com/watch?v=WyF4CIHsHfA&list=PLzzeuFUy_CnhVfJIKyc3okTiiCc0anutx&ab_channel=cours-en-ligne)

# Console pour visualiser la DB h2

Aller sur l'adresse :  
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

Et se connecter avec le login/password renseigner dans :  
`src/main/resources/applications.properties`

# Remarque

Cette application utilise une API [hypermedia](https://spring.io/guides/gs/rest-hateoas), ce qui
permet de découvrir les différents end-points avec la commande curl. Par exemple :

```shell
curl http://localhost:8080/customer

{
  "_embedded" : {
    "customer" : [ {
      "firstName" : "Jack",
      "lastName" : "Bauer",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/customer/1"
        },
        "customer" : {
          "href" : "http://localhost:8080/customer/1"
        }
      }
    }, ... ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/customer"
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/customer"
    },
    "search" : {
      "href" : "http://localhost:8080/customer/search"
    }
  }
}
```

# Exemples de requêtes

Lister les customers :

```shell
curl http://localhost:8080/customer
```

Chercher des customers par nom de famille :

```shell
curl http://localhost:8080/customer/search/findByLastName?lastName=Bauer
curl http://localhost:8080/customer/search/findByLastName?lastName=dupont
```

Créer un nouveau customer :

```shell
curl -i -H "Content-Type:application/json" -d '{"firstName": "Frodo", "lastName": "Baggins"}' http://localhost:8080/customer
```

Mettre à jour un customer :

```shell
curl -X PUT -H "Content-Type:application/json" -d '{"firstName": "Bilbo", "lastName": "Baggins"}' http://localhost:8080/customer/6
curl -X PATCH -H "Content-Type:application/json" -d '{"firstName": "Bilbo Jr."}' http://localhost:8080/customer/6
```

Supprimer un customer :

```shell
curl -X DELETE http://localhost:8080/customer/6
```


