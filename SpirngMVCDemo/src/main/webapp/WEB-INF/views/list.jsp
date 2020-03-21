<%--
  Created by IntelliJ IDEA.
  User: Kuroneko
  Date: 2017/9/27
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
                $("#delete").attr("action",href).submit();
                return false;
            });

        })
    </script>
</head>
<body>

<a href="emp">add New Employee</a>

<form id = "delete" action="" method="post">
    <input type="hidden" name="_method" value="DELETE" />
</form>


<c:if test="${empty requestScope.employees}">
    没有任何员工信息
</c:if>
<c:if test="${!empty requestScope.employees}">
<table cellpadding="10" cellspacing="0" border="1">
    <thead>
    <tr>
    <td>id</td>
    <td>lastName</td>
    <td>email</td>
    <td>gender</td>
    <td>departmentId</td>
    <td>departmentName</td>
    <td>delete</td>
    <td>edit</td>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${requestScope.employees}" var ="emp" varStatus="status">
    <tr>
        <td>${emp.id}</td>
        <td>${emp.lastName}</td>
        <td>${emp.email}</td>
        <td>${emp.gender == 1?"male ":"female"}</td>
        <td>${emp.department.id}</td>
        <td>${emp.department.departmentName}</td>
        <td><a class = "delete" href="emp/${emp.id}">delete</a></td>
        <td><a href="emp/${emp.id}">edit</a></td>
    </tr>
</c:forEach>
    </tbody>
</table>
</c:if>
</body>
</html>
