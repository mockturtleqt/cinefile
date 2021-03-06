<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movies</title>
    <meta charset="utf-8">
    <link href="../css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>
<c:import url="header.jsp"/>
<section class="section main">
    <div class="section-title">
        <h2><fmt:message key="celebs"/></h2>
    </div>
    <section class="section-movies">
        <ol>
            <c:forEach var="mediaPerson" items="${requestScope.mediaPerson}">
                <li>
                    <div class="movie">
                        <a href="controller?command=show_media_person_page&mediaPersonId=${mediaPerson.id}">
                            <h4 class="title"><c:out value="${mediaPerson.firstName} ${mediaPerson.lastName}"/></h4>
                        </a>

                        <div class="poster">
                            <a href="#">
                                <img src="${mediaPerson.picture}"
                                     alt="${mediaPerson.firstName} ${mediaPerson.lastName}"/>
                            </a>
                        </div>

                        <p class="description"><c:out value="${mediaPerson.bio}"/></p>
                    </div>
                </li>
            </c:forEach>
        </ol>
        <a href="${requestScope.previous_page}"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

