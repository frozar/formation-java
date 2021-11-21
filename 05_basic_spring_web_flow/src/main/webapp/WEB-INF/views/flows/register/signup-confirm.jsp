<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Registration Page 3</title>
<link rel="stylesheet" href="/resources/static/css/bootstrap.min.css" />
</head>

<body>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <sf:form method="POST" modelAttribute="registerModel">
          <h1>Confirm Details (Stage 3)</h1>
          <hr />
          <a href="${flowExecutionUrl}&_eventId_home"
            class="btn btn-danger"
          >Home</a>

          <div class="row">
            <div class="col-md-6">
              <h2>Personal Info</h2>
              <div>
                <p>
                  <c:if
                    test="${fn:length(registerModel.personalInfo.getImageBase64()) != 0}"
                  >
                    <img class="img-fluid"
                      src="data:image/png;base64,${registerModel.personalInfo.getImageBase64()}"
                    />
                  </c:if>
                </p>
                <p>${registerModel.personalInfo.username}</p>
                <p>${registerModel.personalInfo.firstname}</p>
                <p>${registerModel.personalInfo.lastname}</p>
                <button type="submit" class="btn btn-warning"
                  name="_eventId_personal"
                >&laquo; Goto Personal</button>
              </div>
            </div>
            <div class="col-md-6">
              <h2>Billing Info</h2>
              <p>${registerModel.billingInfo.address}</p>
              <button type="submit" class="btn btn-warning"
                name="_eventId_billing"
              >&laquo; Goto Billing</button>
            </div>
            <c:if
              test="${not empty flowRequestContext.messageContext.getMessagesBySource('registration')}"
            >
              <c:forEach
                items="${flowRequestContext.messageContext.getMessagesBySource('registration')}"
                var="err"
              >
                <span> err : <c:out value="${err.text}" />
                </span>
              </c:forEach>
            </c:if>
            <button type="submit" class="btn btn-success"
              name="_eventId_submit"
            >Register &raquo;</button>
          </div>
        </sf:form>
      </div>
    </div>
  </div>
</body>

</html>