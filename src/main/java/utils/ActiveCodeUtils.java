package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ActiveCodeUtils {

    public static String getActiveCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 12);;
        return format+uuid;
    }
}
