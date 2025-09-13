<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Spring Boot JSP</title>
</head>
<body>
<table class="panel-body table table-hover">
    <thead>
    <tr>
        <th>Nome</th>
        <th>Código</th>
        <th>Instrutor</th>
        <th>Categoria</th>
        <th>Descricao</th>
        <th>Status</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.name}</td>
            <td>${course.code}</td>
            <td>${course.instructor}</td>
            <td>${course.category}</td>
            <td>${course.description}</td>
            <td>${course.status}</td>
            <td><a class="btn btn-primary" href="/admin/course/edit/${course.id}">Editar</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
