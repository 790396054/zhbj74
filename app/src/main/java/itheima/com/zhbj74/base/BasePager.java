package itheima.com.zhbj74.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import itheima.com.zhbj74.R;

/**
 * 五个标签页的基类
 * Created by Administrator on 2016/8/15.
 */

public class BasePager {
    public Activity mActivity;

    public TextView tvTitle;
    public ImageButton imgBtn;
    public FrameLayout flContent;//空的帧布局对象，要动态添加布局
    public View mRootView;//

    public BasePager(Activity activity){
        this.mActivity = activity;
        mRootView = initView();
    }

    //初始化布局
    public View initView(){
        View view = View.inflate(mActivity, R.layout.basepager,null);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        imgBtn = (ImageButton) view.findViewById(R.id.img_btn);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);
        return view;
    }

    //初始化数据
    public void initData(){

    }
}
