<html>
<body>
<h2>Hello World!</h2>

<br><br>

<a href="listEmps">listEmps</a>
<br><br>
<br><br>
<br><br>
<form action="/testModelAttribute" method="post">
    <input type="hidden" name="id" value="1"/>
    <input type="text" name="username" value="tom"/>
    <input type="text" name="age" value="12"/>
    <input type="submit" value="submit"/>
</form>

<br><br>
<form action="/testRest/1" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
    <input type="submit" value="testRestDelete"/>
</form>
<br><br>

<a href="testPathVariable/1">testPathVariable</a>

<br><br>

<a href="helloworld">helloworld</a>
<br><br>
<form action="/test_date" method="get">
    <input type="date" name="user_date"   value="2015-09-24" />
    <input type="submit" />
</form>

<br><br>
<a href="testJson">testJson</a>

</body>
</html>
