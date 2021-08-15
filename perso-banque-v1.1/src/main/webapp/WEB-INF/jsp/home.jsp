<!DOCTYPE html>
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
            <c:out value="${adherent.nom} ${adherent.prenom}" /> <br />
        </c:forEach>
        <br />
        <a href="/add-person-model-and-view"><button>Ajouter un adhérent</button></a>
        <a href="/add-current-account"><button>Créer un compte courant</button></a>
        <a href="/add-current-account-full"><button>Créer un compte courant (full)</button></a>
		
	</div>


</body>

</html>