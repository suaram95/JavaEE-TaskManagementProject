package servlet;

import manager.TaskManager;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String status = req.getParameter("status");
            int taskId = Integer.parseInt(req.getParameter("taskId"));

            taskManager.changeTaskStatus(taskId, status);
            resp.sendRedirect("/userHome");
    }
}
