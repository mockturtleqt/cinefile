<%--<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

    <%--Request from ${pageContext.errorData.requestURI} is failed <br/>--%>
    <%--Servlet name ${pageContext.errorData.servletName} <br/>--%>
    <%--Status code ${pageContext.errorData.statusCode} <br/>--%>
    <%--Exception ${pageContext.exception} <br/>--%>
    <%--Message from exception ${pageContext.exception.message}--%>
<%--</body>--%>
<%--</html>--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <meta charset="utf-8">
    <%--<link href="../css/moviePage.css" rel="stylesheet"/>--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${locale}"/>

<c:import url="../header.jsp"/>
<section class="section main">

    <div class="section-title">
        <h2>Error occurred!<br/></h2>
    </div>
    <section class="section-movies">
        <div class="movie">

            Request from ${pageContext.errorData.requestURI} is failed <br/>
            Servlet name ${pageContext.errorData.servletName} <br/>
            Status code ${pageContext.errorData.statusCode} <br/>
            Exception ${pageContext.exception} <br/>
            Message from exception ${pageContext.exception.message}

        </div>
        <a href="#"><fmt:message key="back"/></a>
    </section>
</section>
<c:import url="../footer.jsp"/>
</body>
</html>

