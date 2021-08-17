<html>

<head>
<title>Training Portal</title>
</head>


<body>
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

</body>

</html>