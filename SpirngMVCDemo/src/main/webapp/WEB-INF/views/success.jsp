<%--
  Created by IntelliJ IDEA.
  User: Kuroneko
  Date: 2017/9/26
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>success page</h4>
sESSION:
<br><br>
session user: ${sessionScope.user}
<br><br>
abc user: ${sessionScope.abc}
<br><br>
zzzz user: ${sessionScope.zzzz}
<br><br>
REQUEST
<br><br>
request user: ${requestScope.user}
<br><br>
zzzz user: ${sessionScope.zzzz}
<br><br>
abc user: ${requestScope.abc}
<br><br>
<fmt:message key="i18n.username"/>
<br><br>
<fmt:message key="i18n.password"/>
</body>
</html>
