<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Student Catalog</title>
    <%@ include file="/view/include.jsp" %>
</head>

<body>
    <%@ include file="/view/menu.jsp" %>
    <div class="container table-container">
        <div class="row">
            <table class="table student-table">
                <tr>
                    <td rowspan="2" class="huge-title">ФИО</td>
                    <td rowspan="2" class="huge-title">Группа</td>
                    <td colspan="4" style="font-size:18px;font-weight:800">Адрес прописки</td>
                    <td rowspan="2"></td>
                    <td rowspan="2"></td>
                </tr>
                <tr class="address-titles">
                    <td>Город</td>
                    <td>Улица</td>
                    <td>Дом</td>
                    <td>Квартира</td>
                </tr>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td class="student-info"><span>${student.surname} ${student.name} ${student.secondName}</span></td>
                        <td class="student-info"><span>${student.groupNum}</span></td>
                        <td class="student-info"><span>${student.city}</span></td>
                        <td class="student-info"><span>${student.address.street}</span></td>
                        <td class="student-info"><span>${student.address.house}</span></td>
                        <td class="student-info"><span>${student.address.flat}</span></td>
                        <td>
                            <form action="" method="POST">
                                <button type="submit" class="editBtn btn student-button"
                                        name="studentButton" value="editStudent_${student.id}">Изменить</button>
                            </form>
                        </td>
                        <td>
                            <form action="" method="POST">
                                <button type="submit" class="delBtn btn student-button"
                                        name="studentButton" value="delStudent_${student.id}">Удалить</button>
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
