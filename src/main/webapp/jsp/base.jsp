<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movies</title>
    <meta charset="utf-8">
    <link href="../css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">

<c:import url="header.jsp"/>
<section class="section main">
    <div class="section-title">
        <h2>
            ${requestScope.queryName}
        </h2>
    </div>
    <section class="section-movies">
        <c:forEach var="movie" items="${requestScope.movie}">
            <ul>
                <li>
                    <div class="movie">
                        <a href="controller?command=show_movie_page&movieId=${movie.id}">
                            <h4 class="title"><c:out value="${movie.title}"/></h4>
                        </a>

                        <div class="poster">
                            <a href="#">
                                <img src="${movie.poster}" alt="${movie.title}"/>
                            </a>
                        </div>

                        <p class="description"><c:out value="${movie.description}"/></p>
                    </div>
                </li>
            </ul>
        </c:forEach>
        <a href="${requestScope.previous_page}"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

