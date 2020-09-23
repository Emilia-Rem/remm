package utils.weixin;

/**
 * @Auther: Administrator
 * Thanks for Everything
 * 用于配置上述需要的信息
 */
public class PayConfigUtil {
    public static String APP_ID = "wx632c8f211f8122c6";
    public static String MCH_ID = "1497984412";
    public static String API_KEY = "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
    public static String UFDOOER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    //http://m8r676.natappfree.cc  是natapp 做内网穿透后分配的地址
    public static String NOTIFY_URL = "http://remm.free.idcfengye.com/payment/result";//回调地址，能通过互联网访问
    public static String CREATE_IP = "106.38.62.154";//测试可通过baidu,输入ip查询

}
