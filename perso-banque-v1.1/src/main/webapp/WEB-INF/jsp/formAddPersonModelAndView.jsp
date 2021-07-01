<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<title>Nouvelle personne</title>
<style type="text/css">
    .error {
        color: red;
        font-style: italic;
    }
</style>
</head>

<body>

    <h1>Nouvelle personne</h1>

	<sf:form action="add-person-model-and-view" method="POST"
		modelAttribute="personne">
		<div>
		<sf:label path="prenom">Prenom : </sf:label>
		<sf:input path="prenom" />
        <br />
		<sf:errors path="prenom" cssClass="error" />
		</div>
        <br />

        <div>
		<sf:label path="nom">Nom : </sf:label>
		<sf:input path="nom" />
        <br />
		<sf:errors path="nom" cssClass="error" />
        </div>
        <br />
		<input type="submit" value="Soumettre" />
	</sf:form>

</body>

</html>