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
                </tr>
    <script src="/js/main.js"></script>
        <c:forEach items="${listOtdel}" var="otdel">
        <tr>
            <td>${otdel.id}</td>
            <td><a href="/groups/${otdel.id}">${otdel.title}</a></td>
            <td>${otdel.address}</td>
        </tr>
                </c:forEach>
            </table>
               </div>
</body>
</html>