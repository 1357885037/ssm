<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FreeMarker Web</title>
</head>
<body>
<a href="/addEmp.do">添加员工</a><br>
<form action="/selectAllEmp.do">
    姓名：<input type="text" name="empname" style="width: 60px">
    性别：<input type="text" name="sex" style="width: 60px">
    部门：<input type="text" name="did" style="width: 60px">
    </select>
    <input type="submit" value="提交">
</form>
<table border="1">
    <tr>
        <td><input type="checkbox"></td>
        <td>编号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>部门</td>
        <td>操作</td>
    </tr>
    <#list list as li>
        <tr>
            <td><input type="checkbox"></td>
            <td>${li_index+1}</td>
            <td>${li.empname}</td>
            <td>${(li.sex == 1)?string('女','男')}</td>
            <td>${li.deptBean.dname}</td>
            <td><a href="updateEmpOne.do?empid=${li.empid}">修改</a>|
                <a href="deleteEmp.do?empid=${li.empid}">删除</a></td>
        </tr>
    </#list>
</table>
</body>
</html>
