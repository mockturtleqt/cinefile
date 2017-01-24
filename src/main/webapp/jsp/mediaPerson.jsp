<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="mediaPersonPage" scope="request" class="com.epam.web.entity.MediaPerson"/>
<head>
    <title>${mediaPersonPage.firstName} ${mediaPersonPage.lastName}</title>
    <meta charset="utf-8">
    <%--<link href="../css/moviePage.css" rel="stylesheet"/>--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>

<c:import url="header.jsp"/>
<section class="section main">

    <div class="section-title">
        <h2>${mediaPersonPage.firstName} ${mediaPersonPage.lastName}</h2>
    </div>
    <section class="section-movies">
        <div class="movie">

            <c:if test="${not empty mediaPersonPage.picture}">
                <div class="poster">
                    <a href="#">
                        <img src="${mediaPersonPage.picture}"
                             alt="${mediaPersonPage.firstName} ${mediaPersonPage.lastName}"/>
                    </a>
                </div>
            </c:if>

            <c:if test="${not empty mediaPersonPage.bio}">
                <p class="description">
                        ${mediaPersonPage.bio}
                </p>
            </c:if>

            <c:if test="${not empty mediaPersonPage.occupation}">
                <p><strong><fmt:message key="occupation"/>: </strong>
                    <c:forEach var="occupation" items="${mediaPersonPage.occupation}">
                        ${occupation},
                    </c:forEach>
                </p>
            </c:if>

            <c:if test="${not empty mediaPersonPage.gender}">
                <p><strong><fmt:message key="gender"/>: </strong>${mediaPersonPage.gender}</p>
            </c:if>


            <c:if test="${not empty mediaPersonPage.birthday}">
                <p><strong><fmt:message key="birthday"/>: </strong>${mediaPersonPage.birthday}</p>
            </c:if>

            <c:if test="${not empty mediaPersonPage.movies}">
                <p><strong><fmt:message key="movies"/>: </strong></p>
                <c:forEach var="movie" items="${mediaPersonPage.movies}">
                    <div class="movie">
                        <p>
                            <a href="controller?command=show_movie_page&movieId=${movie.id}">
                                <c:out value="${movie.title}"/>
                            </a>
                            <br>
                        </p>
                    </div>
                </c:forEach>
            </c:if>

        </div>
        <a href="${requestScope.previous_page}"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

