<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Start page</title>
</head>
<body>
Welcome!
<p><a href="/aircost/start?command=home_page">START</a></p>
<a href="/aircost/start?command=profile_page">My profile</a>
<p><a href="/aircost/start?command=login_page">Log in</a></p>
Not registered yet?
<p><a href="/aircost/start?command=register_page">Register now!</a></p>
</body>
</html>