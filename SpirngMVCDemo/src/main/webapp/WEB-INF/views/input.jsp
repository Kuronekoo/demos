<%--
  Created by IntelliJ IDEA.
  User: Kuroneko
  Date: 2017/9/28
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">

    <c:if test="${empty employee.id}">
        lastName:<form:input path="lastName" />
    </c:if>

    <c:if test="${!empty employee.id}">
        <form:hidden path="id"/>
        lastName:<form:input path="lastName"  disabled="true"/>
        <input type="hidden" name="_method" value="PUT"/>
    </c:if>

    <br>
    Email:<form:input path="email"/>
    <br>
   Gender <form:radiobuttons path="gender" items="${genders}"/>
    <br>
    department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/>
    <br>
    <input type="submit" value="submit">
</form:form>
</body>
</html>
