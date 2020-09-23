package servlet.goods;

import com.alibaba.fastjson.JSON;
import domain.GoodsType;
import service.GoodsTypeService;
import service.impl.GoodsTypeServiceImpl;
import servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GoodsTypeServlet",value = "/goodsType")
public class GoodsTypeServlet extends BaseServlet {
        private static GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

        public static String goodsTypeList(HttpServletRequest request, HttpServletResponse response){
                try {
                        response.setContentType("text/html;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        List<GoodsType> goodsTypes = goodsTypeService.AllTypes();
                        if(goodsTypes!=null){
                                String g = JSON.toJSONString(goodsTypes);
                                writer.write(g);
                                writer.close();
                        }else {
                                new RuntimeException("查询结果为空！");
                        }

                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
                return null;
        }

        public static String getGoodType(HttpServletRequest request, HttpServletResponse response){
                String typeid = request.getParameter("typeid");
                try {
                        return "/goods" +typeid;
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
                return null;
        }

}
