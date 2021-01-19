package servlet;

import manager.TaskManager;
import model.Task;
import model.TaskStatus;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeTaskStatus")
public class ChangeTaskStatusServlet extends HttpServlet {

    private TaskManager taskManager=new TaskManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser!=null&&currentUser.getUserType()== UserType.USER){
            String taskId = req.getParameter("id");
            String taskStatus = req.getParameter("taskStatus");

            taskManager.changeTaskStatus(taskId, TaskStatus.valueOf(taskStatus));
            Task taskById = taskManager.getTaskById(Integer.parseInt(taskId));
            taskById.setStatus(TaskStatus.valueOf(taskStatus));
            resp.sendRedirect("/userHome");
        } else {
            resp.sendRedirect("/login.jsp");        }

    }
}
