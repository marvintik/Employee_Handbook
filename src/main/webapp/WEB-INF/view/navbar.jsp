<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-16">
    <title>Главная форма</title>

    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
    <script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
</head>

<body>
    <nav class="navbar navbar-inverse">
    <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">Справочник</a>
                </div>
            <div id="myNavbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/">Домашняя страница</a></li>
                    <li><a href="/employees">Работники</a></li>
                    <li><a href="/departments">Департаменты</a></li>
                    <li><a href="/groups">Отделы</a></li>
                    <li><a href="/positions">Должности</a></li>
                    <li><a href="/employees/search">Поиск по сотрудникам</a></li>
                </ul>
            </div>
               </div>
    </nav>
</body>
</html>