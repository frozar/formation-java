<!DOCTYPE html>
<%@page import="java.util.Arrays"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this, 
        Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
    <spring:url value="/css/main.css" var="springCss" />
    <link href="${springCss}" rel="stylesheet" />
     -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>JSP Scriplet</h1>
			<div>
				<%
				for (int i = 0; i < 5; ++i) {
				  out.println("I really love rougail saucisse: " + i + "<br />");
				}
				%>
			</div>
			<div>
				And <br />
				<ul>
					<%
					for (int i = 0; i < 3; ++i) {
					  out.println("<li>Carry poulet: " + i + "</li>");
					}
					%>
				</ul>
				<br /> Gouvernement <br />
				<ul>
					<%
					List<String> gouv = Arrays.asList("Jean Castex", "Jean-Yves le Drian",
					    "Barbara Pompili", "Jean-Michel Blanquer", "Bruno Le Maire",
					    "Florence Parly", "Gérald Darmanin", "Elisabeth Borne", "Sébastien Lecornu",
					    "Jacqueline Gourault", "Eric Dupond-Moretti", "Roselyne Bachelot",
					    "Olivier Véran", "Annick Girardin", "Frédérique Vidal",
					    "Julien Denormandie", "Amélie de Montchalin");
					for (String name : gouv) {
					  out.println("<li>Ministre: " + name + "</li>");
					}
					%>
				</ul>
				<p>
				Source : <a href="https://www.gouvernement.fr/composition-du-gouvernement">https://www.gouvernement.fr/composition-du-gouvernement</a>
				</p>
			</div>
		</div>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>