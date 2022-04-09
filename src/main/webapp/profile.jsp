<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.ProfilePage"/></title>
    <link rel="stylesheet" href="background.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
</head>
<body>
<c:if test="${sessionScope.user == null}">
    <h3><fmt:message bundle="${rs}" key="language.NotLogged"/></h3>
    <p><a href="/aircost/start?command=login_page"><fmt:message bundle="${rs}" key="language.LogIn"/></a></p>
</c:if>
<c:if test="${sessionScope.user != null}">
    <c:if test="${sessionScope.users != null}">
        <h3><fmt:message bundle="${rs}" key="language.ActiveUsers"/>:</h3>
        <hr>
        <c:forEach items="${users}" var="user" varStatus="status">
            <p><fmt:message bundle="${rs}" key="language.Name"/>: ${user.name}</p>
            <p><fmt:message bundle="${rs}" key="language.Surname"/>: ${user.surname}</p>
            <p><fmt:message bundle="${rs}" key="language.Email"/>: ${user.email}</p>
            <hr>
        </c:forEach>
        <c:if test="${sessionScope.flights != null}">
            <form action="/aircost/start" method="post">
                <h3><fmt:message bundle="${rs}" key="language.ActiveFlights"/>:</h3>
                <hr>
                <c:forEach items="${flights}" var="flight" varStatus="status">
                    <p><fmt:message bundle="${rs}" key="language.From"/>: ${flight.fromCity}</p>
                    <p><fmt:message bundle="${rs}" key="language.To"/>: ${flight.toCity}</p>
                    <p><fmt:message bundle="${rs}" key="language.Date"/>: ${flight.date}</p>
                    <p><fmt:message bundle="${rs}" key="language.Price"/>: ${flight.price}</p>
                    <p><fmt:message bundle="${rs}" key="language.Amount"/>: ${flight.amount}</p>
                    <button type="submit" name="deleteChoice" value="${flight.id}"><fmt:message bundle="${rs}" key="language.Delete"/></button>
                    <button type="submit" name="editChoice" value="${flight.id}"><fmt:message bundle="${rs}" key="language.Edit"/></button>
                    <hr>
                </c:forEach>
                <c:if test="${error == 1}">
                    <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.DeleteFlightUnableError"/></p>
                </c:if>
                <input type="hidden" name="command" value="update_flight">
            </form>
            <p><a href="/aircost/start?command=add_page"><fmt:message bundle="${rs}" key="language.AddFlight"/></a></p>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.users == null}">
        <h3><fmt:message bundle="${rs}" key="language.WelcomeProfile"/>${sessionScope.user.name}!</h3>
            <hr>
            <p><fmt:message bundle="${rs}" key="language.Name"/>: ${sessionScope.user.name}</p>
            <p><fmt:message bundle="${rs}" key="language.Surname"/>: ${sessionScope.user.surname}</p>
            <p><fmt:message bundle="${rs}" key="language.Email"/>: ${sessionScope.user.email}</p>
        <p><fmt:message bundle="${rs}" key="language.Passport"/>: ${sessionScope.user.passport}</p>
            <p><a href="/aircost/start?command=update_profile_page"><fmt:message bundle="${rs}" key="language.EditPassword"/></a></p>
        <c:if test="${sessionScope.orders == null}">
            <p><fmt:message bundle="${rs}" key="language.NoActiveOrders"/></p>
        </c:if>
        <c:if test="${sessionScope.orders != null}">
            <p><fmt:message bundle="${rs}" key="language.ActiveOrders"/>:</p>
            <hr>
            <c:forEach items="${orders}" var="order" varStatus="status">
                <p><fmt:message bundle="${rs}" key="language.From"/>: ${order.fromCity}</p>
                <p><fmt:message bundle="${rs}" key="language.To"/>: ${order.toCity}</p>
                <p><fmt:message bundle="${rs}" key="language.Date"/>: ${order.date}</p>
                <p><fmt:message bundle="${rs}" key="language.AmountOfTickets"/>: ${order.amount}</p>
                <p><fmt:message bundle="${rs}" key="language.TotalCost"/>: ${order.totalCost}</p>
                <hr>
            </c:forEach>
        </c:if>
    </c:if>
        <p><a href="/aircost/start?command=logout"><fmt:message bundle="${rs}" key="language.LogOut"/></a></p>
</c:if>
<a href="<c:url value='/index.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>
</body>
</html>
