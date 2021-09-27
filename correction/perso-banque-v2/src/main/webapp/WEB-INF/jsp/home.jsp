<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <html lang="en">
<head>
 <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
    <!-- Access the bootstrap Css like this, 
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
     
    <spring:url value="/css/main.css" var="springCss" />
    <link href="${springCss}" rel="stylesheet" />
    
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    
    <c:url value="/img/logo.png" var="logo" />
</head>
<body>

           
            
    <nav class="navbar navbar-inverse">
        <div class="container">
<!--            <div class="navbar-header">
                <a class="navbar-brand" href="#">Spring Boot</a>
            </div>   -->
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="add-current-account-model-and-view">Ouverture compte courant</a></li>
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
   
    <div class="container">

        <div class="starter-template">
 <!--           <h1>BanqueV1Application.</h1>
            <h2>Message: ${message}</h2>     
            <h2>Hello ${message}</h2> --> 
            <center> <h1>Bienvenue au ${codeBanque}</h1></center>
        <center><img src="/img/logo2.jpg" alt="Cr�dit 974" width="400" height="400" /></center>
        </div>

    </div>
     
<!--     <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 -->
</body>

</html>