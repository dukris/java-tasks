<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
</head>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center border-bottom">
        <a href="/aircost/" class="d-flex align-items-center text-light text-decoration-none">
            <span class="fs-3" style="text-indent: 30px">AirCOST</span>
        </a>
        <nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
            <form action="/aircost/start" method="get">
                <input type="hidden" name="command" value="language">
                <button class="btn btn-outline-light" type="submit" name="language" value="en_US">EN</button>
                <button class="btn btn-outline-light" type="submit" name="language" value="ru_RU">RU</button>
                <a class="btn btn-success" href="/aircost/start?command=profile_page"><fmt:message bundle="${rs}" key="language.Profile"/></a>
            </form>
<%--            <a class="btn btn-success" href="/aircost/start?command=profile_page"><fmt:message bundle="${rs}" key="language.Profile"/></a>--%>
<%--            <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/homepage">Home</a>--%>
<%--            <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/contactspage">Contacts</a>--%>
<%--            <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/salepage">Sale</a>--%>
<%--            <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/brandspage">Car Brands</a>--%>
<%--            <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/drivepage">Test Drive</a>--%>
<%--            <a class="me-3 py-2 text-dark text-decoration-none" href="/avia/callpage">Call</a>--%>
<%--            <a class="me-3 py-2 text-decoration-none btn btn-outline-dark"><fmt:message bundle="${rs}" key="language.Profile"/></a>--%>
        </nav>
    </div>
</header>

</html>
