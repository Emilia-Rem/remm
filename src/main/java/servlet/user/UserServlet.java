package servlet.user;

import cn.dsna.util.images.ValidateCode;
import domain.Address;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.AddressService;
import service.UserService;
import service.impl.AddressServiceImpl;
import service.impl.UserServiceImpl;
import servlet.BaseServlet;
import utils.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserServlet",value = "/user")
public class UserServlet extends BaseServlet {
    private static UserService userService = new UserServiceImpl();
    private static AddressService addressService = new AddressServiceImpl();

    public static String userLogin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String s = MD5Utils.md5(password);
        String auto = request.getParameter("auto");
        User user = userService.login(username, s);
        if(user.getFlag()==2){
            request.setAttribute("msg","无效的会员！无法登陆！");
            return "/login.jsp";
        }
        if(user.getRole()==0){
            request.setAttribute("msg", "该用户权限不正确，无法登陆！");
            return "/login.jsp";
        }
        if(!StringUntils.isEmpty(user.getCode())){
            request.setAttribute("msg","该账号未激活，请激活重试！");
            return "/login.jsp";
        }
        if(user!=null){
            request.getSession().setAttribute("user",user );
            if(auto!=null){
                //加密账号密码
                String userInfo=username+"#"+password;
                String base64Pwd = Base64Utils.base64Encode(userInfo);
                //为自动登录的用户创建一个两周的cookie
                Cookie cookie = new Cookie("userInfo", base64Pwd);
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(60*60*24*14);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
            return "redirect:/index.jsp";
        }else {
            return "redirect:/login.jsp";
        }
    }


    public static String register(HttpServletRequest request, HttpServletResponse response){

        try {
            request.setCharacterEncoding("utf-8");
            User user = new User();


            String password = request.getParameter("password");
            String s = MD5Utils.md5(password);
            BeanUtils.populate(user, request.getParameterMap());
            user.setPassword(s);
            user.setCode(ActiveCodeUtils.getActiveCode());
            user.setFlag(0);
            user.setRole(1);
            boolean b = userService.addUser(user);
            if(b){
                request.getSession().setAttribute("registerUser", user);
                return "redirect:/registerSuccess.jsp";
            }else{
                request.setAttribute("registerMsg", "注册失败！");
                return "redirect:/register.jsp";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String code(HttpServletRequest request, HttpServletResponse response){
        ValidateCode vc = new ValidateCode(100,40,4,5);
        String code = vc.getCode();
        request.getSession().setAttribute("code",code);
        try {
            vc.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String checkCode(HttpServletRequest request, HttpServletResponse response){
        String code = (String)request.getSession().getAttribute("code");
        String code1 = request.getParameter("code");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(code.equals(code1)){
            writer.write("0");
        }
        writer.close();
        return null;
    }

    public static String checkUser(HttpServletRequest request, HttpServletResponse response){
        boolean b = false;
        PrintWriter writer = null;
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            String username = request.getParameter("username");

            b = userService.searchName(username);
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(b){
            writer.write("1");

        }else{
            writer.write("0");
        }
        writer.close();
        return null;
    }

    //发送激活邮件
    public static String activation(HttpServletRequest request, HttpServletResponse response){
        User registerUser = (User) request.getSession().getAttribute("registerUser");
        if(registerUser!=null){
            EmailUtils.sendEmail(registerUser);
            request.getSession().removeAttribute("registerUser");
        }
        return null;
    }

    //接收激活邮件
    public static String activate(HttpServletRequest request, HttpServletResponse response){
        String e = request.getParameter("e");
        String c = request.getParameter("c");
        String u = request.getParameter("u");
        String userEmail = Base64Utils.base64Decode(e);
        String userCode = Base64Utils.base64Decode(c);
        String userName = Base64Utils.base64Decode(u);
        User registerUser = userService.searchByName(userName);
        if(userEmail.equals(registerUser.getEmail())&&userCode.equals(registerUser.getCode())){
            try {
                userService.clearUserCode(registerUser);
                userService.changeFlag(1);
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
        return "redirect:/login.jsp";
    }

    //注销用户
    public static String logOut(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userInfo")){
                    Cookie cookie1 = new Cookie("userInfo", "");
                    cookie1.setPath(request.getContextPath());
                    cookie1.setMaxAge(0);
                    response.addCookie(cookie1);
                }
            }
        }
        request.getSession().removeAttribute("user");
        return "redirect:/login.jsp";
    }
    //展示地址
    public static String showAddress(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user");
        try {
            List<Address> addresses = addressService.searchByUid(user.getId());
            request.getSession().setAttribute("addList",addresses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/self_info.jsp";
    }
    //添加收货地址
    public static String addAddress(HttpServletRequest request, HttpServletResponse response){
        try {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String detail = request.getParameter("detail");
            User user = (User) request.getSession().getAttribute("user");

            addressService.addAddress(user.getId(),name, phone,detail);
            return "redirect:/user?method=showAddress";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            return null;
    }

    public static String deleteAddress(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        try {
            addressService.deleteById(Integer.valueOf(id));
            return "redirect:/user?method=showAddress";
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String updateAddress(HttpServletRequest request, HttpServletResponse response){
        try {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String detail = request.getParameter("detail");
            String id = request.getParameter("id");
            addressService.updateById(Integer.valueOf(id),name, phone,detail);
            return "redirect:/user?method=showAddress";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String defaultAddress(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        try {
            User user = (User) request.getSession().getAttribute("user");
            List<Address> addresses = addressService.searchByUid(user.getId());

            for (Address address : addresses) {
                if(address.getLevel()==1){
                    addressService.updateLevelById(Integer.valueOf(address.getId()),0);
                }
            }
            addressService.updateLevelById(Integer.valueOf(id),1);
            return "redirect:/user?method=showAddress";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}


