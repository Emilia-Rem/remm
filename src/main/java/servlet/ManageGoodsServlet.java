package servlet;

import domain.GoodsType;
import domain.GoodsWrapper;
import service.GoodsService;
import service.GoodsTypeService;
import service.impl.GoodsServiceImpl;
import service.impl.GoodsTypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "ManageGoodsServlet",value = "/manageGoods")
public class ManageGoodsServlet extends BaseServlet {
    private static GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
    private static GoodsService goodsService = new GoodsServiceImpl();
    //获取商品分类
    public static String getGoodsType(HttpServletRequest request, HttpServletResponse response){
        String flag = request.getParameter("flag");
        try {
            List<GoodsType> goodsTypes = goodsTypeService.AllTypes();
            request.setAttribute("goodsTypeList",goodsTypes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(flag.equals("show")){
            return "/admin/showGoodsType.jsp";
        }else {
            return "/admin/addGoodsType.jsp";
        }
    }

    public static String addGoodsType(HttpServletRequest request, HttpServletResponse response){
        String typeId = request.getParameter("goodsParent");
        String typename = request.getParameter("typename");
        try {
            GoodsType type = goodsTypeService.getTypesById(Integer.valueOf(typeId));
            goodsTypeService.addGoodsType(typename,type);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "/admin/addGoodsType.jsp";
    }

    public static String getGoodsList(HttpServletRequest request, HttpServletResponse response){
        try {
            List<GoodsWrapper> goodsList = goodsService.getAll();
            request.setAttribute("goodsList",goodsList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/admin/showGoods.jsp";
    }

}
