<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Categorias</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/@phosphor-icons/web@2.1.1/src/regular/style.css"/>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/notiflix@3.2.6/dist/notiflix-3.2.6.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/notiflix@3.2.6/dist/notiflix-3.2.6.min.js"></script>

    <link rel="stylesheet" href="/assets/css/global.css">
    <link rel="stylesheet" href="/assets/css/UI/index.css">
</head>

<%@ include file="../layout.jsp" %>
<script src="/assets/js/loadingData.js"></script>

<body>
<div class="layout-main">
    <c:if test="${not empty successMessage}">
        <script>
            Notiflix.Notify.success('${successMessage}');
        </script>
    </c:if>

    <c:if test="${not empty successMessageInactive}">
        <script>
            Notiflix.Notify.success('${successMessageInactive}');
        </script>
    </c:if>

    <c:if test="${not empty successEditMessage}">
        <script>
            Notiflix.Notify.success('${successEditMessage}');
        </script>
    </c:if>

    <div class="title-page flex-between">
        <div>
            <h2>Categorias</h2>
            <p>Visualize todas as categorias já cadastradas.</p>
        </div>
        <a class="btn btn-create" href="/admin/category/new">Nova Categoria</a>
    </div>

    <div class="card-list">
        <table class="responsive-table">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th>Cor</th>
                <th>Ordem</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.name}</td>
                    <td>${category.code}</td>
                    <td>
                        <span class="category-color-tag" style="background-color: ${category.color};">
                                ${category.color}
                        </span>
                    </td>
                    <td>${category.order}</td>
                    <td>
                        <a href="/admin/category/edit/${category.id}">
                            <button class="btn btn-edit btn-small" title="Editar categoria">
                                <i class="ph ph-pen"></i>
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>