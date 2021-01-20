<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<%
    String loginErrorMsg = "";
    if (request.getSession().getAttribute("loginErrorMsg") != null) {
        loginErrorMsg= (String) request.getSession().getAttribute("loginErrorMsg");
        request.getSession().removeAttribute("loginErrorMsg");
    }
%>
<div style="border: 1px black">
    <p style="color: red"><%=loginErrorMsg%></p>
    <form action="/login" method="post">
        Email: <input type="text" name="email"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
