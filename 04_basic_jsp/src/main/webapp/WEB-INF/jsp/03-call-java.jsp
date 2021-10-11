<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.basic.Utils"%>
<html lang="en">
<head>
<jsp:include page="header.jsp" />
</head>

<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/call-java" />
  </jsp:include>

  <div class="container">

    <div class="starter-template">
      <h1>JSP Call Java</h1>
      <div>
        Lower case "Hello World":
        <%=com.example.basic.Utils.makeItLower("Hello World")%>
      </div>
      <div>
        Thanks to the [page import="com.example.basic.Utils"], lower
        case "Hello World":
        <%=Utils.makeItLower("Hello World")%>
      </div>
    </div>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>