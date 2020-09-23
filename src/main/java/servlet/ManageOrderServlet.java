package servlet;

import domain.Order;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "MangeOrderServlet",value = "/manageOrder")
public class ManageOrderServlet extends BaseServlet {
    private static OrderService orderService = new OrderServiceImpl();
    public static String getAllOrder(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Order> orderList = orderService.getOrderAll();
            request.setAttribute("orderList",orderList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/admin/showAllOrder.jsp";
    }

    public static String searchOrder(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        Integer status = Integer.valueOf(request.getParameter("status"));
        try {
            List<Order> orderList = orderService.searchOrderByUNameAndStatus(username,status);
            request.setAttribute("orderList",orderList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/admin/showAllOrder.jsp";
    }
}
