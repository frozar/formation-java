<html>

<head><title>Student Confirmation Form</title></head>

<body>

Nouvelle personne : <%= request.getParameter("prenom") %> <%= request.getParameter("nom") %> 
<!-- A shortcut version here. -->
<br />
<br />
Nouvelle personne : ${param.prenom} ${param.nom}

</body>

</html>