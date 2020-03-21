<%--
  Created by IntelliJ IDEA.
  User: Kuroneko
  Date: 2017/9/20
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>emp-list</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
    <script src="<%=basePath %>js/jquery.min.js"></script>
    <script>
        $(function () {

            $(".delete").on("click",function () {
                var name = $(this).next(":input").val().trim();
                var flag = confirm("确定要删除 【" + name +"】 员工的信息吗");
                if(flag){
                    var $tr = $(this).parent().parent();
                    var url = this.href;
                    var args = {};
                   $.post(url,args,function (data) {
                       if(data == "1"){
                           alert("删除成功");
                           $tr.remove();
                       }else {
                           alert("删除失败，出现异常")  ;
                       }
                   });
                }
                return false;
            })


        })
    </script>
</head>
<body>

<a href="emp!input">add new employee</a>
<s:if test="#request.employees == null || #request.employees.size() == 0">
    没有员工信息
</s:if>
<s:else>
    <table border="1" cellspacing="0" cellpadding="10">
        <thead>
        <tr>
            <td>ID</td>
            <td>NAME</td>
            <td>EMAIL</td>
            <td>BIRTH</td>
            <td>CREATE_DATE</td>
            <td>DEPT_ID</td>
            <td>DEPT_NAME</td>
            <td>DELETE</td>
            <td>EDIT</td>
        </tr>
        </thead>

        <tbody>
        <s:iterator value="#request.employees">
        <tr>
        <td>${id}</td>
        <td>${name}</td>
        <td>${email}</td>
        <td>${birth}</td>
        <td>${createTime}</td>
        <td>${DepartmentEntity.id}</td>
        <td>${DepartmentEntity.departmentName}</td>
        <td>
            <a class ="delete"  href="emp!delete?id=${id}">delete</a>
            <input type="hidden" value="${name}"/>
        </td>
        <td>
            <a class ="edit"  href="emp!input?id=${id}">edit</a>
        </td>
        </tr>
        </s:iterator>
        </tbody>

    </table>
</s:else>

</body>
</html>
