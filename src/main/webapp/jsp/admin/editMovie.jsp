<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../../css/editPage.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Edit movie</title>
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>
<div class="edit-page">
    <div class="greeting">
        <h2>Edit movie</h2>
    </div>

    <form class="edit-form" action="/controller" method="post">
        <c:choose>
            <c:when test="${empty requestScope.movie}">
                <input type="hidden" name="command" value="create_movie"/>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="command" value="update_movie"/>
                <input type="hidden" name="movie-id" value="${requestScope.movie.id}"/>
            </c:otherwise>
        </c:choose>
        <div class="block">
            <label for="title">Title</label><br>
            <input type="text" id="title" class="title" name="title" value="${requestScope.movie.title}"/>
        </div>

        <div class="block">
            <label for="release-date">Release date</label><br>
            <input type="date" id="release-date" name="release-date" value="${requestScope.movie.releaseDate}"/>
        </div>

        <div class="block">
            <label for="description">Description</label><br/>
            <textarea id="description" name="description" cols="40"
                      rows="5">${requestScope.movie.description}</textarea>
        </div>

        <div class="block genre">
            <label for="genre-div">Genre</label>
            <div id="genre-div" class="genre-div">
                <c:forEach var="genre" items="${requestScope.genreType}">
                    <div class="block-div">
                        <input type="checkbox" id="${genre}" name="genre"
                               value="${genre}"/>
                        <label for="${genre}">${genre}</label>
                    </div>
                </c:forEach>

                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="comedy" name="genre" value="COMEDY"/>--%>
                    <%--<label for="comedy">Comedy</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="romance" name="genre" value="romance"/>--%>
                    <%--<label for="romance">Romance</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="thriller" name="genre" value="thriller"/>--%>
                    <%--<label for="thriller">Thriller</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="cartoon" name="genre" value="cartoon"/>--%>
                    <%--<label for="cartoon">Cartoon</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="drama" name="genre" value="drama"/>--%>
                    <%--<label for="drama">Drama</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="fantasy" name="genre" value="fantasy"/>--%>
                    <%--<label for="fantasy">Fantasy</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="documentary" name="genre" value="documentary"/>--%>
                    <%--<label for="documentary">Documentary</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="crime" name="genre" value="crime"/>--%>
                    <%--<label for="crime">Crime</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="action" name="genre" value="action"/>--%>
                    <%--<label for="action">Action</label>--%>
                <%--</div>--%>
                <%--<div class="block-div">--%>
                    <%--<input type="checkbox" id="adventure" name="genre" value="adventure"/>--%>
                    <%--<label for="adventure">Adventure</label>--%>
                <%--</div>--%>
            </div>
        </div>

        <div class="block">
            <label for="poster">Poster</label><br/>
            <input type="text" id="poster" name="poster" value="${requestScope.movie.poster}"/>
        </div>

        <div class="block">
            <input type="submit" class="edit-btn" value="Edit">
        </div>
    </form>
    <div class="block">
        <a href="${requestScope.previous_page}"><fmt:message key="back"/></a>
    </div>
</div>
</body>
</html>
