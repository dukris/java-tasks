<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Result page</title>
</head>
<body>
${operation} (
<c:forEach items="${stats}" var="stat" varStatus="status">
    ${stat}
    <c:if test="${not status.last}">
        ,
    </c:if>
</c:forEach>
) is ${result}

<br/>
<a href="<c:url value='/'/>">Main</a>
</body>
</html>
