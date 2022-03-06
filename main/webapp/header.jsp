<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
</head>
<header>
    <div>
        <h3>AirCOST</h3>
        <a href="/aircost/start?command=profile_page"><fmt:message bundle="${rs}" key="language.Profile"/></a>
        <form action="/aircost/start" method="post">
            <button type="submit" name="language" value="EN">EN</button>
            <button type="submit" value="RU">RU</button>
            <input type="hidden" name="command" value="login_page">
        </form>
    </div>
    <hr>
</header>
</html>
