package servlet;

import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.MD5Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminServlet",value = "/admin")
public class AdminServlet extends BaseServlet {
    private static UserService userService = new UserServiceImpl();
    public static String adminLogin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String s = MD5Utils.md5(password);
        User admin = userService.login(username, s);
        if(admin!=null&&admin.getRole()==0){
            request.getSession().setAttribute("admin",admin);

            return "redirect:/admin/admin.jsp";
        }else {
            return "redirect:/admin/login.jsp";
        }
    }
}
