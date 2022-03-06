<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>
<form name="SearchForm" action="/aircost/start" method="get">
    <div class="container">
        <h1>Search Form</h1>
        <p>Please fill in this form.</p>
        <hr>
        <label>
            <input type="text" placeholder="From" name="from" required>
        </label>
        <hr>
        <label>
            <input type="text" placeholder="To" name="to" required>
        </label>
        <hr>
        <label>
            <input type="date" placeholder="Date" name="date">
        </label>
        <hr>
        <button type="submit" class="searchbtn">Search</button>
    </div>
    <p id="errorText" style="color:red;">${error}</p>
    <input type="hidden" name="command" value="HOME">
</form>
<br/>
<c:if test="${sessionScope.flights != null}">
<form action="/aircost/start" method="post">
    <c:forEach items="${flights}" var="flight" varStatus="status">
        <p>From: ${flight.fromCity}</p>
        <p>To: ${flight.toCity}</p>
        <p>Date: ${flight.date}</p>
        <p>Price: ${flight.price}</p>
        <button type="submit" name="id" value="${flight.id}">Buy</button>
        <hr>
    </c:forEach>
    <input type="hidden" name="command" value="reserve_flight">
</form>
</c:if>
<h3>Что-то красивое выводить</h3>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
