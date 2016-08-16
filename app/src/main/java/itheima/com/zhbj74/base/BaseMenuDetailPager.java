package itheima.com.zhbj74.base;

import android.app.Activity;
import android.view.View;

/**
 * 菜单详情页基类
 * Created by Administrator on 2016/8/16.
 */

public abstract class BaseMenuDetailPager {

    public Activity mActivity;

    public View mRootView;

    public BaseMenuDetailPager(Activity activity){
        mActivity = activity;
        mRootView = initView();
    }

    public abstract View initView();

    public void initData(){

    }
}
