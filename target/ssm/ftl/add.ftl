<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FreeMarker Web</title>
</head>
<body>
<form action="/insertEmp.do" method="post">
    姓名：<input type="text" name="empname"><br>
    性别：<select name="sex">
        <option value="2">男</option>
        <option value="1">女</option>
    </select><br>
    部门：<select name="did"><br>
        <#list d as d>
            <option value="${d.did}">${d.dname}</option>
        </#list>
    </select><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
