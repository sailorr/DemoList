package me.sailor.demolist.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Stack;

/**
 * @description
 * @time 2018/11/14
 * @user Administrator
 */
public class ActivityManager {
    private static Stack<Activity> activityStack;

    /**
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     *
     * @param context
     * @param clazz
     */
    public static void startActivity(Context context,Class<?> clazz){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
    }


}
