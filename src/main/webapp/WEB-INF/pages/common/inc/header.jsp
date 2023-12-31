
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="security"%>
<nav class="navbar-dark bg-dark px-5">
  <div class="navbar navbar-expand navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/posts">Blog</a>
    <ul class="navbar-nav me-auto">
      <li class="nav-item"><a class="nav-link"
        href="${pageContext.request.contextPath}/posts">Home</a></li>
      <security:authorize access="hasAuthority('user')">
        <li class="nav-item"><a class="nav-link"
          href="${pageContext.request.contextPath}/posts">Posts</a></li>
      </security:authorize>
      <security:authorize access="hasAuthority('admin')">
        <li class="nav-item"><a class="nav-link"
          href="${pageContext.request.contextPath}/users">Users</a></li>
      </security:authorize>
    </ul>
    <div class="btn-group">
      <button type="button" class="btn btn-secondary dropdown-toggle"
        data-bs-toggle="dropdown" data-bs-display="static"
        aria-expanded="false"><c:out value="${user.name}" /></button>
      <ul class="dropdown-menu dropdown-menu-start">
        <c:url var="logoutAction" value="/logout" />
        <li><a href="${logoutAction}" class="dropdown-item">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
