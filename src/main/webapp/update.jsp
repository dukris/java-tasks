<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.language" var="rs"/>
    <title><fmt:message bundle="${rs}" key="language.UpdateProfilePage"/></title>
</head>
<body>
<form name="RegisterForm" action="/aircost/start" method="post">
    <br class="container">
    <p><fmt:message bundle="${rs}" key="language.FormInfo"/></p>
    <hr>
    <label>
        <input type="password" class="inbox" minlength="8" placeholder="<fmt:message bundle="${rs}" key="language.Password"/>" name="password_old" required>
    </label>
    <br>
    <label>
        <input type="password" class="inbox" minlength="8" placeholder="<fmt:message bundle="${rs}" key="language.NewPassword"/>" name="password" required>
    </label>
    <br>
    <label>
        <input type="password" minlength="8" placeholder="<fmt:message bundle="${rs}" key="language.ConfirmPassword"/>" name="password_confirm" required>
    </label>
    <hr>
    <button type="submit" class="updatebtn"><fmt:message bundle="${rs}" key="language.Update"/></button>
    </div>
    <c:if test="${error == 1}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.UpdateProfilePasswordError"/></p>
    </c:if>
    <c:if test="${error == 2}">
        <p id="errorText" style="color:red;"><fmt:message bundle="${rs}" key="language.EmailAlreadyTakenError"/></p>
    </c:if>
    <input type="hidden" name="command" value="update_profile">
</form>
<br/>
<a href="<c:url value='/profile.jsp'/>"><fmt:message bundle="${rs}" key="language.Back"/></a>
</body>
</html>
