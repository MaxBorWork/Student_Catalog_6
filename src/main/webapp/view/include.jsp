<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />" >

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
    .menu-bar {
        text-align: center;
        margin-top: 20px;
    }

    .menu-bar ul {
        display: -webkit-box;
        margin-top: 10px;
        padding-left: 115px;
    }

    .menu-bar ul li {
        text-decoration: none;
        list-style: none;
        color: #000000;
        margin-right: 50px;
        font-size: 18px;
        font-weight: 600;
    }

    .menu-bar ul li a {
        text-decoration: none;
        color: #000000;
    }

    .menu-bar ul li a:hover, .student-paging a:hover {
        text-decoration: none;
        color: #000000;
    }

    .menu-bar ul li a.focus, .menu-bar ul li a:focus {
        text-decoration: underline;
        color: #000000;
    }

    .addStudentForm, .loginForm, .regForm {
        display: grid;
        text-align: center;
    }

    .addStudentForm button, .loginForm button, .regForm button {
        background: #000;
        color: #fff;
        font-size: 18px;
        font-weight: 700;
    }

    .addStudentForm .btn:hover, .loginForm .btn:hover, .regForm .btn:hover {
        color: #fff;
    }

    .addStudentForm .btn.focus, .addStudentForm .btn:focus,
    .loginForm .btn.focus, .regForm.btn.focus, .loginForm .btn:focus, .regForm .btn:focus {
        color: #fff;
        font-size: 20px;
        font-weight: 700;
    }

    .student-table, .student-paging {
        text-align: center;
    }

    .student-table .huge-title {
        padding-top: 25px;
        font-weight: 800;
        font-size: 18px;
    }

    .address-titles {
        font-size: 16px;
        font-weight: 700;
    }

    .table-container .student-table tr td.student-info {
        padding-top: 15px;
    }

    .title {
        font-size: 28px;
        font-weight: 700;
        text-align: center;
        color: blue;
        padding-left: 0;
    }
    .student-paging a, .student-paging span {
        color: black;
        font-size: 18px;
    }
    .login-error, .register-success {
        text-align: center;
        margin: 50px;
        color: red;
        font-size: 18px;
    }
    .register-success {
        color: limegreen;
    }
</style>
