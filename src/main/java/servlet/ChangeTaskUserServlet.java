package servlet;

import manager.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeTaskUser")
public class ChangeTaskUserServlet extends HttpServlet {

    private TaskManager taskManager=new TaskManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        int userId = Integer.parseInt(req.getParameter("userId"));

        taskManager.changeTaskUser(taskId,userId);
        resp.sendRedirect("/managerHome");
    }
}
