<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Stat page</title>
    <script>
        function checkData() {
            const MIN_NUM = 2;
            const MAX_NUM = 10;
            const err = document.getElementById("errorText");
            const statsNumber = new Number(document.statsNumber.number.value);
            if(!isInteger(statsNumber) || statsNumber < MIN_NUM || statsNumber > MAX_NUM) {
                err.innerHTML = "input integer number between " + MIN_NUM + " and " + MAX_NUM;
                return false;
            }
            return true;

            function isInteger(number) {
                return Math.floor(number) == number;
            }
        }

        function sendForm() {
            if(checkData()) {
                document.statsNumber.submit();
            }
        }
    </script>
</head>
<body>
<p id="errorText" style="color:red;"></p>
<p/>
<form name="statsNumber" action="/start" onsubmit="return checkData()">
    <label for="quantity">stats number (between 2 and 10):</label>
    <input type="number" id="quantity" name="number" min="2" max="10" step="1" value="3">
    <br/>
    <a href="JavaScript:sendForm()">generate stats</a>
</form>
</body>
</html>