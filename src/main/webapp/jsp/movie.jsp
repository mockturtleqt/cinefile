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

            <div class="poster">
                <a href="#">
                    <img src="${moviePage.poster}"
                         alt="${moviePage.title}"/>
                </a>
            </div>

            <p class="description">
                ${moviePage.description}
            </p>

            <p>Rating:
                ${moviePage.rating}
            </p>
        </div>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

