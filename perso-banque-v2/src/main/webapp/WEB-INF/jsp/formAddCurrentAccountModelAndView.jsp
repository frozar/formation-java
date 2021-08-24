<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
 <html lang="en">
<head>
<title>Nouveau compte courant</title>
 <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
    <!-- Access the bootstrap Css like this, 
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
     
    <spring:url value="/css/main.css" var="springCss" />
    <link href="${springCss}" rel="stylesheet" />
    
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    
    <c:url value="/img/logo.png" var="logo" />
    <style type="text/css">
    .error {
        color: red;
        font-style: italic;
    }
</style>
</head>
<body>
   <nav class="navbar navbar-inverse">
        <div class="container">
<!--            <div class="navbar-header">
                <a class="navbar-brand" href="#">Spring Boot</a>
            </div>   -->
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li>
                    <li class="active"><a href="#about">Ouverture compte courant</a></li>
                    <li><a href="synthese-compte">Synthèse compte</a></li>
                    <li><a href="depotChequeCCModelAndView">Dépôt chèque</a></li>
                    <li><a href="#about">Retrait espèces</a></li>
                    <li><a href="#about">Dépot espèces</a></li>
                    <li><a href="#about">Liste des opérations</a></li>
                    <li><a href="#about">Consultation solde</a></li>
                    <li><a href="#about">Demande de chéquier</a></li>
                </ul>
            </div>
        </div>
    </nav>
        <center><img src="/img/logo2.jpg" alt="Crédit 974" width="150" height="150" />
   <h2>Création compte courant</h2>

	<sf:form action="add-current-account-model-and-view" method="POST"
		modelAttribute="addCompteCourantForm">
		<div>
		<sf:label path="nom">Nom : </sf:label>
		<sf:input path="nom" />
        <br />
		<sf:errors path="nom" cssClass="error" />
		</div>
        <br />

        <div>
		<sf:label path="prenom">Prenom : </sf:label>
		<sf:input path="prenom" />
        <br />
		<sf:errors path="prenom" cssClass="error" />
        </div>
        <br />    

        <div>
		<sf:label path="codeGuichet">Code guichet : </sf:label>
		<sf:input path="codeGuichet" />
        <br />
		<sf:errors path="codeGuichet" cssClass="error" />
        </div>
        <br />   

        <br />         
		<input type="submit" value="Soumettre" />
	</sf:form>
</center>
</body>

</html>