package itheima.com.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import itheima.com.zhbj74.base.BasePager;

/**
 * 首页
 * Created by Administrator on 2016/8/15.
 */

public class SettingsPager extends BasePager {

    public SettingsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        //要给帧布局填充的对象
        TextView textView = new TextView(mActivity);
        textView.setText("设置");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(22);
        flContent.addView(textView);

        tvTitle.setText("设置");
        //隐藏侧滑菜单按钮
        imgBtn.setVisibility(View.GONE);
    }
}
