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
    <div class="row">
        <div class="ss-bnt">
            <ul>
                <li><a class="vk-btn" href="https://oauth.vk.com/authorize?client_id=6970510&display=page&redirect_uri=http://localhost:8800/borisevich_war/login/vk&scope=friends&response_type=code&v=5.95&state=123456">Vk</a></li>
                <li>Google</li>
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
