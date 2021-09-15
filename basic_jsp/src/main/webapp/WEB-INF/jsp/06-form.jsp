<html lang="en">
<head>
<title>Student Registration Form</title>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/form" />
  </jsp:include>

  <div class="container">

    <form action="form-confirmation">
      Prenom : <input type="text" name="prenom" />
      <br />
      <br />
      Nom : <input type="text" name="nom" />
      <br />
      <br />
      <input type="submit" value="Soumettre" />
    </form>

  </div>

  <jsp:include page="footer.jsp" />
</body>

</html>