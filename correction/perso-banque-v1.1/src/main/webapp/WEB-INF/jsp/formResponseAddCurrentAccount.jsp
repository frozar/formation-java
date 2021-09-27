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

  <h3>
    <a href="/">Home</a>
  </h3>

</body>

</html>