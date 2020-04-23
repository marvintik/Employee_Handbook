<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <h2 class="hello-title">Hello ${name}!</h2>
    <script src="/js/main.js"></script>
        <c:forEach items="${listDepartment}" var="department">
        <tr>
            <td>${department.id}</td>
            <td>${department.title}</td>
            <td>${department.address}</td>
            <td>
</body>
</html>