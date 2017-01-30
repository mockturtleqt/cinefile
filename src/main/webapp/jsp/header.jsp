<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cinefile</title>
    <link href="../css/style.css" rel="stylesheet"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="Home">
<fmt:setLocale value="${sessionScope.locale}"/>
<header class="main-header">
    <nav class="site-nav">
        <ul class="site-links">
            <li>
                <a href="../index.jsp" class="site-name">Cinefile</a>
            </li>
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="change_language">
                    <select title="language-select" class="language-select" name="language"
                            onchange="this.form.submit()">
                        <option selected disabled><fmt:message key="language"/></option>
                        <option value="en_US">English</option>
                        <option value="ru_RU">Русский</option>
                    </select>
                </form>
            </li>
            <li>
                <a href="controller?command=find_all_movies"><fmt:message key="movie"/> </a>
            </li>
            <li>
                <a href="controller?command=find_all_media_people"><fmt:message key="celebs"/></a>
            </li>

            <%--<jsp:useBean id="user" scope="session" class="com.epam.web.entity.User"/>--%>
            <c:set var="user" value="${sessionScope.user}"/>
            <c:choose>
                <c:when test="${not empty user.login}">
                    <li>
                        <a href="controller?command=show_user_page&userId=${user.id}">${user.login}</a>
                    </li>
                    <li>
                        <a href="controller?command=logout"><fmt:message key="logout"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="../jsp/loginForm.jsp"><fmt:message key="signup"/></a>
                    </li>
                </c:otherwise>
            </c:choose>

            <li>
                <form action="controller" method="get" class="input-line">
                    <input type="hidden" name="command" value="find_movies_by_name"/>
                    <input type="text" class="text-input" name="movie-to-find"
                           placeholder="<fmt:message key="search"/>">
                    <button class="find-btn"><i class="fa fa-search"></i></button>
                </form>
            </li>
        </ul>
    </nav>
</header>
</body>
</html>
