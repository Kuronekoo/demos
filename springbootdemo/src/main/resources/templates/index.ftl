<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <script src="http://www.java1234.com/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script>

        var f = {};
        f.testAjax = function () {
            $.post("/ajax",{},function (data) {
                alert(data);
            });
        };
           $(function () {

           })
    </script>
</head>
<body>
<h1>index</h1>
<button onclick="f.testAjax()">click me</button>
</body>
</html>