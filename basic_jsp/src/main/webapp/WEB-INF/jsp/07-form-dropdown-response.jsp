<html lang="en">
<head>
<title>Student Confirmation Form</title>
<jsp:include page="header.jsp" />
</head>

<body>

  <jsp:include page="nav-bar.jsp" />

  <div class="container">The student is confirmed: ${param.prenom}
    ${param.nom}, from ${param.country}.</div>

  <jsp:include page="footer.jsp" />
</body>

</html>