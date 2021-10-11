<html lang="en">
<head>
<title>Student Registration Form</title>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/form-dropdown" />
  </jsp:include>

  <div class="container">

    <form action="form-dropdown-confirmation">
      Prenom : <input type="text" name="prenom" />
      <br />
      <br />
      Nom : <input type="text" name="nom" />
      <br />
      <br />
      <select name="country">
        <option>Brazil</option>
        <option>France</option>
        <option>Germany</option>
        <option>India</option>
        <option>Turkey</option>
        <option>United Kingdom</option>
        <option>United State of America</option>
      </select>
      <br />
      <br />
      <input type="submit" value="Soumettre" />
    </form>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>