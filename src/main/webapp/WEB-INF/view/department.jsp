<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
                    <h3><a href="/api/v1/groups">Отделы</a></h3>
                    <h3><a href="/api/v1/employees">Работники</a></h3>
                    <h3><a href="/api/v1/positions">Должности</a></h3>
<head>
    <meta charset="UTF-16">
    <title>Пирвет ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
        <table border="1" cellpadding="3">
                <tr>
                    <th>ID</th>
                    <th>title</th>
                    <th>Address</th>
                    <th>Отделы</th>
                </tr>
    <script src="/js/main.js"></script>
        <c:forEach items="${listDepartment}" var="department">
        <tr>
            <td>${department.id}</td>
            <td>${department.title}</td>
            <td>${department.address}</td>
            <td>
                    <c:forEach items="${department.otdel}" var="otdel">
                    <a href="/api/v1/groups/${otdel.id}">${otdel.title},</a>
                                    </c:forEach>

            </td>
        </tr>
                </c:forEach>
            </table>
</body>
</html>