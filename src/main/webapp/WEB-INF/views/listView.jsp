<%@ page import="ru.job4j.models.User" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.models.ValidateService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 17.06.2018
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <c:forEach var="user" items="${myusers}">
        <tr>
            <td>
                <p>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="${user.getId()}">
                <p><input type="submit" value="Редактировать" name="update">
                </p>
                </form>
                </p>
                <p>
                    <form action="${pageContext.servletContext.contextPath}/" method="post">
                        <input type="hidden" name="id" value="${user.getId()}">
                <p><input type="submit" value="Удалить"  name="del">
                </p>
                </form>
                </p>
            </td>
            <td><c:out value="${user}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <p><input type="submit" value="Создать пользователя" name="create"></p>
</form>
</body>
</html>
