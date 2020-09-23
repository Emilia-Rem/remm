package servlet.goods;

import domain.Goods;
import service.GoodsService;
import service.impl.GoodsServiceImpl;
import utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet",value = "/search")
public class SearchServlet extends HttpServlet {
    private static GoodsService goodsService = new GoodsServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String likeName = request.getParameter("likeName");
        try {
            List<Goods> goodsList = goodsService.searchByLike(likeName);
            PageBean pageBean = new PageBean(1, 4, goodsList.size(),goodsList);
            request.getSession().setAttribute("pageBean",pageBean );
            request.getRequestDispatcher("/searchGoods.jsp").forward(request,response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
