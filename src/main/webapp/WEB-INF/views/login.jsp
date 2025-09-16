<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="/assets/css/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap" rel="stylesheet">
</head>
<body class="login-page">
<div class="container">
    <div class="login-box">
        <h2>Já estuda com a gente?</h2>
        <p>Faça seu login e boa aula!</p>
        <a href="/admin/courses" class="btn-login">ENTRAR</a>
    </div>

    <div class="courses">
        <h2>Ainda não estuda com a gente?</h2>
        <p>São mais de mil cursos nas seguintes áreas:</p>

        <div class="grid">
            <c:forEach items="${reportData}" var="categoryData">
                <div class="card">
                    <h3 style="color: ${categoryData.color};">Escola_</h3>
                    <p style="color: ${categoryData.color};">${categoryData.name}</p>

                        <c:if test="${empty categoryData.courses}">
                            <p>Nenhum curso nesta categoria.</p>
                        </c:if>

                        <c:if test="${not empty categoryData.courses}">
                            <c:forEach items="${categoryData.courses}" var="course" varStatus="loop">
                                <c:if test="${not empty course.name}">
                                    <span>${course.name}</span><c:if test="${not loop.last}">, </c:if>
                                </c:if>
                            </c:forEach>
                        </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
