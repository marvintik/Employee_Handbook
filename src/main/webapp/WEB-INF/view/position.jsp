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
               </div>
</body>
</html>

