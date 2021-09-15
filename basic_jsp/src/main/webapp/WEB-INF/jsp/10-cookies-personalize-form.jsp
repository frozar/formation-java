<html lang="en">
<head>
<title>Personalize The Site</title>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/cookies" />
  </jsp:include>

  <div class="container">

    <form action="cookies-response">

      Select your Favorite Programming Language <select
        name="favoriteLanguage"
      >
        <option>Java</option>
        <option>C#</option>
        <option>PHP</option>
        <option>Ruby</option>
      </select>

      <br />
      <br />

      <input type="submit" value="Soumettre" />
    </form>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>