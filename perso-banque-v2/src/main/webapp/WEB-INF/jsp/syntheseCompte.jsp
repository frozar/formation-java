<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
 <html lang="en">
<head>
<title>Synthèse compte</title>
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
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}

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
                    <li><a href="add-current-account-model-and-view">Ouverture compte courant</a></li>
                    <li class="active"><a href="synthese-compte">Synthèse compte</a></li>
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
   <h3>Synthèse des comptes</h3>

  <div>
    Nombre de comptes : ${fn:length(compteAPersonne)}
    <br /><br />

<!-- 
    Documentation link about JSTL if tag:
    https://javarevisited.blogspot.com/2013/02/5-jstl-core-if-tag-examples-in-jsp.html
 -->
    <c:if test="${fn:length(compteAPersonne) != 0}">
      <table style="width: 800px">
        <tr>
          <th style="font-weight: bold; background-color: #D0E3FA;">Type compte</th>
          <th style="font-weight: bold;background-color: #D0E3FA;">RIB</th>
          <th style="font-weight: bold;background-color: #D0E3FA;">Titulaire</th>
          <th style="font-weight: bold;background-color: #D0E3FA;">Solde</th>
          <th style="font-weight: bold;background-color: #D0E3FA;">M</th>
          <th style="font-weight: bold;background-color: #D0E3FA;">D</th>
        </tr>
        <c:forEach items="${compteAPersonne}" var="entry">
          <tr>
            <th style="font-weight: normal;">
              <c:choose>
                <c:when
                  test="${entry.key['class'].simpleName == 'CompteCourant'}"
                >
                Compte courant
              </c:when>
                <c:otherwise>
                Compte épargne
              </c:otherwise>
              </c:choose>
            </th>
            <th style="font-weight: normal;">${entry.key.codeBanque}
              ${entry.key.codeGuichet} ${entry.key.numCompte}
              ${entry.key.cle}</th>
            <th style="font-weight: normal;text-transform: capitalize;">${entry.value.nom}
              ${entry.value.prenom}</th>
            <th style="font-weight: normal;">${entry.key.solde}</th>
            <th style="font-weight: normal;">
              <img src="pencil-icon.jpg" style="width: 16px;" />
            </th>
            <th style="font-weight: normal;">
              <a
                href="/delete-account?codeGuichet=${entry.key.codeGuichet}&numCompte=${entry.key.numCompte}"
              >
                <img src="trash-icon.jpg" style="width: 16px;" />
              </a>
            </th>
          </tr>
        </c:forEach>
      </table>
      </center>
    </c:if>

    <br />

    <c:if test="${! empty error}">
      <span class="error">${error}</span>
    </c:if>

  </div>
</body>
</html>
