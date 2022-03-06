<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<h3>Confirm order, please</h3>
<hr>
Personal data:
<hr>
<p>Name: ${sessionScope.user.name}</p>
<p>Surname: ${sessionScope.user.surname}</p>
<p>Passport: ${sessionScope.user.passport}</p>
<hr>
Order data:
<hr>
<p>From: ${order.fromCity}</p>
<p>To: ${order.toCity}</p>
<p>Date: ${order.date}</p>
<p>Price: ${order.price}</p>
<hr>
<form action="/aircost/start" method="post">
    <input type="radio" id="choice"
           name="baggage">
    <label for="choice">Baggage (+15$)</label>
    <br>
    <label>
        <input type="number" placeholder="Enter the amount of tickets" name="amount" required>
    </label>
    <br>
    <button type="submit">Confirm</button>
    <input type="hidden" name="command" value="submit_order">
    <p id="errorText" style="color:red;">${error}</p>
</form>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
