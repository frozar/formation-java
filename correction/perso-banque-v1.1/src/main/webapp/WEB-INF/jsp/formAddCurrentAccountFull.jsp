<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
  <div>Status: ${status}</div>
  <br />

  <form action="add-current-account-full" method="POST">
    <div>
      <spring:bind path="personne.nom">
        <div>Status: ${status}</div>
        <div>Out: ${out}</div>
        <div>Request: ${request}</div>
        <div>Response: ${response}</div>
        <div>Session: ${session}</div>
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
        <label>Pr?nom : </label>
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
        <label>Num?ro de compte : </label>
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
        <label>Cl? : </label>
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
    <br />
    <input type="submit" value="Soumettre" />
  </form>

</body>

</html>