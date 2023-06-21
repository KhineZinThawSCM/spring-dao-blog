<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container py-5">
	<h1>User Create</h1>
	<c:url var="storeAction" value="/users/store"></c:url>
	<form:form action="${storeAction}" method="POST"
		modelAttribute="userForm">
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
			<label for="password" class="form-label">Password</label>
			<form:input type="password" path="password" class="form-control"
				placeholder="********" />
			<form:errors path="password" cssClass="text-danger" />
		</div>
		<div class="mb-3">
			<button type="submit" class="btn btn-success">Add New</button>
		</div>
	</form:form>
</div>
