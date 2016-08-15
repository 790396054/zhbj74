package itheima.com.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import itheima.com.zhbj74.base.BasePager;

/**
 * 首页
 * Created by Administrator on 2016/8/15.
 */

public class NewsPager extends BasePager {

    public NewsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        //要给帧布局填充的对象
        TextView textView = new TextView(mActivity);
        textView.setText("新闻");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);

        flContent.addView(textView);

        tvTitle.setText("新闻");
    }
}
