package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.TaskStatus;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {

    private UserManager userManager=new UserManager();
    //taskManager
    private TaskManager taskManager=new TaskManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String userId = req.getParameter("userId");
        String taskStatus = req.getParameter("taskStatus");
        String deadline = req.getParameter("deadline");

        StringBuilder addTaskMessage=new StringBuilder();
        if (name==null||name.equals("")){
            addTaskMessage.append("Name field is required <br>");
        }
        if (description==null||description.equals("")){
            addTaskMessage.append("Description field is required <br>");
        }
        if (deadline==null||deadline.equals("")){
            addTaskMessage.append("Deadline field is required <br>");
        }

        if (addTaskMessage.toString().equals("")){
            Task task= Task.builder()
                    .name(name)
                    .description(description)
                    .status(TaskStatus.valueOf(taskStatus))
                    .deadline(DateUtil.getDateFromString(deadline))
                    .user(userManager.getUserById(Integer.parseInt(userId)))
                    .build();
            taskManager.addTask(task);
            addTaskMessage.append("Task was successfully added!");
        }
        req.getSession().setAttribute("addTaskMessage", addTaskMessage.toString());
        resp.sendRedirect("/managerHome");


    }
}
