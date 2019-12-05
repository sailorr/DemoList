package me.sailor.libcommon.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class FileUtils {
    //    var basePath = Environment.getExternalStorageDirectory().absolutePath + "/pqcloud/${CHelper.usr!!.account}"
    public static final String BASEPATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/demo_img";

    /**
     * 在指定的位置创建文件夹
     *
     * @param dirPath 文件夹路径
     * @return 若创建成功，则返回True；反之，则返回False
     */
    public static boolean mkDir(String dirPath) {
        return new File(dirPath).mkdirs();
    }

    public static void init(final String path){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b = mkDir(path);
                Log.d("File","path:"+path+"mk dir:"+b);
            }
        }).start();
    }


}
