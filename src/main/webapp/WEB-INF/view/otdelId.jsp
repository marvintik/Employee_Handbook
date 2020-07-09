<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-16">
    <title>Пирвет ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
    <jsp:include page="navbar.jsp"/>

</head>
<body>
        <div class="table-responsive">
           <table class="table">
                <tr>
                    <th>ID</th>
                    <th>title</th>
                    <th>Address</th>
                    <th>Employees</th>
                </tr>
        <tr>
            <td>${otdel.id}</td>
            <td>${otdel.title}</td>
            <td>${otdel.address}</td>
            <td><c:forEach items="${otdel.employees}" var="employee">
            <p>${employee.lastName} ${employee.firstName} ${employee.secondName} ${employee.login}</p>
            </c:forEach></td>
        </tr>
        </table>
           </div>
</body>
</html>