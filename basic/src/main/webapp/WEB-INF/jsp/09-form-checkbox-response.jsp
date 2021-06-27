<html>

<head><title>Student Confirmation Form</title></head>

<body>

The student is confirmed: ${param.prenom} ${param.nom}
<br />
<br />
The student favorite language: 
<br />
<ul>
    <%
    String[] langs = request.getParameterValues("favoriteLanguage");
    
    for (String tempLang: langs) {
      out.println("<li>" + tempLang + "</li>");
    }
    %>
</ul>

</body>

</html>