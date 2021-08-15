<!DOCTYPE html>
<%-- 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.List"%>

<html lang="en">
<head>
</head>
<body>
	<h1>La DGFiP banque</h1>

	<div>
        <br />
		Nombre d'adhérents : ${fn:length(adherents)}
        <br />
        <c:forEach items="${adherents}" var="adherent">
            <c:out value="${adherent}" /> <br />
        </c:forEach>
        <br />
        <button><a href="/add-person-model-and-view">Ajouter un adhérent</a></button>
        <button><a href="/add-current-account-full">Créer un compte courant</a></button>
		
	</div>


</body>

</html>