<%--
  Created by IntelliJ IDEA.
  User: maksim
  Date: 04/05/19
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/view/include.jsp" %>
</head>
<body>
<%@ include file="/view/menu.jsp" %>
<div class="container main-container">
    <div class="row">
        <form action="<c:url value="/register"/>" method="post" id="roleForm" role="form" class="roleForm">
            <div class="form-group col-xs-10 col-xs-offset-1">
                <label for="role">Роль</label><select name="role" id="role" class="form-control" required>
                <c:forEach items="${rolesList}" var="role">
                    <option>${role}</option>
                </c:forEach>
            </select>
            </div>
            <div class="form-group col-xs-10 col-xs-offset-1">
                <input type="hidden" name="username" value="${username}" id="username"
                       class="form-control" placeholder="ID"/>
            </div>
            <button id="roleBtn" type="submit" class="btn submit-button col-sm-6 col-sm-offset-3">
                <span>Сохранить</span>
            </button>
            <br />
        </form>
    </div>
    <div class="row">
        <div class="register-success">
            <c:if test="${successRegistration != null}">${successRegistration}</c:if>
        </div>
    </div>
</div>
</body>
</html>
