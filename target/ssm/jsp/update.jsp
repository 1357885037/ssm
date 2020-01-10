<%--
  Created by IntelliJ IDEA.
  User: 心意
  Date: 2019/11/29
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/updateEmp.do?empid=${up.empid}" method="post">
    <input type="hidden" value="${up.empid}" name="empid">
    姓名：<input type="text" name="empname" value="${up.empname}"><br>
    性别：<select name="sex" value="${up.sex}">
    <option value="2">男</option>
    <option value="1">女</option>
</select><br>
    部门：<select name="did" value="${up.did}">
    <c:forEach items="${d}" var="d">
        <option value="${d.did}" ${d.did eq up.did ? selected : ''}>${d.dname}</option>
    </c:forEach>
</select><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
