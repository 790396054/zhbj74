package itheima.com.zhbj74.utils;

import android.content.Context;

/**
 * 网络缓存工具类
 * Created by Administrator on 2016/8/15.
 */

public class CacheUtils {

    /**
     * 获取缓存
     * @param ctx
     * @param url
     */
    public static String getCache(Context ctx,String url){
        return PreUtils.getString(ctx,url,null);
    }

    /**
     * 设置缓存
     * @param ctx
     * @param url
     * @param value
     */
    public static void setCache(Context ctx,String url,String value){
        PreUtils.setString(ctx,url,value);
    }
}
