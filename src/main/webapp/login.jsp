<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.LogInPage"/></title>
    <link rel="stylesheet" href="background.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
</head>
<body>
<form name="LoginForm" action="/aircost/start" method="post">
    <div class="container">
        <img class="logo" src="http://pngimg.com/uploads/padlock/padlock_PNG103184.png"><h1 class="display-4 fw-normal text-white"><fmt:message bundle="${rs}" key="language.LogInForm"/></h1>
        <p class="fs-5 text-muted"><fmt:message bundle="${rs}" key="language.FormInfo"/></p>
        <label>
            <input type="text" placeholder="<fmt:message bundle="${rs}" key="language.Email"/>"
                   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="email">
        </label>
        <label>
            <input type="password" placeholder="<fmt:message bundle="${rs}" key="language.Password"/>" name="password">
        </label>
        <button type="submit"><fmt:message bundle="${rs}" key="language.SignIn"/></button>
    </div>
    <div class="container signing">

        <p><fmt:message bundle="${rs}" key="language.NoAccount"/> <a href="/aircost/start?command=register_page"><fmt:message bundle="${rs}" key="language.Register"/></a></p>
    </div>
    <div class="container">
    <c:if test="${error == 1}">
        <p id="errorText" class="fs-5 fw-normal text-red" style="color:red;"><fmt:message bundle="${rs}" key="language.InvalidUsernameError"/></p>
    </c:if>
    <c:if test="${error == 2}">
        <p id="errorText" class="fs-5 fw-normal text-red" style="color:red;"><fmt:message bundle="${rs}" key="language.ReserveFlightError"/></p>
    </c:if>
    <c:if test="${error == 3}">
        <p id="errorText" class="fs-5 fw-normal text-red" style="color:red;"><fmt:message bundle="${rs}" key="language.EmptyFieldsError"/></p>
    </c:if>
    </div>
    <input type="hidden" name="command" value="login">
</form>
<br/>
<a class="fs-5 btn btn-outline-light" href="<c:url value='/index.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>
<jsp:include page="footer.jsp"/>
</body>
</html>