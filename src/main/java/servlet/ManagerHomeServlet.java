package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/managerHome")
public class ManagerHomeServlet extends HttpServlet {

    private UserManager userManager=new UserManager();
    private TaskManager taskManager=new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser!=null&&currentUser.getUserType()== UserType.ADMIN){
            req.setAttribute("allUsers", userManager.getAllUsers());
            req.setAttribute("allTasks", taskManager.getAllTasks());
            req.getRequestDispatcher("/managerHome.jsp").forward(req, resp);
        }else {
            resp.sendRedirect("/login");
        }
    }
}
