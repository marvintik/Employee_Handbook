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
	<form action="#" method="POST">
	<div class="form-group">
                    <label>Фамилия</label>
   <input type="text" name="lastName">
                </div>
    			<div class="form-group">
                                <label>Имя:</label>
               <input type="text" name="firstName">
                            </div>
    			<div class="form-group">
                                <label>Отчество:</label>
               <input type="text" name="secondName">
                            </div>
    				<div class="form-group">
                                                <label>Телефон:</label>
                               <input type="text" name="phone">
                                            </div>
                                            	<div class="form-group">
                                                                            <label>Мейл:</label>
                                                           <input type="text" name="mail">
                                                                        </div>
		<input type="submit" value="Поиск">
	</form>
	       <h3>Результаты поиска</h3>
	          <div class="table-responsive">
                 <table class="table">
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
            <td><img src="/employees/image/${employee.login}" width="100" height="100"/></td>
            <td><c:forEach items="${employee.phone}" var="phone">
                                         <p>${phone.phone}</p>
                                         </c:forEach></td>
            <td><c:forEach items="${employee.mail}" var="mail">
            <p>${mail.mail}</p>
            </c:forEach></td>

        </tr>
        </c:forEach>
            </table>
               </div>
</body>
</html>