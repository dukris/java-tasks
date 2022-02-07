<%--
  Created by IntelliJ IDEA.
  User: Ann Izmailovich
  Date: 04.02.2022
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
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
<form name="RegisterForm" action="/registration" method="post">
    <br class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
    <label>
        <input type="text" class="inbox" placeholder="Enter Name" name="name" required>
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
    <button type="submit" class="registerbtn">Register</button>
    </div>
    <div class="container signin">
        <p>Already have an account? <a href="/login?command=LOGIN_PAGE">Sign in</a>.</p>
    </div>
    <%
        String msg = (String) request.getAttribute("error");
        if (msg != null) {
    %>
    <%="<font color=red size=4px> " + msg + "</font>"%>
    <%}%>
    <input type="hidden" name="command" value="REGISTER">
</form>
<br/>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
