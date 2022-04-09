<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
</head>
<footer class=" pt-md-5 border-top">
    <div class="row">
        <div class="col-12 col-md">
            <a href="/avia/homepage" class="d-flex align-items-center text-dark text-decoration-none">
                <span class="fs-3" style="text-indent: 30px">AirCOST</span></a>
            <small class="d-block mb-3 text-light" style="text-indent: 30px">© 2022</small>
        </div>
        <div class="col-6 col-md">
            <h5>Что-то придумать</h5>
            <ul class="list-unstyled text-small">
                <li class="mb-1"><a class="link-secondary text-decoration-none" href="/avia/brandspage/bmw">Что-то придуматьW</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>Feedback</h5>
            <ul class="list-unstyled text-small">
                <li class="mb-1"><a class="link-secondary text-decoration-none" href="/avia/callpage">Contacts</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>About</h5>
            <ul class="list-unstyled text-small">
                <li class="mb-1"><a class="link-secondary text-decoration-none" href="https://t.me/joinchat/L1E6TEeGwlUwZDUy">Technical support</a></li>
            </ul>
        </div>
    </div>
</footer>
</html>
