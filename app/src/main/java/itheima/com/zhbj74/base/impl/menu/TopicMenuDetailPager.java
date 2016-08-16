package itheima.com.zhbj74.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import itheima.com.zhbj74.base.BaseMenuDetailPager;

/**
 * 话题-新闻详情页
 * Created by Administrator on 2016/8/16.
 */

public class TopicMenuDetailPager extends BaseMenuDetailPager {


    public TopicMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        //要给帧布局填充的对象
        TextView textView = new TextView(mActivity);
        textView.setText("专题-详情页");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(22);

        return textView;
    }

    @Override
    public void initData() {

    }
}
