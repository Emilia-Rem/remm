package servlet.order;

import domain.Order;
import domain.OrderWrapper;
import domain.User;
import service.OrderDetailService;
import service.impl.OrderDetailServiceImpl;
import servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "OrderDetailServlet",value = "/orderDetail")
public class OrderDetailServlet extends BaseServlet {
    private static OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    public static String addOrderDetail(HttpServletRequest request, HttpServletResponse response){
        Order order = (Order) request.getAttribute("order");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {

            List<OrderWrapper> orderWrapper = orderDetailService.getOrderWrapper(order.getId());
            request.setAttribute("order",order);
            request.setAttribute("orderWrapper",orderWrapper);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/orderDetail.jsp";
    }

}
