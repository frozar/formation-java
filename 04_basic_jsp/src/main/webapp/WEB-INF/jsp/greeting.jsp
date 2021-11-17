<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List,java.util.Arrays"%>
<html lang="en">
<head>
<title>Greeting</title>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/greeting" />
  </jsp:include>

  <div class="container">

    <div class="starter-template">
      <h1>Spring Boot Web JSP Example</h1>
      <h2>Hello, ${name}</h2>
      <h2>Answer to all questions, ${myVar}</h2>
      <h2>An array raw: ${myArray}</h2>
      <h2>An array: [ ${myArray[0]}, ${myArray[1]} ]</h2>

      <h4>In JSTL style:</h4>
      <c:forEach items="${myArray}" var="item">
				JSTL: There is: <c:out value="${item}" />
        <br />
      </c:forEach>

      <h4>In JSP expression style:</h4>
      <%=request.getAttribute("myArray")%>
      <br />

      <h4>In JSP scriptlet style:</h4>
      <h5>The whole array</h5>
      <%
      out.print(request.getAttribute("myArray"));
      %>
      <br />
      <h5>Item by item</h5>
      <%
      for (String item : (List<String>) request.getAttribute("myArray")) {
        out.print(item);
        out.print("<br />");
      }
      %>
      <h3><%=new java.util.Date()%></h3>
    </div>

  </div>

  <jsp:include page="footer.jsp" />
</body>

</html>