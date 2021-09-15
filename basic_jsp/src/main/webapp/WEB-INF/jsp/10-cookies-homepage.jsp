<html lang="en">
<head>
<title>Training Portal</title>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/cookies-homepage" />
  </jsp:include>

  <div class="container">
    <h3>Training Portal</h3>

    <!-- read the favorite programming language cookie -->
    <%
    // the default ... if there are no cookies
    String favLang = "Java";

    // get the cookies from the browser request
    Cookie[] theCookies = request.getCookies();

    // find our favorite language cookie
    if (theCookies != null) {
      for (Cookie tempCookie : theCookies) {
        if ("myApp.favoriteLanguage".equals(tempCookie.getName())) {
      favLang = tempCookie.getValue();
      break;
        }
      }
    }
    %>

    <!-- now show a personalized page ... use the "favLang" variable -->

    <!-- show new books for this lang -->
    <h4>
      New Books for
      <%=favLang%></h4>
    <ul>
      <li>toto titi tata</li>
      <li>toto titi tata</li>
    </ul>

    <h4>
      Latest News Rreports for
      <%=favLang%></h4>
    <ul>
      <li>toto titi tata</li>
      <li>toto titi tata</li>
    </ul>

    <h4>
      Hot Jobs for
      <%=favLang%></h4>
    <ul>
      <li>toto titi tata</li>
      <li>toto titi tata</li>
    </ul>

    <hr>
    <a href="cookies">Personalize this page.</a>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>