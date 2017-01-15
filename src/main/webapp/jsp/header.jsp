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
<fmt:setLocale value="${locale}"/>
<header class="main-header">
    <nav class="site-nav">
        <ul class="site-links">
            <li>
                <a href="../index.jsp" class="site-name">Cinefile</a>
            </li>
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="change_language">
                    <select class="language-select" name="language" onchange="this.form.submit()">
                        <option selected disabled><fmt:message key="language"/> </option>
                        <option value="en_US">English</option>
                        <option value="ru_RU">Русский</option>
                    </select>
                </form>
            </li>
            <li>
                <a href="#"><fmt:message key="movie"/> </a>
            </li>
            <li>
                <a href="#"><fmt:message key="celebs"/></a>
            </li>
            <li>
                <c:set var="user" value="${user}"/>
                <c:choose>
                    <c:when test="${not empty user}">
                        <a href="../jsp/result.jsp">${user}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="../jsp/loginForm.jsp"><fmt:message key="signup"/></a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <form action="controller" method="get" class="input-line">
                    <input type="hidden" name="command" value="find"/>
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
