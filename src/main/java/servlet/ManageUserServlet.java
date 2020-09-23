package servlet;

import com.alibaba.fastjson.JSON;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ManageUserServlet",value = "/manageUser")
public class ManageUserServlet extends BaseServlet {
    //查询有效会员
    private static UserService userService  = new UserServiceImpl();
    public static String getUserList(HttpServletRequest request, HttpServletResponse response){
        String s = null;
        try {
            List<User> users = userService.searchAll(1);
            s = JSON.toJSONString(users);
            response.getWriter().write(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //无效会员列表
    public static String getInUserList(HttpServletRequest request, HttpServletResponse response){
        String s = null;
        try {
            List<User> users = userService.searchAll(2);
            s = JSON.toJSONString(users);
            response.getWriter().write(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    //删除普通用户
    public static String deleteUser(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        try {
            userService.removeUserById(Integer.valueOf(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    //查找符合条件的有效用户
    public static String searchUser(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        try {
            List<User> userList = userService.searchNameAndGender(username, gender,1);
            if(userList==null){
                response.getWriter().write(0);
            }else {
                String s = JSON.toJSONString(userList);
                response.getWriter().write(s);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //查找符合条件的无效用户
    public static String searchInUser(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        try {
            List<User> userList = userService.searchNameAndGender(username, gender,2);
            if(userList==null){
                response.getWriter().write(0);
            }else {
                String s = JSON.toJSONString(userList);
                response.getWriter().write(s);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
