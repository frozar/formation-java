<html lang="en">
<head>
<title>Student Confirmation Form</title>
<jsp:include page="header.jsp" />
</head>
<body>

  <jsp:include page="nav-bar.jsp" />

  <div class="container">

    The student is confirmed:
    <%=request.getParameter("prenom")%>
    <%=request.getParameter("nom")%>
    <!-- A shortcut version here. -->
    <br />
    <br />
    The student is confirmed: ${param.prenom} ${param.nom}

  </div>

  <jsp:include page="footer.jsp" />
</body>

</html>