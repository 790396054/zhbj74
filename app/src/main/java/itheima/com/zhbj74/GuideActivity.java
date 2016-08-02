package itheima.com.zhbj74;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import itheima.com.zhbj74.utils.LogUtils;
import itheima.com.zhbj74.utils.PreUtils;

/**
 * 引导页面
 */
public class GuideActivity extends Activity {

    private ViewPager mViewPager;
    private Context mContext;
    private List<ImageView> mListImageViews;
    private Button mBtn;
    private LinearLayout llContainer;
    private ImageView mRedPoint;//小红点
    private int mPoindDis;

    private int[] guideImages = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        mContext = this;

        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        mBtn = (Button) findViewById(R.id.btn_start);
        mBtn.setVisibility(View.INVISIBLE);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        mRedPoint = (ImageView) findViewById(R.id.red_point);

        initData();//初始化数据

        mViewPager.setAdapter(new GuideAdapter());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //页面滑动过程中的回调
                //更新小红点
                int leftMargin = (int) (mPoindDis * positionOffset) + position * mPoindDis;//左边距乘以偏移量=移动的距离
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRedPoint.getLayoutParams();
                params.leftMargin = leftMargin;
                mRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //某个页面被选中
                if (position == mListImageViews.size() - 1) {
                    mBtn.setVisibility(View.VISIBLE);
                } else {
                    mBtn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面状态发生改变时的回调
            }
        });

        // 计算两个远点的距离
        // 移动距离 = 第二个圆点 left - 第一个圆点 left
        // measure -> layout -> draw(activity 的 onCreate 方法执行结束后才走此流程)
        mRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                mRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //layout 方法结束时回调
                mPoindDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
                LogUtils.i("GuideActivity","相距的距离"+mPoindDis);
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新 sp
                PreUtils.setBoolean(mContext,"is_first_enter",false);
                startActivity(new Intent(mContext,MainActivity.class));
                finish();
            }
        });
    }

    private void initData() {
        mListImageViews = new ArrayList<>();
        for (int i = 0; i < guideImages.length; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(guideImages[i]);
            mListImageViews.add(imageView);

            //初始化小圆点
            ImageView oval = new ImageView(mContext);
            oval.setImageResource(R.drawable.shape__oval_gray);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                //设置左边距
                layoutParams.leftMargin = 10;
            }
            oval.setLayoutParams(layoutParams);
            llContainer.addView(oval);
        }
    }

    class GuideAdapter extends PagerAdapter {

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return mListImageViews.size();
        }

        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
         * required for a PagerAdapter to function properly.
         *
         * @param view   Page View to check for association with <code>object</code>
         * @param object Object to check for association with <code>view</code>
         * @return true if <code>view</code> is associated with the key object <code>object</code>
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化布局资源
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mListImageViews.get(position);
            container.addView(view);
            return view;
        }

        //销毁时调用
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
