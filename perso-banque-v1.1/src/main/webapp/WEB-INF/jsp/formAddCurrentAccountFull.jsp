<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<title>Nouveau compte courant</title>
<style type="text/css">
.error {
    color: red;
    font-style: italic;
}
</style>
</head>

<body>

  <h1>Nouveau compte courant</h1>

  <form action="add-current-account-full" method="POST">
    <div>
      <spring:bind path="personne.nom">
        <label>Nom : </label>
        <input type="text" name="${status.expression}"
          value="${status.value}"
        >
        <br />
        <c:if test="${status.error}">
          <span class="error">${status.errorMessages[0]}</span>
        </c:if>
      </spring:bind>
    </div>
    <br />

    <div>
      <spring:bind path="personne.prenom">
        <label>Prénom : </label>
        <input type="text" name="${status.expression}"
          value="${status.value}"
        >
        <br />
        <c:if test="${status.error}">
          <span class="error">${status.errorMessages[0]}</span>
        </c:if>
      </spring:bind>
    </div>
    <br />

    <!-- 
    This field is used only in the successful summary form
    -->
    <div>
      <spring:bind path="banque.codeBanque">
        <input type="hidden" name="${status.expression}"
          value="${status.value}"
        >
      </spring:bind>
    </div>

    <div>
      <spring:bind path="compteCourant.codeGuichet">
        <label>Code guichet : </label>
        <input type="text" name="${status.expression}"
          value="${status.value}"
        >
        <br />
        <c:if test="${status.error}">
          <span class="error">${status.errorMessages[0]}</span>
        </c:if>
      </spring:bind>
    </div>
    <br />

    <div>
      <spring:bind path="compteCourant.numCompte">
        <label>Numéro de compte : </label>
        <input type="text" name="${status.expression}"
          value="${status.value}"
        >
        <br />
        <c:if test="${status.error}">
          <span class="error">${status.errorMessages[0]}</span>
        </c:if>
      </spring:bind>
    </div>
    <br />

    <div>
      <spring:bind path="compteCourant.cle">
        <label>Clé : </label>
        <input type="text" name="${status.expression}"
          value="${status.value}"
        >
        <br />
        <c:if test="${status.error}">
          <span class="error">${status.errorMessages[0]}</span>
        </c:if>
      </spring:bind>
    </div>
    <br />

    <div>
      <spring:bind path="compteCourant.solde">
        <label>Solde : </label>
        <input type="text" name="${status.expression}"
          value="${status.value}"
        >
        <br />
        <c:if test="${status.error}">
          <span class="error">${status.errorMessages[0]}</span>
        </c:if>
      </spring:bind>
    </div>
    <br /> <input type="submit" value="Soumettre" />
  </form>

</body>

</html>