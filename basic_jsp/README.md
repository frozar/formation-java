# Introduction

Ce projet présente les différentes interactions possible entre un controlleur
Spring Boot et des vues en JSP.

[Slide de présentation](https://slides.com/frozar/spring-boot-jsp)

## JSP

Les différents types de balises JSP sont les suivants :

```java
<%= expression java%>
```

```java
<% scriptlet java%>
```

```java
<%! declaration de function java%>
```

```java
<%@ directive d'import%>
```

Par exemple, pour importer la classe `Util` et la classe standard `List`:

```java
<%@ page import="com.example.basic.Utils,java.util.List"%>
```

## Spring-boot

Ce projet utilise principalement un controller pour dispatcher les différentes
pages de l'application.  
Les annotations Spring suivantes ont été utilisées :

| Annotation             | Description   |
| ---------------------- | ------------- |
| @SpringBootApplication | Se met au dessus de la classe main de l'app: déclenche la recherche de composants spring |
| @Controller | Se met au dessus de la classe qui gère les routes |
| @RequestMapping | Se met au dessus d'une méthode de controller. Prend en argument une route, peut gérer les différents types de request : GET, POST, PUT, PATCH, DELETE. |
| @GetMapping | Se met au dessus d'une méthode de controller. Prend en argument une route, gère les requêtes GET. |
| @RequestParam | Se met dans le prototype d'une méthode. Permet de récupérer les paramètres passés à dans une route. |

L'annotation Java utilisée :

| Annotation             | Description   |
| ---------------------- | ------------- |
| @Value | Se met au dessus d'un champ d'une classe. Permet d'injecter des valeurs de application.properties dans un champ d'une classe. |

# Documentation links

https://mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/

https://spring.io/guides/gs/serving-web-content/

https://www.youtube.com/watch?v=Wz-q2JzPP5U&list=PLEAQNNR8IlB588DQvb2wbKFQh2DviPApl&index=10&ab_channel=luv2code
