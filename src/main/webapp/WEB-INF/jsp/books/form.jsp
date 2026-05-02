<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="bookAction" value="${pageContext.request.contextPath}/books"/>
<c:if test="${book.id != null}">
    <c:set var="bookAction" value="${pageContext.request.contextPath}/books/${book.id}"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${book.id == null ? 'New' : 'Edit'} book</title>
    <jsp:include page="../_header.jsp"/>
</head>
<body>
<div class="shell">
    <header class="site-header">
        <div class="brand">Atlas <span>Library</span></div>
        <nav class="site-nav">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/books">Books</a>
        </nav>
    </header>

    <div class="card">
        <h1 class="page-title">${book.id == null ? 'Add book' : 'Edit book'}</h1>
        <p class="lead">ISBN must be unique. Choose the author for this title.</p>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">${errorMessage}</div>
        </c:if>

        <form:form modelAttribute="book" action="${bookAction}" method="post" cssClass="form-grid">
            <label class="field">
                Title
                <form:input path="title" autocomplete="off"/>
                <form:errors path="title" cssClass="error-text" element="div"/>
            </label>
            <label class="field">
                ISBN
                <form:input path="isbn" autocomplete="off"/>
                <form:errors path="isbn" cssClass="error-text" element="div"/>
            </label>
            <label class="field">
                Author
                <select name="authorId" id="authorId">
                    <option value="">— select —</option>
                    <c:forEach var="auth" items="${authors}">
                        <c:set var="sel" value=""/>
                        <c:if test="${not empty book.author && book.author.id == auth.id}">
                            <c:set var="sel" value="selected"/>
                        </c:if>
                        <option value="${auth.id}" ${sel}>${auth.fullName}</option>
                    </c:forEach>
                </select>
            </label>
            <form:errors path="*" cssClass="error-text" element="div"/>
            <div>
                <button type="submit" class="btn btn-primary">Save</button>
                <a class="btn btn-ghost" href="${pageContext.request.contextPath}/books" style="margin-left:.5rem">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
