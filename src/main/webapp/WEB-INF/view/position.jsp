<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-16">
    <title>Пирвет ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
                    <h3><a href="/api/v1/groups">Отделы</a></h3>
                     <h3><a href="/api/v1/departments">Департаменты</a></h3>
                     <h3><a href="/api/v1/employees">Работники</a></h3>
<body>
        <table border="1" cellpadding="8">
                <tr>
                    <th>Код должности</th>
                    <th>Название</th>
                </tr>
    <script src="/js/main.js"></script>
        <c:forEach items="${listPosition}" var="position">
        <tr>
            <td>${position.code}</td>
            <td>${position.title}</td>
        </tr>
        </c:forEach>
            </table>
</body>
</html>

