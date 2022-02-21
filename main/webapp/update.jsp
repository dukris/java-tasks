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
    <h1>Update</h1>
    <p>Please fill in this form to change a password.</p>
    <hr>
    <label>
        <input type="password" class="inbox" minlength="8" placeholder="Enter your current password" name="password_old" required>
    </label>
    <br>
    <label>
        <input type="password" class="inbox" minlength="8" placeholder="Enter new password" name="password" required>
    </label>
    <br>
    <label>
        <input type="password" minlength="8" placeholder="Confirm new password" name="password_confirm" required>
    </label>
    <hr>
    <button type="submit" class="updatebtn">Update</button>
    </div>
    <p id="errorText" style="color:red;">${error}</p>
    <input type="hidden" name="command" value="update_profile">
</form>
<br/>
<a href="<c:url value='/profile.jsp'/>">Back</a>
</body>
</html>
