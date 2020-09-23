package servlet;

import domain.Goods;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import service.GoodsService;
import service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@MultipartConfig(maxFileSize = 1024*1024*10,maxRequestSize = 1024*1024*30)
@WebServlet(name = "AddGoodsServlet",value = "/addGoods")
public class AddGoodsServlet extends HttpServlet {
    private static GoodsService goodsService = new GoodsServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Goods goods = new Goods();
        String dir = "/upload" + File.separator;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        //时间转化器
        try {
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.populate(goods, request.getParameterMap());
            Part picture = request.getPart("picture");
            if (!picture.getSubmittedFileName().equals("")) {
                String header = picture.getHeader("content-disposition");
                String filename = header.substring(header.lastIndexOf("filename=") + 10, header.length() - 1);
                //兼容ie
                filename = filename.substring(filename.lastIndexOf("\\") + 1);
                picture.write(dir + File.separator + filename);
                goods.setPicture(dir+filename);
                picture.delete();
            }
            goodsService.addGoods(goods);
            response.sendRedirect(request.getContextPath()+"/admin/addGoods.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
