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
                     <a href="/groups/${otdel.id}">${otdel.title},</a>
                  </c:forEach>
               </td>
            </tr>
         </c:forEach>
      </table>
      </div>
   </body>
</html>