<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.OrderPage"/></title>
</head>
<body>
<h3><fmt:message bundle="${rs}" key="language.ConfirmOder"/></h3>
<hr>
<fmt:message bundle="${rs}" key="language.PersonalData"/>:
<hr>
<p><fmt:message bundle="${rs}" key="language.Name"/>: ${sessionScope.user.name}</p>
<p><fmt:message bundle="${rs}" key="language.Surname"/>: ${sessionScope.user.surname}</p>
<p><fmt:message bundle="${rs}" key="language.Passport"/>: ${sessionScope.user.passport}</p>
<hr>
<fmt:message bundle="${rs}" key="language.OtherData"/>:
<hr>
<p><fmt:message bundle="${rs}" key="language.From"/>: ${order.fromCity}</p>
<p><fmt:message bundle="${rs}" key="language.To"/>: ${order.toCity}</p>
<p><fmt:message bundle="${rs}" key="language.Date"/>: ${order.date}</p>
<p><fmt:message bundle="${rs}" key="language.Price"/>: ${order.price}</p>
<hr>
<form action="/aircost/start" method="post">
    <label>
        <input type="number" placeholder="<fmt:message bundle="${rs}" key="language.AmountOfTickets"/>" name="amount" required>
    </label>
    <br>
    <button type="submit"><fmt:message bundle="${rs}" key="language.Confirm"/></button>
    <input type="hidden" name="command" value="submit_order">
    <c:if test="${error == 1}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.InvalidAmountError"/></p>
    </c:if>
</form>
<a href="<c:url value='/index.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>
</body>
</html>
