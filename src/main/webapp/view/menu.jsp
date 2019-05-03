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
    </div>
    <div class="row">
        <ul class="col-sm-offset-4 col-sm-4">
            <li class="nav-item"><a class="nav-link" href="<c:url value="/showStudents?page=1"/>">Показать</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/addStudent"/>">Добавить</a></li>

            <li class="nav-item"><a class="nav-link" href="<c:url value="/login"/>">Войти</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/register"/>">Регистрация</a></li>
        </ul>
    </div>
</div>
