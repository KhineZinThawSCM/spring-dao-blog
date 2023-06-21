<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container py-5">
	<h1>Post Edit</h1>
	<c:url var="updateAction" value="/posts/update?id=${postForm.getId()}"></c:url>
	<form:form action="${updateAction}" method="POST"
		modelAttribute="postForm">
		<div class="mb-3">
			<label for="title" class="form-label">Title</label>
			<form:input type="text" path="title" class="form-control"
				placeholder="Enter Title" />
			<form:errors path="title" cssClass="text-danger" />
		</div>
		<div class="mb-3">
			<label for="description" class="form-label">Description</label>
			<form:input type="text" path="description" class="form-control"
				placeholder="Enter Description" />
			<form:errors path="description" cssClass="text-danger" />
		</div>
		<div class="mb-3">
			<label for="user" class="form-label">Select User</label>
			<form:select path="userId" class="form-select">
				<option>Select....</option>
				<c:forEach items="${users}" var="user">
					<option value="${user.id}"
						${user.id == postForm.userId ? 'selected' : '' }>${user.name}</option>
				</c:forEach>
			</form:select>
			<form:errors path="userId" cssClass="text-danger" />
		</div>
		<div class="mb-3">
			<button type="submit" class="btn btn-success">Update</button>
		</div>
	</form:form>
</div>
