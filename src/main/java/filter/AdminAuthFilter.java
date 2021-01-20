package filter;

import model.User;
import model.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/managerHome", "/addTask", "/changeTaskUser","/deleteUser", "/register"})
public class AdminAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser != null && currentUser.getUserType() == UserType.ADMIN) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");
        }
    }

}
