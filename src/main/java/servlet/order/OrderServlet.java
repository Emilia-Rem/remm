package servlet.order;

import domain.Address;
import domain.Cart;
import domain.Order;
import domain.User;
import service.AddressService;
import service.CartService;
import service.OrderDetailService;
import service.OrderService;
import service.impl.AddressServiceImpl;
import service.impl.CartServiceImpl;
import service.impl.OrderDetailServiceImpl;
import service.impl.OrderServiceImpl;
import servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "OrderServlet",value = "/order")
public class OrderServlet extends BaseServlet {
    private static AddressService addressService = new AddressServiceImpl();
    private static OrderService orderService = new OrderServiceImpl();
    private static CartService cartService = new CartServiceImpl();
    private static OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    public static String getOrderView(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user");
        try {
            List<Address> addresses = addressService.searchByUid(user.getId());
            request.getSession().setAttribute("addList",addresses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/order.jsp";
    }

    public static String addOrder(HttpServletRequest request, HttpServletResponse response){
        String aid = request.getParameter("aid");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
        Integer money = 0;
        for (Cart cart1 : cart) {
            money = cart1.getMoney()+money;
        }
        try {
            Order order = orderService.addOrderByAid(Integer.valueOf(aid), user.getId(), money);
            orderDetailService.addOrderDetail(order.getId(),user.getId());
            cartService.clearCart(user);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        return "redirect:/order?method=getOrderList";
    }

    public static String getOrderList(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        try {
            List<Order> orderList = orderService.getOrderAllByUid(Integer.valueOf(user.getId()));
            request.getSession().setAttribute("orderList",orderList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/orderList.jsp";
    }

    public static String getOrder(HttpServletRequest request, HttpServletResponse response){
        String oid = request.getParameter("oid");
        try {
            Order order = orderService.searchOrderById(oid);
            request.setAttribute("order",order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/orderDetail?method=addOrderDetail";
    }

}
