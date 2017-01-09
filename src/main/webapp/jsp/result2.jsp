<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result page</title>
</head>
<body>
<p>${msg}</p>
<p><strong><c:out value="${user.role}"/> </strong></p>
<p><strong><c:out value="${user.login}"/> </strong></p>
<c:forEach var="review" items="${user.reviews}">
    <hr>
    <p><strong><c:out value="${review.title}"/> </strong></p>

    <p><c:out value="${review.body}"/></p>
    <p><em><c:out value="${review.date}"/> </em></p>
</c:forEach>
<hr>
<hr>

<c:forEach var="u" items="${users}">
    <p><strong>Role: <c:out value="${u.role}"/> </strong></p>
    <p><strong>Login: <c:out value="${u.login}"/> </strong></p>
    <p><strong>Password: <c:out value="${u.password}"/> </strong></p>
    <p><strong><c:out value="${u.email}"/> </strong></p>

    <p><strong>First name: <c:out value="${u.firstName}"/> </strong></p>

    <p><strong>Last name: <c:out value="${u.lastName}"/> </strong></p>

    <p><strong><c:out value="${u.birthday}"/> </strong></p>
    <c:forEach var="review" items="${u.reviews}">
        <hr>
        <p><strong><c:out value="${review.title}"/> </strong></p>

        <p><c:out value="${review.body}"/></p>
        <p><em><c:out value="${review.date}"/> </em></p>
    </c:forEach>
    <hr>
    <hr>
</c:forEach>

<c:forEach var="movie" items="${movie}">
    <p><strong><c:out value="${movie.title}"/></strong></p>
    <p><c:out value="${movie.description}"/></p>
</c:forEach>
<br>
<a href="/index.jsp">back</a>
</body>
</html>
