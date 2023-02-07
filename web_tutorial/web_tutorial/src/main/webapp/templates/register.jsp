<%--
  Created by IntelliJ IDEA.
  User: xiekangzhang
  Date: 07.02.23
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<b>Welcome to our Web Application</b>
<% String message = (String) request.getAttribute("message"); %>
<script type="text/javascript">
    const msg = "<%=message%>";
    if (msg !== "null") {
        alert(msg);
    }
</script>
<br><br>

<form method="POST" action="UserRegister">
    <fieldset id="insertoffer">
        <legend>Required Information</legend>
        <div>
            <label>Email</label>
            <input type="email" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
    </fieldset>
    <button type="submit" id="submit">Register</button>
</form>
<%@include file="footer.jsp" %>
