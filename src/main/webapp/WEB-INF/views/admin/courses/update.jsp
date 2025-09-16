<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Curso</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/@phosphor-icons/web@2.1.1/src/regular/style.css"/>

    <link rel="stylesheet" href="/assets/css/global.css">
    <link rel="stylesheet" href="/assets/css/UI/index.css">
</head>
<body>
<%@ include file="../layout.jsp" %>

<div class="bg-teste">
    <div class="form-container">
        <div class="form-left">
            <img src="<c:url value='/assets/images/bg-course-edit.png' />" />
        </div>

        <div class="form-right">
            <div>
                <a href="javascript:history.back()" class="btn-back">
                    <i class="ph ph-arrow-left"></i> Voltar
                </a>

                <div>
                    <h2>Editar Curso - ${editCourse.name}</h2>
                    <p class="subtitle">Preencha os campos abaixo para editar o curso.</p>
                </div>
            </div>

            <c:url var="formActionUrl" value="/admin/courses/edit/${editCourse.id}" />
            <form:form modelAttribute="editCourse" cssClass="form-grid" action="${formActionUrl}" method="post">
                <div class="form-group">
                    <label for="editCourse-name">Nome:</label>
                    <form:input path="name" id="editCourse-name"
                                cssClass="form-control"
                                required="required"
                                placeholder="Ex: React Native"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>

                <div class="form-group">
                    <label for="editCourse-code">Código:</label>
                    <form:input path="code"
                                id="editCourse-code"
                                placeholder="Ex: react-nt"
                                cssClass="form-control"
                                required="required"/>
                    <form:errors path="code" cssClass="text-danger"/>
                </div>

                <div class="form-group">
                    <label for="newCourse-instructorId">Instrutor:</label>
                    <form:select path="instructorId"
                                 id="newCourse-instructorId"
                                 cssClass="form-control"
                                 required="required">
                        <form:option value="" label="Selecione o instrutor"/>
                        <form:options items="${instructors}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </div>

                <div class="form-group">
                    <label for="editCourse-categoryId">Categoria:</label>
                    <form:select path="categoryId"
                                 id="editCourse-categoryId"
                                 cssClass="form-control"
                                 required="required">
                        <form:option value="" label="Selecione a categoria"/>
                        <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="categoryId" cssClass="text-danger"/>
                </div>

                <div class="form-group full-width">
                    <label for="editCourse-description">Descrição:</label>
                    <form:textarea path="description"
                                   id="editCourse-description"
                                   placeholder="Digite uma descrição detalhada do curso"
                                   cssClass="form-control"/>
                    <form:errors path="description" cssClass="text-danger"/>
                </div>

                <div class="form-actions full-width">
                    <input type="submit" class="btn btn-save" value="Editar">
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
