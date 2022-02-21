<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<form name="AddForm" action="/aircost/start" method="get">
    <br class="container">
    <h1>Add a flight</h1>
    <p>Please fill in this form to add a flight.</p>
    <hr>
    <label>
        <input type="text" class="inbox" placeholder="Enter from" name="from" required>
    </label>
    <br>
    <label>
        <input type="text" class="inbox" placeholder="Enter to" name="to" required>
    </label>
    <br>
    <label>
        <input type="date" placeholder="Enter date" name="date" required>
    </label>
    <br>
    <label>
        <input type="number" class="inbox" placeholder="Enter amount" name="amount" required>
    </label>
    <br>
    <label>
        <input type="number" class="inbox" placeholder="Enter price" name="price" required>
    </label>
    <br>
    <hr>
    <button type="submit" style="color: green">Add</button>
    <p id="errorText" style="color:red;">${error}</p>
    <input type="hidden" name="command" value="add">
</form>
<br/>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
