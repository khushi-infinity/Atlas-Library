<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Books</title>
    <jsp:include page="../_header.jsp"/>
</head>
<body>
<div class="shell">
    <header class="site-header">
        <div class="brand">Atlas <span>Library</span></div>
        <nav class="site-nav">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/authors">Authors</a>
            <a href="${pageContext.request.contextPath}/books/new">Add book</a>
        </nav>
    </header>

    <div class="card">
        <h1 class="page-title">Books</h1>
        <p class="lead">Rows are loaded with a custom repository query that inner-joins each book to its author.</p>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">${errorMessage}</div>
        </c:if>

        <div class="toolbar">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/books/new">New book</a>
        </div>

        <table class="data-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>ISBN</th>
                <th>Author</th>
                <th>Author email</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items="${books}">
                <tr>
                    <td>${b.id}</td>
                    <td>${b.title}</td>
                    <td>${b.isbn}</td>
                    <td>${b.author.fullName}</td>
                    <td>${b.author.email}</td>
                    <td><a href="${pageContext.request.contextPath}/books/${b.id}/edit">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
