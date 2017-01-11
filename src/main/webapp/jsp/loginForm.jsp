<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../css/registrationStyle.css" rel="stylesheet"/>
    <link href="../css/style.css" rel="stylesheet"/>
    <script src="../js/validation.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Registration</title>
</head>
<body class="home">
<fmt:setLocale value="${locale}"/>
<header class="main-header">
    <nav class="site-nav">
        <ul class="site-links">
            <li>
                <a href="../index.jsp" class="site-name">Cinefile</a>
            </li>
            <li>
                <form class="input-line" action="controller" method="post">
                    <input type="hidden" name="command" value="login">
                    <input type="text" class="login text-input" name="known-login"
                           placeholder=
                                   "<fmt:message key="username"/>" pattern="^([a-zA-Z]+)[a-zA-Z\d_]{4,}$"
                           title="<fmt:message key="login.requirements"/>"
                           required/>
                    <input type="password" class="password text-input" name="known-password"
                           placeholder="<fmt:message key="password"/>"
                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"
                           title="<fmt:message key="password.requirements"/>"
                           required/>
                    <input type="submit" class="sign-up-btn find-btn" value=
                            "<fmt:message key="login.btn"/>">
                </form>
            </li>
        </ul>
    </nav>
</header>
<div class="registration-page">
    <div class="greeting">
        <h2><fmt:message key="registration.greeting.pt1"/></h2>
        <p><fmt:message key="registration.greeting.pt2"/></p>
    </div>
    <form class="registration-form" action="controller" method="post">
        <input type="hidden" name="command" value="sign-in-user">
        <div class="field required">
            <input type="text" class="login" name="login"
                   placeholder="<fmt:message key="username"/>"
                   pattern="^([a-zA-Z]+)[a-zA-Z\d_]{4,}$"
                   title="<fmt:message key="login.requirements"/>"
                   required/>
        </div>
        <div class="field required">
            <input type="password" id="password" class="password" name="password"
                   placeholder="<fmt:message key="password"/>"
                   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"
                   title="<fmt:message key="password.requirements"/>"
                   required/>
        </div>
        <div class="field required">
            <input type="password" id="confirm-password" class="confirm-password"
                   placeholder="<fmt:message key="confirm.password"/>"
                   required/>
        </div>

        <div class="field optional email-container">
            <input type="email" class="email" name="email"
                   title="<fmt:message key="email.requirements"/>"
                   placeholder="Email"/>
        </div>

        <div class="field optional">
            <input type="text" class="first-name" name="first-name"
                   placeholder="<fmt:message key="first.name"/>">
        </div>

        <div class="field optional">
            <input type="text" class="last-name" name="last-name"
                   placeholder="<fmt:message key="last.name"/>">
        </div>

        <input type="submit" class="sign-up-btn" value="<fmt:message key="signup.btn"/>">
    </form>
</div>
</body>
</html>
