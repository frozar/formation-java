<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<title>Nouveau compte</title>
<style type="text/css">
.error {
	color: red;
	font-style: italic;
}
</style>
</head>

<body>

	<h1>Nouveau compte</h1>

	<%-- <sf:form action="add-current-account" method="POST"
        modelAttribute="personne"> --%>
	<form action="add-current-account" method="POST">
		<div>
			<spring:bind path="personne.prenom">
				<%-- <sf:label path="prenom">Prenom : </sf:label>
				<sf:input path="prenom" /> --%>
				<%-- 				<sf:label path="${status.expression}">Prenom : </sf:label> --%>
				<label>${status.expression} : </label>
				<input type="text" name="${status.expression}"
					value="${status.value}">
				<br />
				<%-- 				<sf:errors path="${status.expression}" cssClass="error" /> --%>
				<c:if test="${status.error}">
                    <span class="error">${status.errorMessages}</span>
				</c:if>
			</spring:bind>
		</div>
		<br />

		<div>
			<spring:bind path="personne.nom">
				<label>${status.expression} : </label>
				<input type="text" name="${status.expression}"
					value="${status.value}">
				<br />
				<c:if test="${status.error}">
                    <span class="error">${status.errorMessages}</span>
				</c:if>
			</spring:bind>
		</div>
		<br />

		<div>
			<spring:bind path="compte-courant.codeGuichet">
				<label>${status.expression} : </label>
				<input type="text" name="${status.expression}"
					value="${status.value}">
				<br />
                <c:if test="${status.error}">
                    <span class="error">${status.errorMessages[0]}</span>
                    <span class="error"><c:out value="${status.errorMessages[0]}" /></span>
                </c:if>
			</spring:bind>
		</div>
		<br />

		<div>
			<spring:bind path="compte-courant.numCompte">
				<label>${status.expression} : </label>
				<input type="text" name="${status.expression}"
					value="${status.value}">
				<br />
                <c:if test="${status.error}">
                    <span class="error">${status.errorMessages}</span>
                </c:if>
			</spring:bind>
		</div>
		<br />

		<div>
			<spring:bind path="compte-courant.cle">
				<%--            <sf:label path="${status.expression}">Nom : </sf:label> --%>
				<label>${status.expression} : </label>
				<input type="text" name="${status.expression}"
					value="${status.value}">
				<br />
				<%--            <sf:errors path="${status.expression}" cssClass="error" /> --%>
			</spring:bind>
		</div>
		<br />

		<div>
			<spring:bind path="compte-courant.solde">
				<%--            <sf:label path="${status.expression}">Nom : </sf:label> --%>
				<label>${status.expression} : </label>
				<input type="text" name="${status.expression}"
					value="${status.value}">
				<br />
				<%--            <sf:errors path="${status.expression}" cssClass="error" /> --%>
			</spring:bind>
		</div>
		<br /> <input type="submit" value="Soumettre" />
	</form>

</body>

</html>