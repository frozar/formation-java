<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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

  Param: ${param}
  <br />
  Status: ${status}
  <br />


  <sf:form action="add-current-account" method="POST"
    modelAttribute="compteCourant"
  >
    <div>
      <sf:label path="nom">Nom : </sf:label>
      <sf:input path="nom" />
      <br />
      <sf:errors cssClass="error" path="nom" />
    </div>
    <br />
    <div>
      <sf:label path="prenom">Prénom : </sf:label>
      <sf:input path="prenom" />
      <br />
      <sf:errors cssClass="error" path="prenom" />
    </div>
    <br />
    <div>
      <sf:label path="codeGuichet">Code guichet : </sf:label>
      <sf:input path="codeGuichet" />
      <br />
      <sf:errors cssClass="error" path="codeGuichet" />
    </div>
    <br />
    <input type="submit" value="Soumettre" />
  </sf:form>
</body>

</html>