<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Access the bootstrap Css like this, 
         Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
  href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
/>

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
