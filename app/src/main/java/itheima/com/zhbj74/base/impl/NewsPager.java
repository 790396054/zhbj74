package itheima.com.zhbj74.base.impl;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import itheima.com.zhbj74.MainActivity;
import itheima.com.zhbj74.base.BaseMenuDetailPager;
import itheima.com.zhbj74.base.BasePager;
import itheima.com.zhbj74.base.impl.menu.InteractMenuDetailPager;
import itheima.com.zhbj74.base.impl.menu.NewsMenuDetailPager;
import itheima.com.zhbj74.base.impl.menu.PhotosMenuDetailPager;
import itheima.com.zhbj74.base.impl.menu.TopicMenuDetailPager;
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

    private ArrayList<BaseMenuDetailPager> mBaseMenuPagers;//新闻详情页

    private NewsMenu newsMenuData;//新闻数据

    @Override
    public void initData() {
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
        newsMenuData = gson.fromJson(json, NewsMenu.class);
        LogUtils.i(TAG,"解析数据。。。"+newsMenuData.toString());
        //设置侧边栏数据
        setLeftMenuData(newsMenuData.data);

        mBaseMenuPagers = new ArrayList<>();
        mBaseMenuPagers.add(new NewsMenuDetailPager(mActivity));
        mBaseMenuPagers.add(new TopicMenuDetailPager(mActivity));
        mBaseMenuPagers.add(new PhotosMenuDetailPager(mActivity));
        mBaseMenuPagers.add(new InteractMenuDetailPager(mActivity));

        // 将新闻菜单详情页设置为默认页面
        setCurrentDetailPager(0);
    }

    //设置侧边栏数据
    private void setLeftMenuData(ArrayList<NewsMenu.NewsMenuData> menuData){
        MainActivity mainActivity = (MainActivity) mActivity;
        LeftMenuFragment fragment = mainActivity.getLeftMenuFragment();
        fragment.setMenuData(menuData);
    }

    //设置菜单详情页
    public void setCurrentDetailPager(int position){
        BaseMenuDetailPager menuDetailPager = mBaseMenuPagers.get(position);

        View view = menuDetailPager.mRootView;
        tvTitle.setText(newsMenuData.data.get(position).title);
        flContent.removeAllViews();
        flContent.addView(view);

        menuDetailPager.initData();
    }

}