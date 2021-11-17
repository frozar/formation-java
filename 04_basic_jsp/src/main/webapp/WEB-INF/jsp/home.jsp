<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/" />
  </jsp:include>

  <div class="container">
    <div class="starter-template">
      <h1>Spring Boot Web JSP Example</h1>
      <h2>Message: ${message}</h2>
    </div>
  </div>

  <jsp:include page="footer.jsp" />
</body>

</html>