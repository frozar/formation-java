<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Registration Page 2</title>
<link rel="stylesheet" href="/resources/static/css/bootstrap.min.css" />
</head>

<body>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h1>Billing Information (Stage 2)</h1>
        <hr />
        <a href="${flowExecutionUrl}&_eventId_home"
          class="btn btn-danger"
        >Home</a>

        <sf:form method="POST" modelAttribute="billingInfo">
          <div class="form-group row">
            <label for="address" class="col-sm-2 col-form-label">Address</label>
            <div class="col-sm-10">
              <sf:input type="text" path="address" class="form-control"
                id="address" placeholder="Address" name="address"
                autofocus="true"
              />
              <sf:errors class="form-text text-danger" path="address"
                cssClass="help-block" element="em"
              />
            </div>
          </div>
          <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-secondary"
                name="_eventId_personal"
              >&laquo; Previous (Personal)</button>
              <button type="submit" class="btn btn-primary"
                name="_eventId_confirm"
              >Next (Confirm) &raquo;</button>
            </div>
          </div>
        </sf:form>

      </div>
    </div>
  </div>
</body>

</html>