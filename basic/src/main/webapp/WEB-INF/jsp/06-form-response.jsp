<html>

<head><title>Student Confirmation Form</title></head>

<body>

The student is confirmed: <%= request.getParameter("prenom") %> <%= request.getParameter("nom") %> 
<!-- A shortcut version here. -->
<br />
<br />
The student is confirmed: ${param.prenom} ${param.nom}

</body>

</html>