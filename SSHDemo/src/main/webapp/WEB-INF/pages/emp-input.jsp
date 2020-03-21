<%--
  Created by IntelliJ IDEA.
  User: Kuroneko
  Date: 2017/9/21
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
    <title>add employee</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
    <script src="<%=basePath %>js/jquery.min.js"></script>
    <sx:head/>
    <s:head/>

    <script>
        $(function () {

            $(":input[name=name]").change(function () {

                var self = $(this);
                var name = self.val().trim();
                self.nextAll("font").remove();
                if(name === ""){
                    self.after("<font color = 'red'>姓名不能为空！</font>");
                }else {
                   var url = "/emp!validateName";
                   var args = {"name":name,"time":new Date().getTime()};

                   $.post(url,args,function (data) {
                       if(data == "1"){
                           self.after("<font color = '#20b2aa'>姓名可用</font>");
                       }else if (data == "0"){
                           self.after("<font color = 'red'>姓名已存在！</font>");
                       }else {
                           alert("服务器出错！");
                       }
                   });
                }
            })

            $("#form1").on("click",function () {

                var name = $(":input[name=name]").val().trim();
                var email = $(":input[name=email]").val().trim();
                var birth = $(":input[name=birth]").val().trim();

                if(!name){
                    alert("name不能为空");
                    return false;
                }
                if(!email){
                    alert("email不能为空");
                    return false;
                }
                if(!birth){
                    alert("birth不能为空");
                    return false;
                }
            });
        })
    </script>
</head>
<body>

<s:form action="emp!save" method="POST" >
    <s:if test="id != nll">
        <s:textfield name="name" label="Name" disabled="true"/>
        <s:hidden  name = "id"/>
    </s:if>
    <s:else>
        <s:textfield name="name" label="Name"/>
    </s:else>
    <s:textfield name="email" label="Email"/>
    <sx:datetimepicker name="birth" displayFormat="yyyy-MM-dd" value="%{date}" label="Birth"/>
<%--
    <s:textfield name = "birth" label="Birth"/>
--%>

    <s:select list="#request.departments" label="Department" name = "departmentEntity.id"
              listKey="id" listValue="departmentName"
    />
    <s:submit id = "form1" />
</s:form>


</body>
</html>
