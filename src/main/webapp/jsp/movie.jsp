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
    <section class="section-movies">
        <c:set var="movie" value="${movie}">
            <ul>
                <li>
                    <div class="movie">
                        <a href="#">
                            <c:set var="movieTitle" value="movie.title"/>
                            <h4 class="title"><c:out value="${movieTitle}"/></h4>
                        </a>

                        <div class="poster">
                            <a href="#">
                                <img src="${movie.poster}" alt="${movie.title}"/>
                            </a>
                        </div>

                        <p class="description"><c:out value="${movie.description}"/> </p>
                    </div>
                </li>
            </ul>
        </c:set>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

