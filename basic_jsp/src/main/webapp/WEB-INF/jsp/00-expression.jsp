<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/expression" />
  </jsp:include>

  <div class="container">

    <div class="starter-template">
      <h1>JSP Expression</h1>
      <h2>
        What time is it?
        <%=new java.util.Date()%></h2>
      <h3>
        Big hello:
        <%=new String("Hello World").toUpperCase()%></h3>
      <h3>
        25 * 4:
        <%=25 * 4%></h3>
      <h3>
        75 < 69:
        <%=75 < 69%></h3>
    </div>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>