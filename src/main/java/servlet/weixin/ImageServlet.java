package servlet.weixin;

import servlet.BaseServlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description:  展示二维码
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2019/11/10
 * @Time: 下午2:34
 */
@WebServlet("/payment/image")
public class ImageServlet extends BaseServlet {

    public   void scanCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage bufferedImage = (BufferedImage) req.getSession().getAttribute("image");
        if(bufferedImage!=null) {
            ImageIO.write(bufferedImage, "jpeg", resp.getOutputStream());
        }

    }
}
