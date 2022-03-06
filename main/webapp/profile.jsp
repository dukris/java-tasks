<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>--%>
<%--    <jsp:include page="header.jsp"/>--%>
<%--    <fmt:setLocale value="${sessionScope.language}"/>--%>
<%--    <fmt:setBundle basename="language.language" var="rs"/>--%>
<%--    <title><fmt:message bundle="${rs}" key="language.ProfilePage"/></title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<c:if test="${sessionScope.user == null}">--%>
<%--    <h3><fmt:message bundle="${rs}" key="language.NotLogged"/></h3>--%>
<%--    <p><a href="/aircost/start?command=login_page"><fmt:message bundle="${rs}" key="language.LogIn"/></a></p>--%>
<%--</c:if>--%>
<%--<c:if test="${sessionScope.user != null}">--%>
<%--    <h3><fmt:message bundle="${rs}" key="language.WelcomeProfile"/>${sessionScope.user.name}!</h3>--%>
<%--    <hr>--%>
<%--    <p><fmt:message bundle="${rs}" key="language.Name"/>: ${sessionScope.user.name}</p>--%>
<%--    <p><fmt:message bundle="${rs}" key="language.Email"/>: ${sessionScope.user.email}</p>--%>
<%--    <p><a href="/aircost/start?command=update_profile_page"><fmt:message bundle="${rs}" key="language.EditPassword"/></a></p>--%>
<%--    <p><a href="/aircost/start?command=logout"><fmt:message bundle="${rs}" key="language.LogOut"/></a></p>--%>
<%--</c:if>--%>
<%--<a href="<c:url value='/index.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>--%>
<%--</body>--%>
<head>
    <title>Profile</title>
</head>
<body>
<c:if test="${sessionScope.user == null}">
    <h3>You are not logged!</h3>
    <p><a href="/aircost/start?command=login_page">Log In</a></p>
</c:if>
<c:if test="${sessionScope.user != null}">
    <c:if test="${sessionScope.users != null}">
        <h3>Active users:</h3>
        <hr>
        <c:forEach items="${users}" var="user" varStatus="status">
            <p>Name: ${user.name}</p>
            <p>Surname: ${user.surname}</p>
            <p>Email: ${user.email}</p>
            <hr>
        </c:forEach>
        <c:if test="${sessionScope.flights != null}">
            <form action="/aircost/start" method="post">
                <h3>Active flights:</h3>
                <hr>
                <c:forEach items="${flights}" var="flight" varStatus="status">
                    <%--            <label for="choice"></label>--%>
                    <%--            <input type="radio" id="choice" name="radioChoice" value="${flight.id}">--%>
                    <p>From: ${flight.fromCity}</p>
                    <p>To: ${flight.toCity}</p>
                    <p>Date: ${flight.date}</p>
                    <p>Price: ${flight.price}</p>
                    <p>Amount: ${flight.amount}</p>
                    <button type="submit" name="deleteChoice" value="${flight.id}">Delete</button>
                    <button type="submit" name="editChoice" value="${flight.id}">Edit</button>
                    <hr>
                </c:forEach>
                <p id="errorText" style="color:red;">${error}</p>
                <input type="hidden" name="command" value="update_flight">
            </form>
            <p><a href="/aircost/start?command=add_page">Add flight</a></p>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.users == null}">
        <h3>Welcome to you profile, ${sessionScope.user.name}!</h3>
        <hr>
        <p>Name: ${sessionScope.user.name}</p>
        <p>Surname: ${sessionScope.user.surname}</p>
        <p>Passport: ${sessionScope.user.passport}</p>
        <p>Email: ${sessionScope.user.email}</p>
        <p><a href="/aircost/start?command=update_profile_page">Edit password</a></p>
        <c:if test="${sessionScope.orders == null}">
            <p>You have not active orders yet!</p>
        </c:if>
        <c:if test="${sessionScope.orders != null}">
            <p>Active orders:</p>
            <hr>
            <c:forEach items="${orders}" var="order" varStatus="status">
                <p>From: ${order.fromCity}</p>
                <p>To: ${order.toCity}</p>
                <p>Date: ${order.date}</p>
                <p>Amount of tickets: ${order.amount}</p>
                <p>Total cost: ${order.totalCost}</p>
                <hr>
            </c:forEach>
        </c:if>
    </c:if>
    <p><a href="/aircost/start?command=logout">Log Out</a></p>
</c:if>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
