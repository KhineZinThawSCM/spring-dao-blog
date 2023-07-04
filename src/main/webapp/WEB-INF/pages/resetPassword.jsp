<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
  crossorigin="anonymous">
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
  crossorigin="anonymous"></script>
</head>
<body>
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="card px-5 py-5 shadow" style="width: 50%;">
        <h2 class="text-center mb-3">RESET PASSWORD</h2>
        <c:url var="resetPasswordAction" value="/reset_password/update"></c:url>
        <form:form action="${resetPasswordAction}" method="POST"
          modelAttribute="resetPasswordForm">
          <form:input type="hidden" path="token"
            value="${resetPasswordToken.token}" />
          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <form:input type="email" path="email"
              value="${resetPasswordToken.user.email}"
              class="form-control" id="email" readonly="true" />
            <form:errors path="email" cssClass="text-danger" />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">New
              Password</label>
            <form:input type="password" path="password"
              class="form-control" id="password" />
            <form:errors path="password" cssClass="text-danger" />
          </div>
          <div class="mb-3">
            <label for="confirmPassword" class="form-label">Confirm
              Password</label>
            <form:input type="password" path="confirmPassword"
              class="form-control" id="confirmPassword" />
            <form:errors path="confirmPassword" cssClass="text-danger" />
          </div>
          <div class="d-grid col-12 mx-auto">
            <button type="submit" class="btn btn-primary">Submit</button>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</body>
</html>