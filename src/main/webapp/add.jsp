<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.AddFlightPage"/></title>
</head>
<body>
<form name="AddForm" action="/aircost/start" method="post">
    <br class="container">
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
    <br>
    <label>
        <input type="date" placeholder="<fmt:message bundle="${rs}" key="language.Date"/>" name="date" required>
    </label>
    <br>
    <label>
        <input type="number" class="inbox" placeholder="<fmt:message bundle="${rs}" key="language.Amount"/>" name="amount" required>
    </label>
    <br>
    <label>
        <input type="number" class="inbox" placeholder="<fmt:message bundle="${rs}" key="language.Price"/>" name="price" required>
    </label>
    <br>
    <hr>
    <button type="submit" style="color: green"><fmt:message bundle="${rs}" key="language.Add"/></button>
    <c:if test="${error == 1}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.AddFlightCityError"/></p>
    </c:if>
    <c:if test="${error == 2}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.AddFlightAmountError"/></p>
    </c:if>
    <c:if test="${error == 3}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.AddFlightUnableError"/></p>
    </c:if>
    <input type="hidden" name="command" value="add">
</form>
<br/>
<a href="<c:url value='/index.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>
</body>
</html>
