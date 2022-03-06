<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit flight</title>
</head>
<body>
<form action="/aircost/start" method="post">
    <br class="container">
    <h1>Edit a flight</h1>
    <p>Please fill in this form to edit a flight.</p>
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
    <button type="submit" style="color: green">Edit</button>
    <input type="hidden" name="command" value="edit_flight">
</form>
<p id="errorText" style="color:red;">${error}</p>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
