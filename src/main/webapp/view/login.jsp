<%--
  Created by IntelliJ IDEA.
  User: maksim
  Date: 27/04/19
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
    <%@ include file="/view/include.jsp" %>
</head>
<body>
<%@ include file="/view/menu.jsp" %>
<div class="container main-container">
    <div class="row">
        <div class="log-form col-sm-6 col-sm-offset-3">
            <form action="<c:url value="/login"/>" method="post" id="loginForm" role="form" class="loginForm">
                <div class="form-group col-xs-10 col-xs-offset-1">
                    <input type="text" id="username" name="username"
                           class="form-control" required placeholder="Имя пользователя"/>
                </div>
                <div class="form-group col-xs-10 col-xs-offset-1">
                    <input type="password" name="password" id="password"
                           class="form-control" required placeholder="Пароль"/>
                </div>
                <button id="LoginBtn" type="submit" class="btn submit-button col-sm-6 col-sm-offset-3">
                    <span>Войти</span>
                </button>
                <br />
            </form>
        </div>
    </div>
    <div class="row">
        <div class="ss-bnt col-sm-4 col-sm-offset-4">
            <ul>
                <li><a class="vk-btn" href="https://oauth.vk.com/authorize?client_id=6970510&display=page&redirect_uri=http://localhost:8080/lab6/login/vk&scope=friends&response_type=code&v=5.95&state=123456">Vk</a></li>
                <li class="margin-left: 15%;"><a class="yandex-btn" href="https://oauth.yandex.ru/authorize?response_type=code&client_id=e3c965087a944fb584e903f7b491f4e6&redirect_uri=http://localhost:8080/lab6/login/yandex&force_confirm=yes">Яндекс</a></li>
            </ul>

        </div>
    </div>
    <div class="row">
        <div class="login-error">
            <c:if test="${message != null}">${message}</c:if>
        </div>
    </div>
</div>

</body>
</html>
