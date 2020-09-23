package filter;

import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Base64Utils;
import utils.MD5Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter",value = "/index.jsp")
public class AutoLoginFilter implements Filter {
    UserService userService = new UserServiceImpl();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        request.setCharacterEncoding("utf-8");
        //1.先判断是否已经登录
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            //转给首页
            chain.doFilter(req, resp);
            return;
        }
        //2.获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userInfo")){
                    String value = cookie.getValue();
                    String[] split = Base64Utils.base64Decode(value).split("#");
                    User login = userService.login(split[0], MD5Utils.md5(split[1]));
                    if(login!=null){
                        request.getSession().setAttribute("user", login);
                    }else {
                        //账号密码不正确，移除cookie
                        Cookie cookie1 = new Cookie("userInfo", "");
                        cookie1.setPath(request.getContextPath());
                        cookie1.setMaxAge(0);
                        response.addCookie(cookie1);
                    }
                }
                chain.doFilter(req, resp);
            }

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
