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

<header class="main-header">
    <nav class="site-nav">
        <ul class="site-links">
            <li>
                <a href="../index.jsp" class="site-name">Cinefile</a>
            </li>
            <li>
                <a href="#">Movies, Tv & Showtimes</a>
            </li>
            <li>
                <a href="#">Celebs, Events & Photos</a>
            </li>
            <li>
                <a href="loginForm.jsp">Log in</a>
            </li>
            <li>
                <form class="input-line" action="controller" method="get">
                    <input type="hidden" name="command" value="find">
                    <input type="text" name="movie-to-find" class="text-input"
                           placeholder="Search"/>
                    <button class="find-btn"><i class="fa fa-search"></i></button>
                </form>
            </li>
        </ul>
    </nav>
</header>

<section class="section main">
    <div class="section-title">
        <h2>${queryName}</h2>
    </div>
    <section class="section-movies">
        <c:forEach var="movie" items="${movie}">
            <ul>
                <li>
                    <div class="movie">
                        <a href="#">
                            <h4 class="title"><c:out value="${movie.title}"/></h4>
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
        </c:forEach>
    </section>
</section>

<footer class="main-footer">
    <div class="footer-block">
        <h5 class="footer-title">Subscribe to our newsletter</h5>
        <div class="footer-body">
            <form class="subscribe-form">
                <input type="email" name="subscribe-email" class="email-input" placeholder="Your email address"/>
                <input type="button" name="subscribe-submit" value="Subscribe" class="subscribe-btn"/>
            </form>
        </div>
    </div>

</footer>
</body>
</html>

