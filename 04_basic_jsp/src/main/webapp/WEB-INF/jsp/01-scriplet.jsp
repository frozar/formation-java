<!DOCTYPE html>
<%@page import="java.util.Arrays"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List,java.util.HashMap,java.util.Map"%>
<html lang="en">
<head>
<jsp:include page="header.jsp" />
</head>
<body>
  <jsp:include page="nav-bar.jsp">
    <jsp:param name="pathname" value="/scriplet" />
  </jsp:include>

  <div class="container">

    <div class="starter-template">
      <h1>JSP Scriplet</h1>
      <div>
        <%
        for (int i = 0; i < 5; ++i) {
          out.println("I really love rougail saucisse: " + i + "<br />");
        }
        %>
      </div>
      <div>
        And
        <br />
        <ul>
          <%
          for (int i = 0; i < 3; ++i) {
            out.println("<li>Carry poulet: " + i + "</li>");
          }
          %>
        </ul>
        <br />
        Gouvernement
        <br />
        <ul>
          <%
          List<String> gouv = Arrays.asList("Jean Castex", "Jean-Yves le Drian",
              "Barbara Pompili", "Jean-Michel Blanquer", "Bruno Le Maire",
              "Florence Parly", "Gérald Darmanin", "Elisabeth Borne", "Sébastien Lecornu",
              "Jacqueline Gourault", "Eric Dupond-Moretti", "Roselyne Bachelot",
              "Olivier Véran", "Annick Girardin", "Frédérique Vidal",
              "Julien Denormandie", "Amélie de Montchalin");
          for (String name : gouv) {
            out.println("<li>Ministre: " + name + "</li>");
          }
          %>
        </ul>
        <p>
          Source :
          <a
            href="https://www.gouvernement.fr/composition-du-gouvernement"
          >https://www.gouvernement.fr/composition-du-gouvernement</a>
        </p>
        <ul>
          <%
          HashMap<String, String> gouvMap = new HashMap<String, String>();
          gouvMap.put("Premier minister", "Jean Castex");
          gouvMap.put("Ministre de l'Europe et des Affaires etrangeres",
              "Jean-Yves le Drian");
          gouvMap.put("Ministre de la Transition ecologique", "Barbara Pompili");

          for (Map.Entry mapElement : gouvMap.entrySet()) {
            String status = (String) mapElement.getKey();
            String name = (String) mapElement.getValue();

            out.println("<li> " + status + " : " + name + " </li>");
          }
          %>
        </ul>
        <br />
      </div>
    </div>

  </div>

  <jsp:include page="footer.jsp" />

</body>

</html>
