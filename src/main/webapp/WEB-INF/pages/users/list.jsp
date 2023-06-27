<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container py-5">
  <div class="d-flex justify-content-between">
    <div class="d-flex justify-content-between" style="width: 15%">
      <h3>User List</h3>
      <c:url var="exportAction" value="/users/excel/export" />
      <a href="${exportAction}" class="btn btn-secondary">Export</a>
    </div>
    <div>
      <c:url var="searchAction" value="/users/search" />
      <form action="${searchAction}" method="GET" class="d-flex">
        <input type="text" name="keyword" class="form-control"
          placeholder="Search....." />
        <button type="submit" class="btn btn-success ml-3">Search</button>
      </form>
    </div>
    <c:url var="createAction" value="/users/create" />
    <a class="inline-block btn btn-primary" href="${createAction}">Add
      New</a>
  </div>
  <div class="table-responsive py-5">
    <table class="table table-bordered table-striped">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Posts</th>
        <th>Action</th>
      </tr>
      <c:forEach items="${users}" var="user">
        <tr>
          <td>${user.id}</td>
          <td>${user.name}</td>
          <td>${user.email}</td>
          <td>
            <c:forEach items="${user.roles}" var="role">
                <span class="badge bg-primary">${role}</span>
            </c:forEach>
          </td>
          <td>
            <c:forEach items="${user.posts}" var="post">
                <span class="badge bg-primary">${post.title}</span>
            </c:forEach>
          </td>
          <td><c:url var="editAction"
              value="/users/edit?id=${user.id}" /> <c:url
              var="deleteAction" value="/users/destroy?id=${user.id}" />
            <a class="btn btn-info" href="${editAction }">Edit</a> <a
            class="btn btn-danger" href="${deleteAction}">Delete</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
