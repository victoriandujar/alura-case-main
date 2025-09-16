<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
    <title>Editar Categoria</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/@phosphor-icons/web@2.1.1/src/regular/style.css"/>

    <link rel="stylesheet" href="/assets/css/global.css">
    <link rel="stylesheet" href="/assets/css/UI/index.css">
</head>

<%@ include file="../layout.jsp" %>
<body>
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
                    <h2>Editar Categoria - ${editCategory.name}</h2>
                    <p class="subtitle">Preencha os campos abaixo para editar uma categoria.</p>
                </div>
            </div>

            <c:url var="formActionUrl" value="/admin/category/edit/${editCategory.id}" />
            <form:form modelAttribute="editCategory" cssClass="form-grid" action="${formActionUrl}" method="post">
                <div class="form-group">
                    <label for="newCategory-name">Nome:</label>
                    <form:input path="name"
                                id="newCategory-name"
                                placeholder="Ex: UI & UX"
                                cssClass="form-control"
                                required="required"/>
                </div>

                <div class="form-group">
                    <label for="newCategory-code">Código:</label>
                    <form:input path="code"
                                id="newCategory-code"
                                placeholder="Ex: ui-ux"
                                cssClass="form-control"
                                required="required"/>
                </div>

                <div class="form-group">
                    <label for="newCategory-color">Cor:</label>
                    <div style="display: flex; align-items: center; gap: 10px;">
                        <form:input path="color"
                                    id="newCategory-color"
                                    type="color"
                                    cssClass="form-control"
                                    required="required"/>
                        <div id="colorPreview" style="width: 40px; height: 40px; border: 1px solid #ccc;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="newCategory-order">Ordem:</label>
                    <form:input path="order"
                                type="number"
                                min="1" id="newCategory-order"
                                placeholder="Ex: 1"
                                cssClass="form-control"
                                required="required"/>
                </div>

                <div class="form-actions full-width">
                    <input class="btn btn-save" type="submit" value="Editar"/>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script src="/assets/js/colorPicker.js"></script>
</body>
</html>