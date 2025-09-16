<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<nav class="navbar">
    <div class="navbar-container">
        <h2 class="navbar-brand">
            <img src="<c:url value='/assets/images/alura-logo.svg' />" alt="Logo Alura" class="navbar-brand-img">
        </h2>

        <div class="divider"></div>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="/admin/courses" class="nav-link ${fn:contains(pageContext.request.requestURI, '/admin/courses') ? 'active' : ''}">Cursos</a>
            </li>
            <li class="nav-item">
                <a href="/admin/categories" class="nav-link ${fn:contains(pageContext.request.requestURI, '/admin/categories') ? 'active' : ''}">Categorias</a>
            </li>
        </ul>

        <div class="user-info">
            <i class="ph ph-user"></i>
            <span>Bem-Vindo! Admin</span>
        </div>
    </div>
</nav>