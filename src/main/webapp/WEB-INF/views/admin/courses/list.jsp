<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Spring Boot JSP</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/@phosphor-icons/web@2.1.1/src/regular/style.css"/>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/notiflix@3.2.6/dist/notiflix-3.2.6.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/notiflix@3.2.6/dist/notiflix-3.2.6.min.js"></script>

    <link rel="stylesheet" href="/assets/css/global.css">
    <link rel="stylesheet" href="/assets/css/UI/layout/navbar.css">
    <link rel="stylesheet" href="/assets/css/UI/layout/table.css">
    <link rel="stylesheet" href="/assets/css/UI/form/popup.css">
    <link rel="stylesheet" href="/assets/css/UI/buttons/buttons.css">
</head>
<body>
<%@ include file="../layout.jsp" %>

<div class="layout-main">
    <c:if test="${not empty successMessage}">
        <div class="popup success-popup" id="successPopup">
            <p>${successMessage}</p>
            <span class="close" onclick="closePopup()">&times;</span>
        </div>
    </c:if>

    <c:if test="${not empty successMessageInactive}">
        <div class="popup success-popup" id="successPopupInactive">
            <p>${successMessageInactive}</p>
            <span class="close" onclick="closePopup()">&times;</span>
        </div>
    </c:if>

    <div class="title-page flex-between">
        <div>
            <h2>Cursos</h2>
            <p>Visualize todos os cursos já cadastrados.</p>
        </div>
        <a class="btn btn-create" href="/admin/courses/new">Novo Curso</a>
    </div>

    <div class="card-list">
        <table class="responsive-table">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th>Instrutor</th>
                <th>Categoria</th>
                <th>Descrição</th>
                <th>Status</th>
                <th>Data Inativação</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.name}</td>
                    <td>${course.code}</td>
                    <td>${course.instructor}</td>
                    <td>${course.category}</td>
                    <td>${empty course.description ? 'Não informado' : course.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${course.status == 'ACTIVE'}">
                                <span class="status status-active">Ativo</span>
                            </c:when>
                            <c:when test="${course.status == 'INACTIVE'}">
                                <span class="status status-inactive">Inativo</span>
                            </c:when>
                            <c:otherwise>
                                ${course.status}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${course.status == 'INACTIVE'}">
                                <fmt:formatDate value="${course.inactivationDate}" pattern="dd/MM/yyyy HH:mm" />
                            </c:when>
                            <c:otherwise>
                                O curso atualmente está ativo.
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <div class="space-items">
                            <c:if test="${course.status == 'ACTIVE'}">
                                <form action="${pageContext.request.contextPath}/admin/courses/${course.code}/inactive" method="post" onsubmit="event.preventDefault(); showLoadingAndSubmit(this);">
                                    <input type="hidden" name="_method" value="post"/>
                                    <button type="submit" class="btn btn-inactive btn-small" title="Inativar curso">
                                        <i class="ph ph-x-circle"></i>
                                    </button>
                                </form>
                            </c:if>

                            <button class="btn btn-edit btn-small" title="Editar curso">
                                <i class="ph ph-pen"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    window.addEventListener('DOMContentLoaded', () => {
        ['successPopupCreate','successPopupInactive'].forEach(id => {
            const popup = document.getElementById(id);
            if(popup) {
                popup.classList.add('show');
                setTimeout(() => popup.classList.remove('show'), 3000);
            }
        });
    });

    function closePopup(id) {
        const popup = document.getElementById(id);
        if(popup) popup.classList.remove('show');
    }

    function showLoadingAndSubmit(form) {
        showLoading();
        setTimeout(() => form.submit(), 50);
    }


    function showLoading() {
        Notiflix.Loading.standard('Carregando...');
    }

    function hideLoading() {
        Notiflix.Loading.remove();
    }

    Notiflix.Loading.standard('Carregando...');

    window.addEventListener('DOMContentLoaded', () => {
        Notiflix.Loading.remove();
    });
</script>
</body>
</html>
