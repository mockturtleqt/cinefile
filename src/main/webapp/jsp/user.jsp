<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="userPage" scope="request" class="com.epam.web.entity.User"/>
<head>
    <title>${userPage.login}</title>
    <meta charset="utf-8">
    <%--<link href="../css/moviePage.css" rel="stylesheet"/>--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${locale}"/>

<c:import url="header.jsp"/>
<section class="section main">
    <div class="section-title">
        <h2>${userPage.login}</h2>
    </div>
    <section class="section-movies">
        <div class="movie">

            <c:if test="${not empty userPage.picture}">
                <div class="poster">
                    <a href="#">
                        <img src="${userPage.picture}"
                             alt="${userPage.login}"/>
                    </a>
                </div>
            </c:if>

            <c:if test="${not empty userPage.firstName}">
                <p><strong><fmt:message key="first.name"/>: </strong>${userPage.firstName}</p>
            </c:if>

            <c:if test="${not empty userPage.lastName}">
                <p><strong><fmt:message key="last.name"/>: </strong>${userPage.lastName}</p>
            </c:if>

            <c:if test="${not empty userPage.email}">
                <p><strong><fmt:message key="user.email"/>: </strong>${userPage.email}</p>
            </c:if>

            <c:if test="${not empty userPage.gender}">
                <p><strong><fmt:message key="gender"/>: </strong>${userPage.gender}</p>
            </c:if>

            <c:if test="${not empty userPage.birthday}">
                <p><strong><fmt:message key="birthday"/>: </strong>${userPage.birthday}</p>
            </c:if>

            <c:if test="${not empty userPage.reviews}">
                <p><strong><fmt:message key="reviews"/> : </strong></p>
                <c:forEach var="review" items="${userPage.reviews}">
                    <div class="review" style="background: #d0cecd">
                        <h4><a href="controller?command=show_movie_page&movieId=${review.movieId}"><c:out
                                value="${review.movieTitle}"/></a></h4>
                        <h3><c:out value="${review.title}"/></h3>
                        <p>
                            <c:out value="${review.body}"/>
                            <br/>
                            <c:out value="${review.date}"/>
                            <br>
                            <br>
                        </p>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${not empty userPage.ratings}">
                <p><strong><fmt:message key="ratings"/>: </strong></p>
                <c:forEach var="rating" items="${userPage.ratings}">
                    <div class="rating">
                        <p>
                            <a href="controller?command=show_movie_page&movieId=${rating.movieId}">${rating.movieTitle}</a>
                            : ${rating.rate}
                            <br>
                        </p>
                    </div>
                </c:forEach>
            </c:if>

        </div>
        <a href="${previous_page}"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

