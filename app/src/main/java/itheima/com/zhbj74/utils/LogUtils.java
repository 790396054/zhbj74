package itheima.com.zhbj74.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/8/2.
 */

public class LogUtils {

    private static boolean isTest = true;

    public static void i(String tag,String msg){
        if (isTest){
            Log.i(tag,msg);
        }
    }

    public static void d(String tag,String msg){
        if (isTest){
            Log.d(tag,msg);
        }
    }

}
