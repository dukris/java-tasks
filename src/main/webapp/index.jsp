<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.StartPage"/></title>
    <link rel="stylesheet" href="background.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
</head>
<%--<header>--%>
<%--<div class="d-flex flex-column flex-md-row align-items-center border-bottom">--%>
<%--    <ul id="lang">--%>
<%--        <li><form action="/aircost/start" method="get">--%>
<%--            <input type="hidden" name="command" value="language">--%>
<%--            <button class="border-button" type="submit" name="language" value="en_US">EN</button>--%>
<%--            <button class="border-button" type="submit" name="language" value="ru_RU">RU</button>--%>
<%--        </form></li>--%>
<%--    </ul>--%>
<%--    <a href="/avia/homepage" class="d-flex align-items-center text-dark text-decoration-none">--%>

<%--        <span class="fs-3" style="text-indent: 30px">AVIA</span>--%>
<%--    </a>--%>
<%--    <nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">--%>
<%--        <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/homepage">Home</a>--%>
<%--        <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/contactspage">Contacts</a>--%>
<%--        <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/salepage">Sale</a>--%>
<%--        <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/brandspage">Car Brands</a>--%>
<%--        <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/drivepage">Test Drive</a>--%>
<%--        <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/callpage">Call</a>--%>
<%--        <a class="me-3 py-2 text-decoration-none btn btn-outline-dark" href="/avia/adminpage">Log In</a>--%>
<%--    </nav>--%>
<%--</div>--%>
<%--</header>--%>
<body>
    <div class="container py-20 d-flex flex-column min-vh-100 foreground">
        <div class="pricing-header p-3 pb-md-4 mx-auto text-center">
            <h1 class="display-4 fw-normal text-white"><fmt:message bundle="${rs}" key="language.Welcome"/></h1>
            <p class="fs-5 text-muted"><fmt:message bundle="${rs}" key="language.StartInfo"/></p>
            <a class="me-3 py-2 text-decoration-none btn btn-outline-light" href="/aircost/start?command=home_page"><fmt:message bundle="${rs}" key="language.Start"/></a>
<%--                <h3 class="wel"><fmt:message bundle="${rs}" key="language.Welcome"/></h3>--%>
<%--                <p><a class="double-border-button" href="/aircost/start?command=home_page"><fmt:message bundle="${rs}" key="language.Start"/></a></p></li>--%>
            <p class="fs-10 text-white"><fmt:message bundle="${rs}" key="language.Auth"/></p>
            <a class="me-1 py-2 text-decoration-none btn btn-outline-light" href="/aircost/start?command=login_page"><fmt:message bundle="${rs}" key="language.LogIn"/></a>
            <a class="me-1 py-2 text-decoration-none btn btn-outline-light" href="/aircost/start?command=register_page"><fmt:message bundle="${rs}" key="language.Register"/></a>

        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
