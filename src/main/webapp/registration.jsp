<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.RegisterPage"/></title>
    <link rel="stylesheet" href="background.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
</head>
<body>
<form name="RegisterForm" action="/aircost/start" method="post">
    <br class="container">
    <p><fmt:message bundle="${rs}" key="language.FormInfo"/></p>
    <hr>
    <label>
        <input type="text" class="inbox" placeholder="<fmt:message bundle="${rs}" key="language.Name"/>" name="name">
    </label>
    <br>
    <label>
        <input type="text" class="inbox" placeholder="<fmt:message bundle="${rs}" key="language.Surname"/>" name="surname">
    </label>
    <br>
    <label>
        <input type="text" class="inbox" placeholder="<fmt:message bundle="${rs}" key="language.Passport"/>" name="passport">
    </label>
    <br>
    <label>
        <input type="text" class="inbox" placeholder="<fmt:message bundle="${rs}" key="language.Email"/>" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="email">
    </label>
    <br>
    <label>
        <input type="password" placeholder="<fmt:message bundle="${rs}" key="language.Password"/>" name="password">
    </label>
    <hr>
    <button type="submit"><fmt:message bundle="${rs}" key="language.Register"/></button>
    </div>
    <div class="container signin">
        <p><fmt:message bundle="${rs}" key="language.AlreadyHaveAccount"/><a href="/aircost/start?command=login_page"><fmt:message bundle="${rs}" key="language.SignIn"/></a></p>
    </div>
    <c:if test="${error == 1}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.EmailAlreadyTakenError"/></p>
    </c:if>
    <c:if test="${error == 2}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.EmptyFieldsError"/></p>
    </c:if>
    <input type="hidden" name="command" value="register">
</form>
<br/>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
