<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="userPage" scope="request" class="com.epam.web.domain.User"/>
<head>
    <title>${userPage.login}</title>
    <meta charset="utf-8">
    <link href="../css/moviePage.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="../js/updateReview.js"></script>
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>

<c:import url="header.jsp"/>
<section class="section main">
    <div class="section-title">
        <h2>${userPage.login}</h2>
    </div>
    <section class="section-movies">
        <div class="movie">
            <c:set var="admin" value="ADMIN"/>
            <c:if test="${userPage.role == admin}">
                <h3><a href="controller?command=show_edit_media_person_form">Add media person</a></h3>
                <h3><a href="controller?command=show_edit_movie_form">Add movie</a></h3>
            </c:if>

            <div class="poster">
                <a href="#">
                    <img src="${userPage.picture}"
                         alt="${userPage.login}"/>
                </a>
            </div>

            <c:if test="${not empty userPage.userRating}">
                <p><strong><fmt:message key="user.rating"/>: </strong>${userPage.userRating}</p>
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

            <c:forEach var="validationException" items="${requestScope.validationExceptions}">
                <h4>${validationException}</h4>
            </c:forEach>

            <c:if test="${not empty userPage.reviews}">
                <p><strong><fmt:message key="reviews"/> : </strong></p>
                <c:forEach var="review" items="${userPage.reviews}">
                    <div class="review" style="background: #d0cecd">
                        <h4><a href="controller?command=show_movie_page&movieId=${review.movieId}"><c:out
                                value="${review.movieTitle}"/></a></h4>

                        <div class="btn-row">
                            <c:if test="${sessionScope.user.id == userPage.id}">
                                <button class="edit-btn" id="${review.id}"><i class="fa fa-pencil-square-o"
                                                                              aria-hidden="true"></i></button>

                                <form action="controller" method="post" class="delete-review-form">
                                    <input type="hidden" name="command" value="delete_review"/>
                                    <input type="hidden" name="reviewId" value="${review.id}"/>
                                    <button class="delete-btn"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                                </form>

                                <form action="controller" method="post" id="edit-review-form-${review.id}">
                                    <input type="hidden" name="reviewId" value="${review.id}"/>
                                    <input type="hidden" name="command" value="update_review"/>
                                    <input type="hidden" name="review"/>
                                    <button class="save-btn" name="save-btn" id="save-${review.id}"><i
                                            class="icon-save"></i>
                                    </button>
                                </form>
                            </c:if>
                        </div>

                        <h3 id="title-${review.id}" class="review-title"><c:out value="${review.title}"/></h3>
                        <p id="body-${review.id}" class="review-body"><c:out value="${review.body}"/></p>
                        <p>
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
                <c:if test="${sessionScope.user.id == userPage.id}">
                    <form action="controller" method="post" class="delete-rating-form">
                        <input type="hidden" name="command" value="delete_movie_rating"/>
                        <input type="hidden" name="rating-id" value="${rating.id}"/>
                        <div class="btn">
                            <button class="delete-rating-btn"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                        </div>
                    </form>
                </c:if>
                <p>
                    <a href="controller?command=show_movie_page&movieId=${rating.movieId}">
                            ${rating.movieTitle}
                    </a>
                    : ${rating.rate}
                    <br>
                </p>

            </c:forEach>
        </div>
        </c:if>

        <a href="${requestScope.previous_page}"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

