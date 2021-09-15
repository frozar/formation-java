<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="routes"
  value="${[['/', 'Home'], 
  ['/greeting', 'Greeting'],
  ['/expression', 'Expression'],
  ['/scriplet', 'Scriplet'],
  ['/declaration', 'Declaration'],
  ['/call-java', 'Call java'],
  ['/request-object', 'Request object'],
  ['/include', 'Include'],
  ['/form', 'Form'],
  ['/form-dropdown', 'Form dropdown'],
  ['/form-radio', 'Form radio'],
  ['/form-checkbox', 'Form checkbox'],
  ['/cookies', 'Cookies'],
  ['/cookies-homepage', 'Cookies homepage']
  ]}"
/>

<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="https://spring.io/projects/spring-boot">Spring Boot</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar">
      <ul class="nav navbar-nav" style="display: flex;align-items: center;">
        <c:forEach items="${routes}" var="route">
          <c:choose>
            <c:when test="${param.pathname == route[0]}">
              <li class="active nav-item">
                <a href="${route[0]}" class="nav-link">${route[1]}</a>
              </li>
            </c:when>
            <c:otherwise>
              <li class="nav-item">
                <a href="${route[0]}">${route[1]}</a>
              </li>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </ul>
    </div>
  </div>
</nav>
