<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authors</title>
    <jsp:include page="../_header.jsp"/>
</head>
<body>
<div class="shell">
    <header class="site-header">
        <div class="brand">Atlas <span>Library</span></div>
        <nav class="site-nav">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/books">Books</a>
            <a href="${pageContext.request.contextPath}/authors/new">Add author</a>
        </nav>
    </header>

    <div class="card">
        <h1 class="page-title">Authors</h1>
        <p class="lead">All authors in the catalog.</p>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">${errorMessage}</div>
        </c:if>

        <div class="toolbar">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/authors/new">New author</a>
        </div>

        <table class="data-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${authors}">
                <tr>
                    <td>${a.id}</td>
                    <td>${a.fullName}</td>
                    <td>${a.email}</td>
                    <td><a href="${pageContext.request.contextPath}/authors/${a.id}/edit">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
