<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-16">
    <title>Главная форма</title>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
    <script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
 <jsp:include page="navbar.jsp"/>
</head>

<body>
                                         <h3>Департаменты: всего <span>${department}</span> департаментов</h3>
                                          <h3>Отделы: всего <span>${otdel}</span> отделов</h3>
                                           <h3>Должности: всего <span>${position}</span> должностей</h3>
                                            <h3>Сотрудники: всего <span>${employee}</span> сотрудников</h3>
</body>
</html>