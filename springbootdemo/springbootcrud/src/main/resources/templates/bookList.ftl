<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/bookAdd.html">添加图书</a>
<form action="/book/list2" method="post">
    书名：<input type="text" name="bookName"/>&nbsp;
    价格位于&nbsp; <input type="text" name="lowPrice"/>&nbsp; 和&nbsp;<input type="text" name="highPrice"/>&nbsp;
    <input type="submit" value="search"/>
</form>
<table>
    <thead>
    <tr>
        <th>编号</th>
        <th>图书名称</th>
        <th>图书价格</th>
        <th>出版日期</th>
        <th>操作</th>
    </tr>
    </thead>
<tbody>
<#list bookList as bookList>
<tr>
<td>${bookList.id}</td>
<td>${bookList.bookName}</td>
<td>${bookList.price}</td>
<td>${bookList.publishDate}</td>
<td>
    <a href="/book/preUpdate/${bookList.id}">修改</a>
    <a href="/book/delete?id=${bookList.id}">删除</a>
</td>
</tr>
</#list>

</tbody>
</table>
</body>
</html>