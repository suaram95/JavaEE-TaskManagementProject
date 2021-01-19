<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page import="util.DateUtil" %>
<%@ page import="model.TaskStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<body>
<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");
    List<Task> tasksByUser = (List<Task>) request.getAttribute("tasksByUser");
%>
<b style="color: forestgreen">Welcome! <%=currentUser.getName()%> <%=currentUser.getSurname()%></b><br>
<a href="/logout">Logout</a>

<br><br>
<div>
    <b style="color: royalblue">My Tasks</b><br>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Description</td>
            <td>Deadline</td>
            <td>Status</td>
            <td>Action</td>
        </tr>
        <%for (Task task : tasksByUser) {%>
        <tr>
              <td><%=task.getId()%></td>
              <td><%=task.getName()%></td>
              <td><%=task.getDescription()%></td>
              <td><%=DateUtil.getStringFromDate(task.getDeadline())%></td>
              <td><%=task.getStatus()%></td>
              <td><select name="taskStatus">
                  <option value="NEW"><%=TaskStatus.NEW%>
                  </option>
                  <option value="DOING"><%=TaskStatus.DOING%>
                  </option>
                  <option value="FINISHED"><%=TaskStatus.FINISHED%>
                  </option>
              </select>
                  <a href="/changeTaskStatus?id=<%=task.getId()%>">Change</a>
              </td>


        </tr>
        <%}%>


    </table>
</div>


</body>
</html>
