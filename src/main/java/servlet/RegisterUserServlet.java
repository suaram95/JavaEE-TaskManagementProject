package servlet;

import exception.DuplicateModelException;
import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterUserServlet extends HttpServlet {

    private UserManager userManager=new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        StringBuilder userRegisterMsg=new StringBuilder();
        if (name==null||name.equals("")){
            userRegisterMsg.append("Name field is required! <br>");
        }
        if (surname==null||surname.equals("")){
            userRegisterMsg.append("Surname field is required! <br>");
        }
        if (email==null||email.equals("")){
            userRegisterMsg.append("Email field is required! <br>");
        }
        if (password == null || password.equals("")) {
            userRegisterMsg.append("Password field is required! <br>");
        }

        if (userRegisterMsg.toString().equals("")){
            User user=User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .userType(UserType.USER)
                    .build();
            try {
                userManager.addUser(user);
                userRegisterMsg.append("User successfully added!");
            } catch (DuplicateModelException e) {
                userRegisterMsg.append("User with email"+user.getEmail()+" already exists");
            }
        }
        req.getSession().setAttribute("userRegisterMsg", userRegisterMsg.toString());
        resp.sendRedirect("/managerHome");

    }
}
