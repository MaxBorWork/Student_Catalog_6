<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student Page</title>
    <%@ include file="/view/include.jsp" %>
    <style>
        #progress {
            display: none;
            color: green;
            font-size: 18px;
            text-align: center;
        }
    </style>
</head>
<body>
    <%@ include file="/view/menu.jsp" %>
    <div class="container main-container">
        <div class="row">
            <div class="add-form col-sm-6 col-sm-offset-3">
                <form action="<c:url value="/addStudent"/>" method="post" id="addStudentForm" role="form" class="addStudentForm">
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" id="studentFullName"
                               name="studentFIO" value="${studentFIO}"
                               pattern="[A-Za-zА-Яа-яЁё]+\s[A-Za-zА-Яа-яЁё]+\s[A-Za-zА-Яа-яЁё]+"
                               class="form-control" required placeholder="Фамилия Имя Отчество"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <select name="studentGroup" id="studentGroup"
                                class="form-control" required placeholder="№ группы">
                            <c:forEach items="${groupsList}" var="groupNum">
                                <option>${groupNum}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" name="studentCity" value="${studentCity}" id="studentCity"
                               class="form-control" pattern="^[A-Za-zА-Яа-яЁё0-9\s_-]+$" required placeholder="Город"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" name="studentStreet" value="${studentStreet}" id="studentStreet"
                               class="form-control" pattern="^[A-Za-zА-Яа-яЁё0-9\s_-]+$" required placeholder="Улица"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" name="studentHouse" value="${studentHouse}" id="studentHouse"
                               class="form-control" pattern="^[A-Za-zА-Яа-яЁё0-9\s_-]+$" required placeholder="Дом"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" name="studentFlat" value="${studentFlat}" id="studentFlat"
                               class="form-control" pattern="^[A-Za-zА-Яа-яЁё0-9\s_-]+$" required placeholder="Квартира"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="hidden" name="studentID" value="${studentID}" id="studentID"
                               class="form-control" pattern="^[A-Za-zА-Яа-яЁё0-9\s_-]+$" placeholder="ID"/>
                    </div>
                    <button id="addBtn" type="submit" class="btn submit-button col-sm-6 col-sm-offset-3">
                        <span>Добавить</span>
                    </button>
                    <br />
                </form>
                <div id="progress">Операция успешна</div>
            </div>
        </div>
    </div>
</body>
<script>
    $(document).ready(function() {
        $('#addStudentForm').submit(function() {
            $('#progress').show();
        });
    });
</script>
</html>
