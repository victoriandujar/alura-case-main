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
    <link rel="stylesheet" href="/assets/css/UI/form/form.css">
</head>
<body class="login-page">
<div class="container">
    <div class="login-box">
        <div class="title">
            <h2>Já estuda com a gente?</h2>
            <p>Faça seu login e boa aula!</p>
        </div>

        <form action="<c:url value='/signin'/>" method="post">
            <div class="form-group">
                <label for="email">E-mail:</label>
                <input class="form-control" type="text" name="email" id="email" required/>
            </div>

            <div class="form-group">
                <label for="password">Senha:</label>
                <input class="form-control" type="password" name="password" id="password" required/>
            </div>

            <div class="form-actions full-width">
                <button type="submit" class="btn-login">ENTRAR</button>
            </div>

            <c:if test="${not empty param.error}">
                <p style="color:red;">Usuário ou senha inválidos</p>
            </c:if>

            <c:if test="${not empty param.logout}">
                <p style="color:green;">Logout realizado com sucesso!</p>
            </c:if>
        </form>
    </div>

    <div class="courses">
        <h2>Ainda não estuda com a gente?</h2>
        <p>São mais de mil cursos nas seguintes áreas:</p>
    </div>
</div>
</body>
</html>
