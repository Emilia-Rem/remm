package servlet.cart;

import domain.Cart;
import domain.Goods;
import domain.User;
import service.CartService;
import service.GoodsService;
import service.impl.CartServiceImpl;
import service.impl.GoodsServiceImpl;
import servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "CartServlet",value = "/cart")
public class CartServlet extends BaseServlet {
    private static CartService cartService = new CartServiceImpl();
    private static GoodsService goodsService = new GoodsServiceImpl();

    //添加购物车
    public static String addCart(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null){
            return "redirect:/login.jsp";
        }
        String goodsId = request.getParameter("goodsId");
        String number = request.getParameter("number");
        Goods goods = goodsService.GoodById(Integer.valueOf(goodsId));

        try {
            cartService.addCart(user, goods,Integer.valueOf(number));
            return "redirect:/cartSuccess.jsp";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            return null;
    }

    //显示购物车列表
    public static String getCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<Cart> cartList = null;
        try {
            cartList = cartService.searchByUser(user.getId());
            if(cartList!=null){
                session.setAttribute("cart",cartList);
                return "redirect:/cart.jsp";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String addCartAjax(HttpServletRequest request, HttpServletResponse response){
        String goodsId = request.getParameter("goodsId");
        String num = request.getParameter("number");
        String price = request.getParameter("price");
        Goods goods = goodsService.GoodById(Integer.valueOf(goodsId));
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null){
            return "redirect:/login.jsp";
        }
        try {
            cartService.updateCart(user, goods,Integer.valueOf(num),Integer.valueOf(price));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String clearCartAjax(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user");
        try {
            cartService.clearCart(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
