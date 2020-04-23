<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-16">
    <title>Пирвет ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
                    <h3><a href="/api/v1/employees">Работники</a></h3>
                     <h3><a href="/api/v1/departments">Департаменты</a></h3>
                     <h3><a href="/api/v1/positions">Должности</a></h3>
<body>
        <table border="1" cellpadding="3">
                <tr>
                    <th>ID</th>
                    <th>title</th>
                    <th>Address</th>
                </tr>
    <script src="/js/main.js"></script>
        <c:forEach items="${listOtdel}" var="otdel">
        <tr>
            <td>${otdel.id}</td>
            <td><a href="/api/v1/groups/${otdel.id}">${otdel.title},</a></td>
            <td>${otdel.address}</td>
        </tr>
                </c:forEach>
            </table>
</body>
</html>