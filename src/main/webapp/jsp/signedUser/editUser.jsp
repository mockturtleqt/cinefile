<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../../css/editPage.css" rel="stylesheet"/>
    <link href="../../css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Edit user</title>
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>
<c:import url="../header.jsp"/>
<div class="edit-page">
    <div class="greeting">
        <h2>Edit user</h2>
    </div>

    <c:forEach var="validationException" items="${requestScope.validationExceptions}">
        <h4>${validationException}</h4>
    </c:forEach>

    <form class="edit-form" action="/controller" method="post">
        <input type="hidden" name="command" value="update_user"/>
        <div class="block">
            <label for="first-name">First name</label><br/>
            <input type="text" id="first-name" class="first-name" name="first-name"/>
        </div>

        <div class="block">
            <label for="last-name">Last name</label><br/>
            <input type="text" id="last-name" class="last-name" name="last-name"/>
        </div>

        <div class="block">
            <label for="email">Email</label><br/>
            <input type="email" id="email" class="email" name="email"/>
        </div>

        <div class="block">
            <label for="gender-div">Gender</label>
            <div id="gender-div">
                <div class="block-div">
                    <input type="radio" id="male" name="gender" value="male"/>
                    <label for="male">Male</label>
                </div>

                <div class="block-div">
                    <input type="radio" id="female" name="gender" value="female"/>
                    <label for="female">Female</label>
                </div>
            </div>
        </div>

        <div class="block">
            <label for="birthday">Birthday</label><br/>
            <input type="date" id="birthday" name="birthday"/>
        </div>

        <div class="block">
            <label for="picture">Picture</label><br/>
            <input type="text" id="picture" name="picture"/>
        </div>

        <div class="block">
            <input type="submit" class="edit-btn" value="Edit">
        </div>
    </form>
    <div class="block">
        <a href="${requestScope.previous_page}"><fmt:message key="back"/></a>
    </div>
</div>
<c:import url="../footer.jsp"/>
</body>
</html>
