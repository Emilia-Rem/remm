package servlet.goods;

import domain.Goods;
import domain.GoodsType;
import service.GoodsService;
import service.GoodsTypeService;
import service.impl.GoodsServiceImpl;
import service.impl.GoodsTypeServiceImpl;
import servlet.BaseServlet;
import utils.PageBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "GoodsServlet",value = "/goods")
public class GoodsServlet extends BaseServlet {
    private static GoodsService goodsService = new GoodsServiceImpl();
    private static GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

    public static String getGoodsListByTypeId(HttpServletRequest request, HttpServletResponse response){
        String typeid = request.getParameter("typeId");
        try {
            List<Goods> goods = goodsService.GoodsListByTypeId(Integer.valueOf(typeid));
            List<Goods> goods1 = goodsService.GoodsPageByTypeId(Integer.valueOf(typeid),1);
            PageBean pageBean = new PageBean(1, 4, goods.size(),goods1);
            request.getSession().setAttribute("pageBean",pageBean );
            return "redirect:/goodsList.jsp";

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getGoodById(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        try {
            Goods good = goodsService.GoodById(Integer.valueOf(id));
            GoodsType type = goodsTypeService.getTypesById(good.getTypeid());
            request.getSession().setAttribute("goods", good);
            request.getSession().setAttribute("goodsType", type);
            return "redirect:/goodsDetail.jsp";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}


