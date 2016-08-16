package itheima.com.zhbj74.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import itheima.com.zhbj74.MainActivity;
import itheima.com.zhbj74.R;
import itheima.com.zhbj74.base.BasePager;
import itheima.com.zhbj74.base.impl.GovoPager;
import itheima.com.zhbj74.base.impl.HomePager;
import itheima.com.zhbj74.base.impl.NewsPager;
import itheima.com.zhbj74.base.impl.SettingsPager;
import itheima.com.zhbj74.base.impl.SmartServicePager;
import itheima.com.zhbj74.view.NoScrollViewPager;

/**
 * 主页面 Fragment
 * Created by Administrator on 2016/8/15.
 */

public class ContentFragment extends BaseFragment{

    private RadioGroup mRadioGroup;

    private List<BasePager> mPagers;//五个标签页的集合

    private NoScrollViewPager vpContent;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content,null);
        vpContent = (NoScrollViewPager) view.findViewById(R.id.vp_content);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        return view;
    }

    @Override
    public void initData() {
        mPagers = new ArrayList<>();
        //添加五个标签页
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NewsPager(mActivity));
        mPagers.add(new SmartServicePager(mActivity));
        mPagers.add(new GovoPager(mActivity));
        mPagers.add(new SettingsPager(mActivity));

        vpContent.setAdapter(new ContentAdapter());

        // 底部导航栏标签切换监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        vpContent.setCurrentItem(0,false);
                        break;
                    case R.id.rb_news:
                        vpContent.setCurrentItem(1,false);
                        break;
                    case R.id.rb_smart:
                        vpContent.setCurrentItem(2,false);
                        break;
                    case R.id.rb_gov:
                        vpContent.setCurrentItem(3,false);
                        break;
                    case R.id.rb_settings:
                        vpContent.setCurrentItem(4,false);
                        break;
                }
            }
        });

        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPagers.get(position).initData();
                if (position == 0 || position == mPagers.size() -1){
                    //首页和设置页侧滑栏不可用
                    setSlindingMenuEnable(false);
                }else {
                    //其他页面开启侧边栏
                    setSlindingMenuEnable(true);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 手动的初始化第一页的数据
        mPagers.get(0).initData();
        //禁用侧滑菜单
        setSlindingMenuEnable(false);
    }

    /**
     * 设置侧滑菜单是否可用
     * @param enable
     */
    private void setSlindingMenuEnable(boolean enable){
        MainActivity mainActivity = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
        if (enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    class ContentAdapter extends PagerAdapter{

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return mPagers.size();
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

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = mPagers.get(position);
            View view = basePager.mRootView;
            //basePager.initData();//初始化数据 ,viewpager每次都会加载下一个页面，这样会浪费性能和流量
            //故不再此处初始化数据
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    //获取新闻业
    public NewsPager getNewPager(){
        return (NewsPager) mPagers.get(1);
    }
}
