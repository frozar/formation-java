<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<jsp:include page="header.jsp" />
</head>

<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/declaration" />
  </jsp:include>

  <!-- Here is the declaration -->
  <%!String makeItLower(String data) {
    return data.toLowerCase();
  }%>

  <div class="container">

    <div class="starter-template">
      <h1>JSP Declaration</h1>
      <div>
        Lower case "Hello World":
        <%=makeItLower("Hello World")%>
      </div>
    </div>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>