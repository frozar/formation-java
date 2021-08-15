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

  <%-- 
  <p>
    On peut voir que l'entité "personne" est correctement renseigné (par
    le contrôleur) :
    <br />
    Personne : ${personne.prenom} ${personne.nom}
    <br />
    Personne : ${personne}
  </p>

  <p>
    Par contre ce n'est pas le cas pour l'entité "compte-courant" :
    <br />
    Compte : ${compteCourant}
  </p>

  <p>toto : ${toto}</p>

  Nouveau compte :
  <br />
  Personne : ${personne.prenom} ${personne.nom}
  <br />
  Compte : ${compte-courant.codeBanque} ${compte-courant.codeGuichet}
  ${compte-courant.numCompte} ${compte-courant.cle}
  ${compte-courant.solde}
  <br />
  Compte : ${toto.codeBanque} ${toto.codeGuichet}
  ${compte-courant.numCompte} ${compte-courant.cle}
  ${compte-courant.solde}
  <br />
  Param : ${param}
  <br />
  Personne : ${personne}
  <br />
  Toto : ${toto}
 --%>
</body>

</html>