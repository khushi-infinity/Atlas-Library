<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="authorAction" value="${pageContext.request.contextPath}/authors"/>
<c:if test="${author.id != null}">
    <c:set var="authorAction" value="${pageContext.request.contextPath}/authors/${author.id}"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${author.id == null ? 'New' : 'Edit'} author</title>
    <jsp:include page="../_header.jsp"/>
</head>
<body>
<div class="shell">
    <header class="site-header">
        <div class="brand">Atlas <span>Library</span></div>
        <nav class="site-nav">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/authors">Authors</a>
        </nav>
    </header>

    <div class="card">
        <h1 class="page-title">${author.id == null ? 'Add author' : 'Edit author'}</h1>
        <p class="lead">Duplicate emails are rejected by the database and handled gracefully.</p>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">${errorMessage}</div>
        </c:if>

        <form:form modelAttribute="author" action="${authorAction}" method="post" cssClass="form-grid">
            <label class="field">
                Full name
                <form:input path="fullName" autocomplete="name"/>
                <form:errors path="fullName" cssClass="error-text" element="div"/>
            </label>
            <label class="field">
                Email
                <form:input path="email" type="email" autocomplete="email"/>
                <form:errors path="email" cssClass="error-text" element="div"/>
            </label>
            <div>
                <button type="submit" class="btn btn-primary">Save</button>
                <a class="btn btn-ghost" href="${pageContext.request.contextPath}/authors" style="margin-left:.5rem">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
