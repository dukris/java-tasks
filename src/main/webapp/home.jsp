<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.HomePage"/></title>
    <link rel="stylesheet" href="background.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
</head>
<body>
<form name="SearchForm" action="/aircost/start" method="get">
    <div class="container">
        <p><fmt:message bundle="${rs}" key="language.FormInfo"/></p>
        <hr>
        <label><fmt:message bundle="${rs}" key="language.From"/>:</label>
        <select name="from">
            <c:forEach items="${cities}" var="city" varStatus="status">
                <option value="${city}">${city}</option>
            </c:forEach>
        </select>
        <br>
        <label><fmt:message bundle="${rs}" key="language.To"/>:</label>
        <select name="to">
            <c:forEach items="${cities}" var="city" varStatus="status">
                <option>${city}</option>
            </c:forEach>
        </select>
        <hr>
        <label>
            <input type="date" placeholder="<fmt:message bundle="${rs}" key="language.Date"/>" name="date">
        </label>
        <hr>
        <button type="submit" class="searchbtn"><fmt:message bundle="${rs}" key="language.Search"/></button>
    </div>
    <c:if test="${error == 1}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.NothingFoundError"/></p>
    </c:if>
    <c:if test="${error == 2}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.IncorrectCityOrDateError"/></p>
    </c:if>
    <input type="hidden" name="command" value="HOME">
</form>
<br/>
<c:if test="${sessionScope.flights != null}">
<form action="/aircost/start" method="post">
    <c:forEach items="${flights}" var="flight" varStatus="status">
        <p><fmt:message bundle="${rs}" key="language.From"/>: ${flight.fromCity}</p>
        <p><fmt:message bundle="${rs}" key="language.To"/>: ${flight.toCity}</p>
        <p><fmt:message bundle="${rs}" key="language.Date"/>: ${flight.date}</p>
        <p><fmt:message bundle="${rs}" key="language.Price"/>: ${flight.price}</p>
        <button type="submit" name="id" value="${flight.id}"><fmt:message bundle="${rs}" key="language.Buy"/></button>
        <hr>
    </c:forEach>
    <input type="hidden" name="command" value="reserve_flight">
</form>
</c:if>
<h3>Что-то красивое выводить</h3>
<a href="<c:url value='/index.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>
</body>
</html>
