<%--
  Created by IntelliJ IDEA.
  User: maksim
  Date: 20/03/19
  Time: 00:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<div class="container menu-bar navbar collapse navbar-collapse">
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4 title">
            <ex:Title/>
        </div>
        <ul class="user-menu">
            <%  if (session.getAttribute("user") != null) {%>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/login"/>">Выйти</a></li>
            <% } else { %>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/login"/>">Войти</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/register"/>">Регистрация</a></li>
            <% } %>
        </ul>
            <%  if (session.getAttribute("user") != null) {%>
                <div class="username">
                    <c:if test="${username != null}">Логин <span>${username}</span></c:if>
                </div>
            <% } %>
    </div>
    <div class="row">
        <ul class="col-sm-offset-3 col-sm-6">
            <%  if (session.getAttribute("user") != null) {%>
                <li class="nav-item"><a class="nav-link" href="<c:url value="/showStudents?page=1"/>">Показать</a></li>
                <%  if (session.getAttribute("user") != null && session.getAttribute("role").equals("admin") ||
                        session.getAttribute("role").equals("sudo")) {%>
                <li class="nav-item"><a class="nav-link" href="<c:url value="/addStudent"/>">Добавить</a></li>
                <li class="nav-item"><a class="nav-link" href="<c:url value="/users"/>">Пользователи</a></li>
                <% } %>
            <% } %>
        </ul>
    </div>
</div>
