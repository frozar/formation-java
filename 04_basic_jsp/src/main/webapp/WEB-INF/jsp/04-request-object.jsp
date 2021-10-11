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
    <jsp:param name="pathname" value="/greeting" />
  </jsp:include>

  <div class="container">

    <div class="starter-template">
      <h1>JSP Built-In Objects</h1>
      <div>
        Request user agent:
        <%=request.getHeader("User-Agent")%>
      </div>
      <div>
        Request language:
        <%=request.getLocale()%>
      </div>
    </div>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>