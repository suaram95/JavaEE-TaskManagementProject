package servlet;

import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        StringBuilder loginErrorMsg = new StringBuilder();
        if (email == null || email.equals("")) {
            loginErrorMsg.append("Email field is required <br>");
        }
        if (password == null || password.equals("")) {
            loginErrorMsg.append("Password field is required <br>");
        }

        if (loginErrorMsg.toString().equals("")) {
            User currentUser = userManager.getUserByEmailAndPassword(email, password);
            if (currentUser!=null){
                req.getSession().setAttribute("currentUser", currentUser);
                if (currentUser.getUserType() == UserType.ADMIN) {
                    resp.sendRedirect("/managerHome");
                } else {
                    resp.sendRedirect("/userHome");
                }
            } else {
                loginErrorMsg.append("Invalid email or password! <br>");
                req.getSession().setAttribute("loginErrorMsg", loginErrorMsg.toString());
                resp.sendRedirect("/login.jsp");
            }
        } else {
            req.getSession().setAttribute("loginErrorMsg", loginErrorMsg.toString());
            resp.sendRedirect("/login.jsp");
        }
    }
}
