<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
    <meta charset="utf-8">
    <link href="css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">

<c:import url="header.jsp"/>
<section class="section main">
    <jsp:useBean id="moviePage" scope="request" class="com.epam.web.entity.Movie"/>
    <div class="section-title">
        <h2>${moviePage.title}</h2>
    </div>
    <section class="section-movies">
        <div class="movie">

            <c:choose>
                <c:when test="${not empty moviePage.poster}">
                    <a href="../jsp/result.jsp">${user}</a>
                </c:when>
                <c:otherwise>
                    <a href="../jsp/loginForm.jsp"><fmt:message key="signup"/></a>
                </c:otherwise>
            </c:choose>

            <c:if test="${not empty moviePage.poster}">
                <div class="poster">
                    <a href="#">
                        <img src="${moviePage.poster}"
                             alt="${moviePage.title}"/>
                    </a>
                </div>
            </c:if>

            <c:if test="${not empty moviePage.description}">
                <p class="description">
                        ${moviePage.description}
                </p>
            </c:if>

            <c:if test="${not empty moviePage.duration}">
                <p>Duration: ${moviePage.duration}</p>
            </c:if>

            <c:if test="${not empty moviePage.releaseDate}">
                <p>Release date: ${moviePage.releaseDate}</p>
            </c:if>

            <c:if test="${not empty moviePage.genre}">
                <p>Genre: ${moviePage.genre}</p>
            </c:if>

            <c:if test="${not empty moviePage.rating}">
                <p>Rating: ${moviePage.rating}</p>
            </c:if>

        </div>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

