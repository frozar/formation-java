<html lang="en">
<head>
<title>Confirmation</title>
<jsp:include page="header.jsp" />
</head>

<%
// res form data
String favLang = request.getParameter("favoriteLanguage");

// create the cookie
Cookie theCookie = new Cookie("myApp.favoriteLanguage", favLang);

// set the life span ... total number of seconds
theCookie.setMaxAge(60 * 60 * 24 * 365);

//send cookie to browser
response.addCookie(theCookie);
%>

<body>
  <jsp:include page="nav-bar.jsp"/>

  <div class="container">

    Thanks! We set your favorite language to: ${param.favoriteLanguage}

    <br />
    <br />

    <a href="cookies-homepage">Return to homepage.</a>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>