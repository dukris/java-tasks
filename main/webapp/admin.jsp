<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<c:forEach items="${flights}" var="flight" varStatus="status">
    <p>From: ${flight.fromCity}</p>
    <p>To: ${flight.toCity}</p>
    <p>Date: ${flight.date}</p>
    <p>Price: ${flight.price}</p>
    <p>Amount: ${flight.amount}</p>
    <hr>
</c:forEach>
<p><a href="/aircost/start?command=add_page">Add</a></p>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
