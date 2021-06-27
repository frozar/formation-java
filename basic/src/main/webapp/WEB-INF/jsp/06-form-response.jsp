<html>

<head><title>Student Confirmation Form</title></head>

<body>

The student is confirmed: <%= request.getParameter("firstName") %> <%= request.getParameter("lastName") %> 
<!-- A shortcut version here. -->
The student is confirmed: ${param.prenom} ${param.nom}

</body>

</html>