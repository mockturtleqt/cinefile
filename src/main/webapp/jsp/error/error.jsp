<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <meta charset="utf-8">
    <link href="../../css/errorPage.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>

<c:import url="../header.jsp"/>
<section class="section main">

    <div class="section-title">
        <h4><fmt:message key="error.short.msg"/><br/></h4>
    </div>
    <section class="section-movies">
        <div class="movie">
            <p><fmt:message key="error.long.msg"/></p>
            ${requestScope.errorMsg}<br/>
        </div>
        <div class="depressed-cat">
            <img src="http://download-telegram.ru/wp-content/uploads/2015/06/Pusheen-30.png">
        </div>
        <a href="/index.jsp"><fmt:message key="index.page.path"/></a><br/>
        <a href="${requestScope.previous_page}"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="../footer.jsp"/>
</body>
</html>

