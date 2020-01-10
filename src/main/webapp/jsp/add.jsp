<%--
  Created by IntelliJ IDEA.
  User: 心意
  Date: 2019/11/29
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/insertEmp.do" method="post">
    姓名：<input type="text" name="empname"><br>
    性别：<select name="sex">
    <option value="2">男</option>
    <option value="1">女</option>
</select><br>
    部门：<select name="did"><br>
    <c:forEach items="${d}" var="d">
        <option value="${d.did}">${d.dname}</option>
    </c:forEach>
</select><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
