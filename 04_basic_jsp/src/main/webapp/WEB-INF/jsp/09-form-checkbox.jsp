<html lang="en">
<head>
<title>Student Registration Form</title>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/form-checkbox" />
  </jsp:include>

  <div class="container">

    <form action="form-checkbox-confirmation">
      Prenom : <input type="text" name="prenom" />
      <br />
      <br />
      Nom : <input type="text" name="nom" />
      <br />
      <br />

      Favorite Programming Language:

      <br />
      <br />

      <input type="checkbox" name="favoriteLanguage" value="Java">
      Java <input type="checkbox" name="favoriteLanguage" value="C#">
      C# <input type="checkbox" name="favoriteLanguage" value="PHP">
      PHP <input type="checkbox" name="favoriteLanguage" value="Ruby">
      Ruby

      <br />
      <br />

      <input type="submit" value="Soumettre" />
    </form>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>