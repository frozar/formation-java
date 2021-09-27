<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">
<head>
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
  <h1>Synthèse des comptes</h1>

  <div>
    <br />
    Nombre de comptes : ${fn:length(compteAPersonne)}
    <br />

<!-- 
    Documentation link about JSTL if tag:
    https://javarevisited.blogspot.com/2013/02/5-jstl-core-if-tag-examples-in-jsp.html
 -->
    <c:if test="${fn:length(compteAPersonne) != 0}">
      <table style="width: 800px">
        <tr>
          <th style="font-weight: normal;">Type compte</th>
          <th style="font-weight: normal;">RIB</th>
          <th style="font-weight: normal;">Titulaire</th>
          <th style="font-weight: normal;">Solde</th>
          <th style="font-weight: normal;">M</th>
          <th style="font-weight: normal;">D</th>
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
            <th style="font-weight: normal;">${entry.value.nom}
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
    </c:if>

    <br />

    <c:if test="${! empty error}">
      <span class="error">${error}</span>
    </c:if>

    <h3>
      <a href="/">Home</a>
    </h3>

  </div>


</body>

</html>