<%@ page import="de.xiekang.web_tutorial.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: xiekangzhang
  Date: 07.02.23
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<b>Welcome to our Web Application</b>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<table>
    <tr>
        <th>Email</th>
        <th>Password</th>
    </tr>
    <% for (User user: users) {%>
    <tr>
        <td><%=user.getEmail()%></td>
        <td><%=user.getPassword()%></td>
    </tr>
    <%}%>
</table>

<%@ include file="footer.jsp"%>
