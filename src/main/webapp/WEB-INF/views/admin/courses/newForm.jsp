<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastrar novo Curso</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/assets/external-libs/bootstrap/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h2>Cadastrar novo Curso</h2>

    <form:form modelAttribute="newCourse"
               cssClass="form-horizontal panel-body"
               action="/admin/courses/new"
               method="post">

        <div class="form-group col-md-9">
            <label for="newCourse-name">Nome:</label>
            <form:input path="name" id="newCourse-name" cssClass="form-control" required="required"/>
            <form:errors path="name" cssClass="text-danger"/>
        </div>

        <div class="form-group col-md-9">
            <label for="newCourse-code">Código:</label>
            <form:input path="code" id="newCourse-code" cssClass="form-control" required="required"/>
            <form:errors path="code" cssClass="text-danger"/>
        </div>

        <div class="form-group col-md-9">
            <label for="newCourse-instructor">Instrutor:</label>
            <form:input path="instructor" id="newCourse-instructor" cssClass="form-control" required="required"/>
            <form:errors path="instructor" cssClass="text-danger"/>
        </div>

        <div class="form-group col-md-9">
            <label for="newCourse-category">Categoria:</label>
            <form:input path="category" id="newCourse-category" cssClass="form-control" required="required"/>
            <form:errors path="category" cssClass="text-danger"/>
        </div>

        <div class="form-group col-md-9">
            <label for="newCourse-description">Descrição:</label>
            <form:input path="description" id="newCourse-description" cssClass="form-control"/>
            <form:errors path="description" cssClass="text-danger"/>
        </div>

        <div class="form-group col-md-9 mt-3">
            <input class="btn btn-success" type="submit" value="Salvar"/>
        </div>
    </form:form>
</div>

</body>
</html>
