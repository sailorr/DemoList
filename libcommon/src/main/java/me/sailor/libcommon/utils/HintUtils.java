package me.sailor.libcommon.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author sailor
 * @description
 * @time 2018/12/19
 */
public class HintUtils {

    /**
     * 吐司
     * @param context
     * @param o
     */
    public static void showToast(Context context, Object o) {
        Toast.makeText(context, o.toString(), Toast.LENGTH_SHORT).show();
    }

    public static void d(Class aClass,Object o){
        Log.d(aClass.getSimpleName(),o.toString());
    }

}
