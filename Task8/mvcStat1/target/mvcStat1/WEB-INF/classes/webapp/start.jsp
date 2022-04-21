<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Stat page</title>
    <script>
        function sendForm(statOperation) {
            const MIN_VALUE = -1000;
            const MAX_VALUE = 1000;
            const err = document.getElementById("errorText");
            for (let i = 0; i < document.result.stats.length; i++) {
                const strValue = document.result.stats[i].value;
                const numValue = new Number(strValue);
                if (strValue === "" || numValue < MIN_VALUE || numValue > MAX_VALUE) {
                    err.innerHTML = "box " + i + " is NaN or outside [" + MIN_VALUE + "; " + MAX_VALUE + "]";
                    return;
                }
            }
            document.result.operation.value = statOperation;
            document.result.submit();
        }
    </script>
</head>
<body>
<p id="errorText" style="color:#ff0000;"></p>
<p/>
<form name="result" action="/result" onsubmit="return false">

    <c:forEach var="i" begin="0" end="${number - 1}">
        ${i}: <input name="stats" type="number" value="0" min="-1000" max="1000"/>
        <br/><br/>
    </c:forEach>
    <input type=hidden name="operation" value="no">
    <br/>
    <a href="JavaScript:sendForm('sum')">sum</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('max')">max</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('min')">min</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('avg')">avg</a>

</form>
<br/>
<a href="<c:url value='/index.jsp'/>">Back</a>
</body>
</html>
