<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.LogInPage"/></title>
</head>
<body>
<form name="LoginForm" action="/aircost/start" method="post">
    <div class="container">
        <h1><fmt:message bundle="${rs}" key="language.LogInForm"/></h1>
        <p><fmt:message bundle="${rs}" key="language.FormInfo"/></p>
        <hr>
        <label>
            <input type="text" placeholder="<fmt:message bundle="${rs}" key="language.Email"/>"
                   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="email"
                   required>
        </label>
        <label>
            <input type="password" minlength="8" placeholder="<fmt:message bundle="${rs}" key="language.Password"/>" name="password" required>
        </label>
        <hr>
        <button type="submit"><fmt:message bundle="${rs}" key="language.SignIn"/></button>
    </div>
    <div class="container signing">
        <p><fmt:message bundle="${rs}" key="language.NoAccount"/><a href="/aircost/start?command=register_page"><fmt:message bundle="${rs}" key="language.Register"/></a></p>
    </div>
    <p id="errorText" style="color:red;">${error}</p>
    <input type="hidden" name="command" value="login">
</form>
<br/>
<a href="<c:url value='/index.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>
</body>
<%--<head>--%>
<%--    <title>Login</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form name="LoginForm" action="/aircost/start" method="post">--%>
<%--    <div class="container">--%>
<%--        <h1>Log in form</h1>--%>
<%--        <p>Fill all fields</p>--%>
<%--        <hr>--%>
<%--        <label>--%>
<%--            <input type="text" placeholder="Email"--%>
<%--                   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="email"--%>
<%--                   required>--%>
<%--        </label>--%>
<%--        <label>--%>
<%--            <input type="password" minlength="8" placeholder="Password" name="password" required>--%>
<%--        </label>--%>
<%--        <hr>--%>
<%--        <button type="submit">Sign In</button>--%>
<%--    </div>--%>
<%--    <div class="container signing">--%>
<%--        <p>Don't have an account yet? <a href="/aircost/start?command=register_page">Register</a></p>--%>
<%--    </div>--%>
<%--    <p id="errorText" style="color:red;">${error}</p>--%>
<%--    <input type="hidden" name="command" value="login">--%>
<%--</form>--%>
<%--<br/>--%>
<%--<a href="<c:url value='/index.jsp'/>">Back</a>--%>
<%--</body>--%>
</html>