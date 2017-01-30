<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../../css/editPage.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Edit media person</title>
</head>
<body class="home">
<fmt:setLocale value="${sessionScope.locale}"/>
<div class="edit-page">
    <div class="greeting">
        <h2>Edit media person</h2>
        <h2>${requestScope.mediaPerson}</h2>
    </div>

    <form class="edit-form" action="/controller" method="post">
        <c:choose>
            <c:when test="${empty requestScope.mediaPerson}">
                <input type="hidden" name="command" value="create_media_person"/>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="media-person-id" value="${requestScope.mediaPerson.id}"/>
                <input type="hidden" name="command" value="update_media_person"/>
            </c:otherwise>
        </c:choose>
        <div class="block">
            <label for="first-name">First name</label><br/>
            <input type="text" id="first-name" class="first-name" name="first-name"
                   value="${requestScope.mediaPerson.firstName}">
        </div>

        <div class="block">
            <label for="last-name">Last name</label><br/>
            <input type="text" id="last-name" class="last-name" name="last-name"
                   value="${requestScope.mediaPerson.lastName}"/>
        </div>

        <div class="block">
            <label for="bio">Biography</label><br/>
            <textarea id="bio" name="bio" cols="40" rows="5">${requestScope.mediaPerson.bio}</textarea>
        </div>

        <div class="block occupation">
            <label for="occupation-div">Occupation</label>
            <div id="occupation-div" class="occupation-div">
                <c:forEach var="occupation" items="${requestScope.occupationType}">
                    <div class="block-div">
                        <c:choose>
                            <c:when test="${not empty requestScope.mediaPerson.occupation and
                                            ctg:containsOccupationType(requestScope.mediaPerson.occupation, occupation)}">
                                <input type="checkbox" id="${occupation}" name="occupation" checked="checked"
                                       value="${occupation}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="${occupation}" name="occupation"
                                       value="${occupation}"/>
                            </c:otherwise>
                        </c:choose>
                        <label for="${occupation}">${occupation}</label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="block">
            <label for="gender-div">Gender</label>
            <div id="gender-div">

                <c:forEach var="gender" items="${requestScope.genderType}">
                    <div class="block-div">
                        <c:choose>
                            <c:when test="${not empty requestScope.mediaPerson.gender and
                                            ctg:equalsGender(gender, requestScope.mediaPerson.gender)}">
                                <input type="radio" id="${gender}" name="gender" value="${gender}" checked="checked"/>
                            </c:when>
                            <c:otherwise>
                                <input type="radio" id="${gender}" name="gender" value="${gender}"/>
                            </c:otherwise>
                        </c:choose>
                        <label for="${gender}">${gender}</label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="block">
            <label for="birthday">Birthday</label><br/>
            <input type="date" id="birthday" name="birthday" value="${requestScope.mediaPerson.birthday}"/>
        </div>

        <div class="block">
            <label for="picture">Picture</label><br/>
            <input type="text" id="picture" name="picture" value="${requestScope.mediaPerson.picture}"/>
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
