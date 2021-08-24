<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
 <html lang="en">
<head>
<title>Compte courant cr��</title>
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
                    <li class="active"><a href="add-person-model-and-view">Ouverture compte courant</a></li>
                    <li><a href="synthese-compte">Synth�se compte</a></li>
                    <li><a href="depotChequeCCModelAndView">D�p�t ch�que</a></li>
                    <li><a href="#about">Retrait esp�ces</a></li>
                    <li><a href="#about">D�pot esp�ces</a></li>
                    <li><a href="#about">Liste des op�rations</a></li>
                    <li><a href="#about">Consultation solde</a></li>
                    <li><a href="#about">Demande de ch�quier</a></li>
                </ul>
            </div>
        </div>
    </nav>
        <center><img src="/img/logo2.jpg" alt="Cr�dit 974" width="150" height="150" />
   <h2>Votre compte a �t� cr��</h2>

<br />
Nom :<%= request.getParameter("nom") %> <p></p> Pr�nom : <%= request.getParameter("prenom") %>
</p> Code guichet : <%= request.getParameter("codeGuichet") %>
<!-- A shortcut version here. -->

<p></p>
</body>
</html>