<%--
  Created by IntelliJ IDEA.
  User: maksim
  Date: 04/05/19
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <%@ include file="/view/include.jsp" %>
</head>
<body>
    <%@ include file="/view/menu.jsp" %>
    <div class="container table-container">
        <div class="row">
            <table class="table users-table">
                <tr>
                    <td>Логин</td>
                    <td>Пароль</td>
                    <td>E-mail</td>
                    <td>Роль</td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td class="student-info"><span>${user.username}</span></td>
                        <td class="student-info"><span>${user.password}</span></td>
                        <td class="student-info"><span>${user.email}</span></td>
                        <td class="student-info"><span>${user.role}</span></td>
                        <td>
                            <%  if (session.getAttribute("user") != null && session.getAttribute("role").equals("sudo")) {%>
                            <form action="" method="POST">
                                <button type="submit" class="editBtn btn student-button"
                                        name="userButton" value="editUser_${user.username}">Изменить</button>
                            </form>
                            <% } %>
                        </td>
                        <td>
                            <form action="" method="POST">
                                <button type="submit" class="delBtn btn student-button"
                                        name="userButton" value="delUser_${user.username}">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="student-paging container col-sm-offset-4 col-sm-4">
        <c:if test="${currentPage != 1}">
            <a href="${pageContext.request.contextPath}/showStudents?page=${currentPage - 1}">Prev</a>
        </c:if>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <span>${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/showStudents?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt noOfPages}">
            <a href="${pageContext.request.contextPath}/showStudents?page=${currentPage + 1}">Next</a>
        </c:if>
    </div>
</body>
</html>
