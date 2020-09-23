package servlet;

import utils.StringUntils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        try {
            Method method1 = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            String url = (String)method1.invoke(this, request, response);
            if(!StringUntils.isEmpty(url)){
                if(url.startsWith("redirect:")){
                    response.sendRedirect(request.getContextPath()+url.split(":")[1]);
                }else{
                    request.getRequestDispatcher(url).forward(request,response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
