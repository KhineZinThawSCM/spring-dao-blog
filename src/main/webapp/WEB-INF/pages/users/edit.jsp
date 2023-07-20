<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="container py-5">
  <h1>User Edit</h1>
  <c:url var="updateAction" value="/users/update?id=${userForm.getId()}"></c:url>
  <form:form action="${updateAction}" method="POST"
    modelAttribute="userForm" enctype="multipart/form-data">
    <div class="mb-3">
      <label for="name" class="form-label">Name</label>
      <form:input type="text" path="name" class="form-control"
        placeholder="Enter Name" />
      <form:errors path="name" cssClass="text-danger" />
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <form:input type="email" path="email" class="form-control"
        placeholder="name@gmail.com" />
      <form:errors path="email" cssClass="text-danger" />
    </div>
    <div class="mb-3">
      <label for="role" class="form-label">Select Role</label>
      <form:select path="roleId" class="form-select">
        <option value="0">Select Role....</option>
        <c:forEach items="${roles}" var="role">
          <option value="${role.id}" ${fn:contains(userForm.roles, role.name) ? 'selected' : ''}>${role.name}</option>
        </c:forEach>
      </form:select>
      <form:errors path="roleId" cssClass="text-danger" />
    </div>
     <div class="mb-3">
      <label for="photo" class="form-label">Photo</label>
      <form:input type="file" path="photo" class="form-control" />
      <form:errors path="photo" cssClass="text-danger" />
    </div>
    <div class="mb-3">
      <button type="submit" class="btn btn-success">Update</button>
    </div>
  </form:form>
</div>