<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile page</title>
</head>
<body>
<c:if test="${sessionScope.user == null}">
    <h3>You are not logged in!</h3>
    <p><a href="/aircost/start?command=login_page">Log in</a></p>
</c:if>
<c:if test="${sessionScope.user != null}">
    <h3>Welcome to your profile, ${sessionScope.user.name}!</h3>
    <hr>
    <p>Name: ${sessionScope.user.name}</p>
    <p>Email: ${sessionScope.user.email}</p>
    <p><a href="/aircost/start?command=update_profile_page">Edit password</a></p>
    <p><a href="/aircost/start?command=logout">Log out</a></p>
</c:if>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
