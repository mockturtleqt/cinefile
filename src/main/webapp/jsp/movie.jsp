<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="moviePage" scope="request" class="com.epam.web.domain.Movie"/>
<head>
    <title>${moviePage.title}</title>
    <meta charset="utf-8">
    <link href="../css/moviePage.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="../js/updateReview.js"></script>
    <script src="../js/movieRating.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>
<jsp:useBean id="user" class="com.epam.web.domain.User" scope="session"/>

<c:import url="header.jsp"/>
<section class="section main">

    <div class="section-title">
        <h2>${moviePage.title}
            <c:set var="admin" value="ADMIN"/>
            <c:if test="${user.role == admin}">
                <button class="edit-by-admin-btn"><a
                        href="controller?command=show_edit_movie_form&movieId=${moviePage.id}"><i
                        class="fa fa-pencil-square-o"
                        aria-hidden="true"></i></a></button>
            </c:if>
        </h2>
    </div>


    <section class="section-movies">
        <div class="movie">
            <div class="poster">
                <a href="#">
                    <img src="${moviePage.poster}"
                         alt="${moviePage.title}"/>
                </a>
            </div>

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
                <p><strong><fmt:message key="rating"/>: </strong>
                <p id="movieRate">${moviePage.rating}</p>
                </p>
            </c:if>

            <c:set var="userRate" value="${ctg:getUserRate(moviePage.ratingList, user.id)}"/>
            <c:if test="${not empty userRate}">
                <p>Your rate: ${userRate.rate}</p>
            </c:if>
            <form action="controller" method="post" class="movieRatingForm">
                <fieldset class="rating">
                    <input type="radio" id="star10" name="rating" value="10"/>
                    <label class="full" for="star10"
                           title="Awesome - 10 stars">10</label>

                    <input type="radio" id="star9" name="rating" value="9"/>
                    <label class="full" for="star9"
                           title="Really good - 9 stars">9</label>

                    <input type="radio" id="star8" name="rating" value="8"/>
                    <label class="full" for="star8"
                           title="Pretty good - 8 stars">8</label>

                    <input type="radio" id="star7" name="rating" value="7"/>
                    <label class="full" for="star7"
                           title="I'd watch it again with a beer - 7 stars">7</label>

                    <input type="radio" id="star6" name="rating" value="6"/>
                    <label class="full" for="star6"
                           title="Not so bad - 6 stars">6</label>

                    <input type="radio" id="star5" name="rating" value="5"/>
                    <label class="full" for="star5"
                           title="Kinda bad - 5 stars">5</label>

                    <input type="radio" id="star4" name="rating" value="4"/>
                    <label class="full" for="star4"
                           title="Bad - 4 stars">4</label>

                    <input type="radio" id="star3" name="rating" value="3"/>
                    <label class="full" for="star3"
                           title="Really bad - 3 stars">3</label>

                    <input type="radio" id="star2" name="rating" value="2"/>
                    <label class="full" for="star2"
                           title="Lame - 2 stars">2</label>

                    <input type="radio" id="star1" name="rating" value="1"/>
                    <label class="full" for="star1"
                           title="Sucks big time - 1 star">1</label>
                </fieldset>

                <c:choose>
                    <c:when test="${empty userRate}">
                        <input type="hidden" name="command" value="create_movie_rating"/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="command" value="update_movie_rating"/>
                    </c:otherwise>
                </c:choose>

                <input type="hidden" name="movieRatingId" value="${userRate.id}"/>
                <input type="hidden" name="movieId" value="${moviePage.id}"/>
                <input type="hidden" name="userId" id="userId" value="${user.id}"/>
            </form>

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


            <c:set var="addedReview" value="false"/>

            <c:if test="${not empty moviePage.reviews}">
                <p><strong><fmt:message key="reviews"/> : </strong></p>
                <c:forEach var="review" items="${moviePage.reviews}">
                    <div class="review">
                        <h4><a href="controller?command=show_user_page&userId=${review.userId}"><c:out
                                value="${review.userLogin}"/></a></h4>

                        <div class="btn-row">
                            <c:if test="${user.id == review.userId}">

                                <div class="btn">
                                    <button class="edit-btn" id="${review.id}"><i class="fa fa-pencil-square-o"
                                                                                  aria-hidden="true"></i></button>
                                </div>

                                <form action="controller" method="post" class="delete-review-form">
                                    <input type="hidden" name="command" value="delete_review"/>
                                    <input type="hidden" name="reviewId" value="${review.id}"/>
                                    <div class="btn">
                                        <button class="delete-btn"><i class="fa fa-trash-o" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                </form>

                                <form action="controller" method="post" id="edit-review-form-${review.id}">
                                    <input type="hidden" name="reviewId" value="${review.id}"/>
                                    <input type="hidden" name="command" value="update_review"/>
                                    <input type="hidden" name="review"/>

                                    <div class="btn">
                                        <button class="save-btn" name="save-btn" id="save-${review.id}"><i
                                                class="icon-save"></i>
                                        </button>
                                    </div>

                                </form>
                                <c:set var="addedReview" value="true"/>
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

            <c:forEach var="validationException" items="${requestScope.validationExceptions}">
                <h4>${validationException}</h4>
            </c:forEach>

            <c:if test="${user.id != 0 and not addedReview and not user.isBanned}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="create_review"/>
                    <input type="hidden" name="user-id" value="${user.id}"/>
                    <input type="hidden" name="movie-id" value="${moviePage.id}"/>
                    <input type="text" required name="review-title-input" class="review-title-input"
                           placeholder="<fmt:message key="review.title"/>"/>
                    <textarea required cols="60" rows="5" name="review-body-input" class="review-body-input"
                              placeholder="<fmt:message key="review.body"/> "></textarea>
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

