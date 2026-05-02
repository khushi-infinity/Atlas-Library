<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Library — Home</title>
    <jsp:include page="_header.jsp"/>
</head>
<body>
<div class="shell">
    <header class="site-header">
        <div class="brand">Atlas <span>Library</span></div>
        <nav class="site-nav">
            <a href="${pageContext.request.contextPath}/authors">Authors</a>
            <a href="${pageContext.request.contextPath}/books">Books</a>
        </nav>
    </header>

    <div class="card">
        <h1 class="page-title">Welcome</h1>
        <p class="lead">Manage authors and books: create records, browse lists (books load via an inner join query), and update details.</p>
        <div class="home-grid">
            <div class="home-tile">
                <h2>Authors</h2>
                <p>Ten sample authors are preloaded. Add more or edit names and emails.</p>
                <p><a href="${pageContext.request.contextPath}/authors">Open authors →</a></p>
            </div>
            <div class="home-tile">
                <h2>Books</h2>
                <p>Each book links to one author. The book list uses a custom repository join.</p>
                <p><a href="${pageContext.request.contextPath}/books">Open books →</a></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
