<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<form name="LoginForm" method="post" action="controller">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="login"/> <br/>
    <input type="text" name="login" value=""/> <br/>
    <fmt:message key="password"/> <br/>
    <input type="password" name="password" value=""/> <br/>
    ${errorLoginPassMsg} <br/>
    ${wrongAction} <br/>
    ${nullPage} <br/>
    <input type="submit" value="Log in"/>
    <a href="../index.jsp">index page</a>

</form>
<hr/>
</body>
</html>
