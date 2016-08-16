package itheima.com.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import itheima.com.zhbj74.MainActivity;
import itheima.com.zhbj74.base.BasePager;
import itheima.com.zhbj74.domain.NewsMenu;
import itheima.com.zhbj74.fragment.LeftMenuFragment;
import itheima.com.zhbj74.utils.CacheUtils;
import itheima.com.zhbj74.utils.GlobalConstant;
import itheima.com.zhbj74.utils.LogUtils;

/**
 * 首页
 * Created by Administrator on 2016/8/15.
 */

public class NewsPager extends BasePager {

    public NewsPager(Activity activity) {
        super(activity);
    }

    private static final String TAG = "NewsPager";

    @Override
    public void initData() {
        //要给帧布局填充的对象
        TextView textView = new TextView(mActivity);
        textView.setText("新闻");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(22);
        flContent.addView(textView);

        tvTitle.setText("新闻");
        //显示侧滑按钮
        imgBtn.setVisibility(View.VISIBLE);
        String cacheJson = CacheUtils.getCache(mActivity,GlobalConstant.CATOGORY_URL);
        if (cacheJson != null){
            //有缓存，直接解析 json
            LogUtils.i(TAG,"有缓存。。。");
            processData(cacheJson);
        }
        //到服务端更新数据
        getJsonFromServer();
    }

    /**
     * 获取服务器数据
     */
    private void getJsonFromServer(){
        RequestParams params = new RequestParams(GlobalConstant.CATOGORY_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.i(TAG,"获取数据成功。。。"+result);
                processData(result);
                //设置缓存
                CacheUtils.setCache(mActivity,GlobalConstant.CATOGORY_URL,result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.i(TAG,"访问服务器错误。。。"+ex.toString());
                Toast.makeText(mActivity, "访问网络错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void processData(String json){
        Gson gson = new Gson();
        NewsMenu newsMenu = gson.fromJson(json, NewsMenu.class);
        LogUtils.i(TAG,"解析数据。。。"+newsMenu.toString());
        //设置侧边栏数据
        setLeftMennuData(newsMenu.data);

    }

    //设置侧边栏数据
    private void setLeftMennuData(ArrayList<NewsMenu.NewsMenuData> mennuData){
        MainActivity mainActivity = (MainActivity) mActivity;
        LeftMenuFragment fragment = mainActivity.getLeftMenuFragment();
        fragment.setMenuData(mennuData);
    }
}