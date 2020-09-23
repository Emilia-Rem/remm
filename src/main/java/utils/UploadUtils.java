package utils;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class UploadUtils {

    /**
     * 创建新的文件名
     * @param filename
     * @return
     */
    public static String makeNewFileName(String filename){
        //UUID统一标识码
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid+"_"+filename;
    }

    /**
     *创建新的路径
     * @param realPath
     * @param filename
     * @return
     */
    public static String makeNewPath(String realPath,String filename){
        int num = filename.hashCode();
        int path1 = num&0xf;
        int path2 = (num>>4)&0xf;
        String newPath = realPath + File.separator + path1 + File.separator +path2;
        File dir = new File(newPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return newPath;
    }

    public static void listFile(File dir, HashMap<String,String> map){
        File[] files = dir.listFiles();
        if(files!=null&&files.length>0){
            for (File file : files) {
                if(file.isDirectory()){
                    listFile(file, map);
                }else{
                    String uuidFilename = file.getName();
                    String filename = uuidFilename.substring(uuidFilename.indexOf("_") + 1);
                    map.put(uuidFilename, filename);
                }
            }
        }
    }
}
