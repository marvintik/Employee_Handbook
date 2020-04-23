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
                     <h3><a href="/api/v1/positions">Должности</a></h3>
<body>
        <table border="1" cellpadding="8">
                <tr>
                    <th>Login</th>
                    <th>Фамилияe</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Дата рождения</th>
                    <th>Должность</th>
                    <th>Фото</th>
                    <th>Телнфон</th>
                    <th>E-mail</th>
                </tr>
    <script src="/js/main.js"></script>
        <c:forEach items="${listEmployee}" var="employee">
        <tr>
            <td>${employee.login}</td>
            <td>${employee.lastName}</td>
            <td>${employee.firstName}</td>
            <td>${employee.secondName}</td>
            <td>${employee.date}</td>
            <td><p>${employee.position.code},${employee.position.title}</p></td>
            <td><img src="http://localhost:8080/${employee.photo}" width="100" height="100"/></td>
            <td><c:forEach items="${employee.phone}" var="phone">
                                         <p>${phone.phone}</p>
                                         </c:forEach></td>
            <td><c:forEach items="${employee.mail}" var="mail">
            <p>${mail.mail}</p>
            </c:forEach></td>

        </tr>
        </c:forEach>
            </table>
</body>
</html>