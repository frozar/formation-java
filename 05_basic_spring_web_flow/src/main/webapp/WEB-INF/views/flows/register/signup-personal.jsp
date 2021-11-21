<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Registration Page 1</title>
<link rel="stylesheet" href="/resources/static/css/bootstrap.min.css" />
</head>

<body>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h1>Personal Information (Stage 1)</h1>
        <hr />
        <a href="${flowExecutionUrl}&_eventId_home"
          class="btn btn-danger"
        >Home</a>
        <sf:form method="POST"
          enctype="multipart/form-data"
          modelAttribute="personalInfo"
        >
          <div class="form-group row">
            <label for="image" class="col-sm-2 col-form-label">Image</label>
            <div class="col-sm-10">
              <sf:input type="file" path="imageContent"
                class="form-control" id="image" placeholder="Image file"
                name="image"
              />
              <sf:errors class="form-text text-danger"
                path="imageContent" cssClass="help-block" element="em"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Username</label>
            <div class="col-sm-10">
              <sf:input type="text" path="username" class="form-control" 
              id="username" placeholder="Username" autofocus="true" />
              <sf:errors class="form-text text-danger" path="username"
                cssClass="help-block" element="em"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
              <!-- th:field="*{password}" -->
              <sf:input type="password" path="password" class="form-control" id="password"
                placeholder="Password" />
              <sf:errors class="form-text text-danger" path="password"
                cssClass="help-block" element="em"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="confirmPassword" class="col-sm-2 col-form-label">Confirm
              Password</label>
            <div class="col-sm-10">
              <!-- th:field="*{confirmPassword}" -->
              <sf:input type="password" path="confirmPassword" class="form-control"
                id="confirmPassword" placeholder="Re-enter password"
              />
              <sf:errors class="form-text text-danger" path="confirmPassword"
                cssClass="help-block" element="em"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="firstname" class="col-sm-2 col-form-label">First
              Name</label>
            <div class="col-sm-10">
              <sf:input type="text" path="firstname" class="form-control" id="firstname"
                placeholder="First Name"
              />
              <sf:errors class="form-text text-danger" path="firstname"
                cssClass="help-block" element="em"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="lastname" class="col-sm-2 col-form-label">Last
              Name</label>
            <div class="col-sm-10">
              <sf:input type="text" class="form-control" path="lastname" id="lastname"
                placeholder="Last Name"
              />
              <sf:errors class="form-text text-danger" path="lastname"
                cssClass="help-block" element="em"
              />
            </div>
          </div>
          <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-primary"
                name="_eventId_billing"
              >Next (Billing) &raquo;</button>
            </div>
          </div>
        </sf:form>
      </div>
    </div>
  </div>
</body>

</html>