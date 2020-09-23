package utils;

import java.util.Base64;

public class Base64Utils {
    public static String base64Encode(String info){
        String encode = Base64.getEncoder().encodeToString(info.getBytes());
        return encode;
    }
    public static String base64Decode(String info){
        byte[] decode = Base64.getDecoder().decode(info);
        String decode1 = new String(decode);
        return decode1;
    }

    public static void main(String[] args) {
        String s = Base64Utils.base64Decode("bnVsbA==");
        System.out.println(s);
    }
}
