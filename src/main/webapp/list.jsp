<%@ page import="ru.job4j.models.User" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="ru.job4j.models.ValidateService" %><%--
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
<% final ValidateService logic = ValidateService.getValidateService();
    Iterator<User> iterator = logic.findAll().iterator();%>
<table>
    <%
        while (iterator.hasNext()) {
            User user = iterator.next();
    %>
    <tr>
        <td>
            <p>
                <form action="<%=request.getContextPath()%>/edit" method="get">
                    <input type="hidden" name="id" value="<%=user.getId()%>">
            <p><input type="submit" value="Редактировать" name="update"></p>
            </form>
            </p>
            <p>
                <form action="<%=request.getContextPath()%>/list" method="post">
                    <input type="hidden" name="id" value="<%=user.getId()%>">
            <p><input type="submit" value="Удалить" name="del"></p>
            </form>
            </p>
        </td>
        <td><%=user %>
        </td>
    </tr>
    <%}%>
</table>
<form action="<%=request.getContextPath()%>/create" method="get">
    <p><input type="submit" value="Создать пользователя" name="create"></p>
</form>
</body>
</html>
