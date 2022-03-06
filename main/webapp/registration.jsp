<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Register page</title>
    <style>
        .inbox {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<form name="RegisterForm" action="/aircost/start" method="post">
    <br class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
    <label>
        <input type="text" class="inbox" placeholder="Enter Name" name="name" required>
    </label>
    <br>
    <label>
        <input type="text" class="inbox" placeholder="Enter Surname" name="surname" required>
    </label>
    <br>
    <label>
        <input type="text" class="inbox" placeholder="Enter Passport" name="passport" required>
    </label>
    <br>
    <label>
        <input type="text" class="inbox" placeholder="Enter Email" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="email" required>
    </label>
    <br>
    <label>
        <input type="password" minlength="8" placeholder="Enter Password" name="password" required>
    </label>
    <hr>
    <button type="submit">Register</button>
    </div>
    <div class="container signin">
        <p>Already have an account? <a href="/aircost/start?command=login_page">Sign in</a>.</p>
    </div>
    <p id="errorText" style="color:red;">${error}</p>
    <input type="hidden" name="command" value="register">
</form>
<br/>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
