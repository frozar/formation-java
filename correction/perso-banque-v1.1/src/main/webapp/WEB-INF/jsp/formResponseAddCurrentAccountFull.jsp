<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<title>Compte courant créé</title>
<style type="text/css">
.error {
    color: red;
    font-style: italic;
}
</style>
</head>

<body>

  <h1>Nouveau compte courant</h1>
  <p>
    Type compte : Compte courant
    <br />
    RIB : ${compteCourant.codeBanque} ${compteCourant.codeGuichet}
    ${compteCourant.numCompte} ${compteCourant.cle}
    <br />
    Titulaire : ${personne.nom} ${personne.prenom}
    <br />
    Solde : ${compteCourant.solde}
  </p>


  <h2>A but pédagogique : détail technique</h2>
  <p>
    Tous les champs qui sont binded à cette vue sont disponible dans la
    variable "param" :
    <br />
    ${param}
  </p>
  
  <h3><a href="/">Home</a></h3>
</body>

</html>