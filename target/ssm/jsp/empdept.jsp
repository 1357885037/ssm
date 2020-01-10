<%--
  Created by IntelliJ IDEA.
  User: 心意
  Date: 2019/11/29
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://huayu.net/huayu04/mytld" prefix="mydate" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/addEmp.do">添加员工</a><br>
<form action="${pageContext.request.contextPath}/selectAllEmp.do">
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
    <c:forEach items="${list}" var="li" varStatus="stat">
        <tr>
            <td><input type="checkbox"></td>
            <td>${stat.index+1}</td>
            <td>${li.empname}</td>
            <td>${li.sex eq '1'?'女':'男'}</td>
            <td>${li.deptBean.dname}</td>
            <td><a href="${pageContext.request.contextPath}/updateEmpOne.do?empid=${li.empid}">修改</a>|
                <a href="${pageContext.request.contextPath}/deleteEmp.do?empid=${li.empid}">删除</a></td>
        </tr>
    </c:forEach>
</table>

<mydate:now url="${pageContext.request.contextPath}/selectAllEmp.do" param="dangqian" dangqian="${pageInfo.pageNum}" zhongyeshu="${pageInfo.pages}" empname="empname" empname_value="${empBean.empname}" sex="sex" sex_value="${empBean.sex}" did="did" did_value="${empBean.did}"/>
</body>
</html>
