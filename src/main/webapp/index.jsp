<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%--<c:set var="locale" value="${pageContext.request.locale}"/>--%>
<fmt:setLocale value="${locale}"/>
<form action="controller">
    <input type="hidden" name="command" value="change_language">
    <select name="language">
        <option value="en_US">English</option>
        <option value="ru_RU">Русский</option>
    </select>
    <button type="submit"><fmt:message key="welcome"/></button>
</form>

<%--Preferred locale: ${locale.displayName}--%>
<%--<br/>--%>
<%--Country: ${locale.displayCountry}--%>
<%--<br/>--%>
<%--Language: ${locale.displayLanguage}--%>
<%--<hr/>--%>

<br/>
<a href="jsp/login.jsp">login</a>
<%--<jsp:forward page="jsp/login.jsp"/>--%>
</body>
</html>
