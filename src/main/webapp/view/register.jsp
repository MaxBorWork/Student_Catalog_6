<%--
  Created by IntelliJ IDEA.
  User: maksim
  Date: 03/05/19
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>Registration</title>
    <%@ include file="/view/include.jsp" %>
</head>
<body>
<%@ include file="/view/menu.jsp" %>
<div class="container main-container">
    <div class="row">
        <div class="log-form col-sm-6 col-sm-offset-3">
            <form action="<c:url value="/register"/>" method="post" id="regForm" role="form" class="regForm">
                <div class="form-group col-xs-10 col-xs-offset-1">
                    <input type="text" id="username" name="username"
                           class="form-control" required placeholder="Имя пользователя"/>
                </div>
                <div class="form-group col-xs-10 col-xs-offset-1">
                    <input type="password" name="password" id="password"
                           class="form-control" required placeholder="Пароль"/>
                </div>
                <div class="form-group col-xs-10 col-xs-offset-1">
                    <input type="email" name="userEmail" id="userEmail"
                           class="form-control" required placeholder="E-mail"/>
                </div>
                <button id="regBtn" type="submit" class="btn submit-button col-sm-6 col-sm-offset-3">
                    <span>Регистрация</span>
                </button>
                <br />
            </form>
        </div>
    </div>
    <div class="row">
        <div class="register-success">
            <c:if test="${successRegistration != null}">${successRegistration}</c:if>
        </div>
    </div>
</div>
</body>
</html>
