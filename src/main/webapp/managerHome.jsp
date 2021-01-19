<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page import="model.TaskStatus" %>
<%@ page import="util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Home</title>
</head>
<body>
<%
    String userRegisterMsg = "";
    if (request.getSession().getAttribute("userRegisterMsg") != null) {
        userRegisterMsg = (String) request.getSession().getAttribute("userRegisterMsg");
        request.getSession().removeAttribute("userRegisterMsg");
    }
    List<User> userList = (List<User>) request.getAttribute("allUsers");
    List<Task> taskList = (List<Task>) request.getAttribute("allTasks");
%>

<div>
    <p><%=userRegisterMsg%>
    </p>
    <b>Add User</b> <br>
    <form action="/register" method="post">
        Name: <input type="text" name="name"><br>
        Surname: <input type="text" name="surname"><br>
        Email: <input type="text" name="email"><br>
        Password: <input type="text" name="password"><br>
        <input type="submit" value="Register"> &nbsp; <input type="reset" value="Reset">
    </form>
</div>

<br><br>

<div>
    <b>Add Task</b> <br>
    <form action="/addTask" method="post">
        Name: <input type="text" name="name"><br>
        Description: <input type="text" name="description"><br>
        User:<select name="userId">
        <%for (User user : userList) {%>
        <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getName()%>
        </option>
        <%}%>
    </select><br>
        Status: <select name="taskStatus">
        <option value="NEW"><%=TaskStatus.NEW%>
        </option>
        <option value="DOING"><%=TaskStatus.DOING%>
        </option>
        <option value="FINISHED"><%=TaskStatus.FINISHED%>
        </option>
    </select><br>
        Deadline: <input type="date" name="deadline"><br>
        <input type="submit" value="OK">
    </form>
</div>

<br><br>

<div>
    <b>All Users</b> <br>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Email</td>
        </tr>
        <% for (User user : userList) {%>
        <tr>
            <td><%=user.getId()%>
            </td>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getSurname()%>
            </td>
            <td><%=user.getEmail()%>
            </td>
            <% }%>
        </tr>
    </table>
</div>
<br><br>
<div>
    <b>All Tasks</b> <br>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Description</td>
            <td>Deadline</td>
            <td>Status</td>
            <td>Assigned user</td>
        </tr>
        <%for (Task task : taskList) {%>
        <td><%=task.getId()%>
        </td>
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=DateUtil.getStringFromDate(task.getDeadline())%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getUser().getName()%>
        </td>
        <%}%>
    </table>
</div>
</body>
</html>