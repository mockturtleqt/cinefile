<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="moviePage" scope="request" class="com.epam.web.entity.Movie"/>
<head>
    <title>${moviePage.title}</title>
    <meta charset="utf-8">
    <%--<link href="../css/moviePage.css" rel="stylesheet"/>--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${locale}"/>

<c:import url="header.jsp"/>
<section class="section main">
    <div class="section-title">
        <h2>${moviePage.title}</h2>
    </div>
    <section class="section-movies">
        <div class="movie">

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

            <c:if test="${not empty moviePage.releaseDate}">
                <p><strong><fmt:message key="release.date"/>: </strong>${moviePage.releaseDate}</p>
            </c:if>

            <c:if test="${not empty moviePage.genre}">
                <p><strong><fmt:message key="genre"/>: </strong>
                    <c:forEach var="genre" items="${moviePage.genre}">
                        ${genre},
                    </c:forEach>
                </p>
            </c:if>

            <c:if test="${not empty moviePage.rating}">
                <p><strong><fmt:message key="rating"/>: </strong>${moviePage.rating}</p>
            </c:if>

            <c:if test="${not empty moviePage.crew}">
                <p><strong><fmt:message key="crew"/>: </strong></p>
                <c:forEach var="mediaPerson" items="${moviePage.crew}">
                    <div class="crew">
                        <p>
                            <a href="controller?command=show_media_person_page&mediaPersonId=${mediaPerson.id}">
                                <c:out value="${mediaPerson.firstName}"/>
                                <c:out value="${mediaPerson.lastName}"/>
                            </a>
                            <br>
                        </p>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${not empty moviePage.reviews}">
                <p><strong><fmt:message key="reviews"/> : </strong></p>
                <c:forEach var="review" items="${moviePage.reviews}">
                    <div class="review" style="background: #d0cecd">
                        <h4><a href="controller?command=show_user_page&userId=${review.userId}"><c:out
                                value="${review.userLogin}"/></a></h4>
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

        </div>
        <a href="${previous_page}"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

