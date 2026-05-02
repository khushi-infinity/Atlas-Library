<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Data conflict</title>
    <jsp:include page="../_header.jsp"/>
</head>
<body>
<div class="shell">
    <header class="site-header">
        <div class="brand">Atlas <span>Library</span></div>
        <nav class="site-nav">
            <a href="${pageContext.request.contextPath}/">Home</a>
        </nav>
    </header>
    <div class="card">
        <h1 class="page-title">Could not save</h1>
        <p class="lead">${message}</p>
        <p style="color:var(--muted);font-size:0.9rem">Request path: <c:out value="${path}"/></p>
        <a class="btn btn-ghost" href="javascript:history.back()">Go back</a>
    </div>
</div>
</body>
</html>
