<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.List"%>

<html lang="en">
<head>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>
<body>
  <h1>Synthèse des comptes</h1>

  <div>
    <br />
    <%-- 
		Nombre d'adhérents : ${fn:length(adherents)}
        <br />
        <c:forEach items="${adherents}" var="adherent">
            <c:out value="${adherent.nom} ${adherent.prenom}" /> <br />
        </c:forEach>
 --%>
    Nombre de comptes : ${fn:length(compteAPersonne)}
    <br />

    <table style="width: 800px">
      <tr>
        <th style="font-weight: normal;">Type compte</th>
        <th style="font-weight: normal;">RIB</th>
        <th style="font-weight: normal;">Titulaire</th>
        <th style="font-weight: normal;">Solde</th>
      </tr>
      <c:forEach items="${compteAPersonne}" var="entry">
        <tr>
          <th style="font-weight: normal;">
            <%--           
            Key = ${entry.key}
 --%>
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
          <th style="font-weight: normal;">
            <%-- 
          value = ${entry.value}
 --%>
            ${entry.key.codeBanque} ${entry.key.codeGuichet}
            ${entry.key.numCompte} ${entry.key.cle}
          </th>
          <th style="font-weight: normal;">${entry.value.nom}
            ${entry.value.prenom}</th>
          <th style="font-weight: normal;">${entry.key.solde}</th>
        </tr>
      </c:forEach>
    </table>
    <br />

    <!-- 
    <a href="/synthese-compte">
      <button>Synthèse des comptes</button>
    </a>
    <a href="/add-current-account">
      <button>Créer un compte courant</button>
    </a>
    <a href="/add-current-account-full">
      <button>Créer un compte courant (full)</button>
    </a>
 -->
    <h3>
      <a href="/">Home</a>
    </h3>

  </div>


</body>

</html>