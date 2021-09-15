<html lang="en">
<head>
<title>Student Confirmation Form</title>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp" />

  <div class="container">

    The student is confirmed: ${param.prenom} ${param.nom}
    <br />
    <br />
    The student favorite language:
    <br />
    <ul>
      <%
      String[] langs = request.getParameterValues("favoriteLanguage");

      for (String tempLang : langs) {
        out.println("<li>" + tempLang + "</li>");
      }
      %>
    </ul>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>