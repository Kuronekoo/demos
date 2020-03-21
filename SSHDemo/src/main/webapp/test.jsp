<%--
  Created by IntelliJ IDEA.
  User: Kuroneko
  Date: 2017/10/23
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
    <script src="<%=basePath %>js/jquery.min.js"></script>
</head>

<body>

<form action="" method="post">

    test: <input type="text" id="test"/>
    test2: <input type="text" id="test2"/>
    <input type="submit" value="submit" id="cli"/>
</form>
<script>
//    alert(123);
$(function () {
    var test = $("#test").val().trim();
    var test1 = $("#test2").val().trim();
    $("#cli").on("click",function () {
        if(!test){
            alert("test");
            return false;
        }
        if(!test2){
            alert("test2");
            return false;
        }
    });



});


</script>
</body>
</html>
