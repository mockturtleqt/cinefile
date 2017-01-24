<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="moviePage" scope="request" class="com.epam.web.entity.Movie"/>
<head>
    <title>${moviePage.title}</title>
    <meta charset="utf-8">
    <link href="../css/moviePage.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="../js/updateReview.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>

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
            <fieldset class="rating">
                <input type="radio" id="star10" name="rating" value="10"/>
                <label class="full" for="star10"
                       title="Awesome - 10 stars">10</label>

                <input type="radio" id="star9" name="rating" value="9"/>
                <label class="full" for="star9"
                       title="Awesome - 9 stars">9</label>

                <input type="radio" id="star8" name="rating" value="8"/>
                <label class="full" for="star8"
                       title="Awesome - 8 stars">8</label>

                <input type="radio" id="star7" name="rating" value="7"/>
                <label class="full" for="star7"
                       title="Awesome - 5 stars">7</label>

                <input type="radio" id="star6" name="rating" value="6"/>
                <label class="full" for="star6"
                       title="Awesome - 5 stars">6</label>

                <input type="radio" id="star5" name="rating" value="5"/>
                <label class="full" for="star5"
                       title="Awesome - 5 stars">5</label>

                <input type="radio" id="star4" name="rating" value="4"/>
                <label class="full" for="star4"
                       title="Pretty good - 4 stars">4</label>

                <input type="radio" id="star3" name="rating" value="3"/>
                <label class="full" for="star3"
                       title="Meh - 3 stars">3</label>

                <input type="radio" id="star2" name="rating" value="2"/>
                <label class="full" for="star2"
                       title="Kinda bad - 2 stars">2</label>

                <input type="radio" id="star1" name="rating" value="1"/>
                <label class="full" for="star1"
                       title="Sucks big time - 1 star">1</label>

            </fieldset>
            <br/>
            <br/>
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

            <jsp:useBean id="user" class="com.epam.web.entity.User" scope="session"/>
            <%--<c:set var="user" value="${sessionScope.user}"/>--%>
            <c:set var="addedReview" value="false"/>

            <c:if test="${not empty moviePage.reviews}">
                <p><strong><fmt:message key="reviews"/> : </strong></p>
                <c:forEach var="review" items="${moviePage.reviews}">
                    <div class="review">
                        <h4><a href="controller?command=show_user_page&userId=${review.userId}"><c:out
                                value="${review.userLogin}"/></a></h4>
                        <c:if test="${user.id == review.userId}">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="delete_review"/>
                                <input type="hidden" name="reviewId" value="${review.id}"/>
                                <input type="submit" value="Delete"/>
                            </form>
                            <input type="button" class="edit-btn" value="Edit"/>
                            <form action="controller" method="post" class="edit-review-form">
                                <input type="hidden" name="reviewId" value="${review.id}"/>
                                <input type="hidden" name="command" value="update_review"/>
                                <input type="hidden" name="review"/>
                                <input type="submit" class="save-btn" name="save-btn" value="Save"/>
                            </form>
                            <c:set var="addedReview" value="true"/>
                        </c:if>
                        <h3 class="review-title"><c:out value="${review.title}"/></h3>
                        <p class="review-body">
                            <c:out value="${review.body}"/>
                        </p>
                        <p>
                            <c:out value="${review.date}"/>
                            <br>
                            <br>
                        </p>

                    </div>
                </c:forEach>
            </c:if>


            <c:if test="${user.id != 0 and not addedReview}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="add_review"/>
                    <input type="hidden" name="user-id" value="${user.id}"/>
                    <input type="hidden" name="movie-id" value="${moviePage.id}"/>
                    <input type="text" name="review-title-input" class="review-title-input"
                           placeholder="<fmt:message key="review.title"/>"/>
                    <input type="text" name="review-body-input" class="review-body-input"
                           placeholder="<fmt:message key="review.body"/> ">
                    <input type="submit" class="leave-review-btn" value="<fmt:message key="leave.review"/> ">
                </form>
            </c:if>

        </div>
        <a href="${requestScope.previous_page}"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

