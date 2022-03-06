<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%--<head>--%>
<%--    <jsp:include page="header.jsp"/>--%>
<%--    <fmt:setLocale value="${sessionScope.language}"/>--%>
<%--    <fmt:setBundle basename="language.language" var="rs"/>--%>
<%--    <title><fmt:message bundle="${rs}" key="language.StartPage"/></title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h3 class="pb-3 my-sm-3"><fmt:message bundle="${rs}" key="language.Welcome"/></h3>--%>
<%--<p><a href="/aircost/start?command=home_page"><fmt:message bundle="${rs}" key="language.Start"/></a></p>--%>
<%--<a href="/aircost/start?command=profile_page"><fmt:message bundle="${rs}" key="language.Profile"/></a>--%>
<%--<p><a href="/aircost/start?command=login_page"><fmt:message bundle="${rs}" key="language.LogIn"/></a></p>--%>
<%--<fmt:message bundle="${rs}" key="language.NoRegister"/>--%>
<%--<p><a href="/aircost/start?command=register_page"><fmt:message bundle="${rs}" key="language.Register"/></a></p>--%>
<%--<input type="button" value="RU" formmethod="post" onClick="document.location = '?language=ru_RU'"/>--%>
<%--<input type="button" value="EN" formmethod="post" onClick="document.location = '?language=en_US'"/>--%>
<%--</body>--%>
<head>
    <title>Start</title>
</head>
<body>
<h3 class="pb-3 my-sm-3">Welcome!</h3>
<p><a href="/aircost/start?command=home_page">Start</a></p>
<a href="/aircost/start?command=profile_page">Profile</a>
<p><a href="/aircost/start?command=login_page">LogIn</a></p>
Not registered yet?
<p><a href="/aircost/start?command=register_page">Register</a></p>
</body>
</html>
