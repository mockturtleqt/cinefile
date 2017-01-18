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
        <jsp:useBean id="moviePage" scope="page" class="com.epam.web.entity.Movie"/>
        <div class="movie">
            <a href="#">
                <h4 class="title">
                    <jsp:getProperty name="moviePage" property="title"/>
                </h4>
            </a>

            <div class="poster">
                <a href="#">
                    <img src="<jsp:getProperty name="moviePage" property="poster"/>"
                         alt="<jsp:getProperty name="moviePage" property="title"/>"/>
                </a>
            </div>

            <p class="description">
                <jsp:getProperty name="moviePage" property="description"/>
            </p>

            <p>Rating:
                <jsp:getProperty name="moviePage" property="rating"/>
            </p>
        </div>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

