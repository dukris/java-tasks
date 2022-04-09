<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.EditFlightPage"/></title>
</head>
<body>
<form action="/aircost/start" method="post">
    <br class="container">
    <p><fmt:message bundle="${rs}" key="language.FormInfo"/></p>
    <hr>
    <label>
        <input type="text" class="inbox" value="${sessionScope.editFlight.fromCity}" name="from" required>
    </label>
    <br>
    <label>
        <input type="text" class="inbox" value="${sessionScope.editFlight.toCity}" name="to" required>
    </label>
    <br>
    <label>
        <input type="date" value="${sessionScope.editFlight.date}" name="date" required>
    </label>
    <br>
    <label>
        <input type="number" class="inbox" value="${sessionScope.editFlight.amount}" name="amount" required>
    </label>
    <br>
    <label>
        <input type="number" class="inbox" value="${sessionScope.editFlight.price}" name="price" required>
    </label>
    <br>
    <hr>
    <button type="submit" style="color: green"><fmt:message bundle="${rs}" key="language.Edit"/></button>
    <input type="hidden" name="command" value="edit_flight">
</form>
<c:if test="${error == 1}">
    <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.EditFlightUnableError"/></p>
</c:if>
<a href="<c:url value='/index.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>
</body>
</html>
