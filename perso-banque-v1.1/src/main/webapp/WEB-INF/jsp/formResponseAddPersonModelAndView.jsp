<html>

<head><title>Student Confirmation Form</title></head>

<body>

Nouvelle personne : <%= request.getParameter("prenom") %> <%= request.getParameter("nom") %> 
<!-- A shortcut version here. -->
<br />
<br />
Nouvelle personne : ${param.prenom} ${param.nom}
<br />
<br />

Nouvelle personne : ${personne.prenom} ${personne.nom}
<br />
<br />

<a href="/">Home</a>

</body>

</html>