package itheima.com.zhbj74.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import itheima.com.zhbj74.R;
import itheima.com.zhbj74.base.BasePager;
import itheima.com.zhbj74.base.impl.GovoPager;
import itheima.com.zhbj74.base.impl.HomePager;
import itheima.com.zhbj74.base.impl.NewsPager;
import itheima.com.zhbj74.base.impl.SettingsPager;
import itheima.com.zhbj74.base.impl.SmartServicePager;

/**
 * 主页面 Fragment
 * Created by Administrator on 2016/8/15.
 */

public class ContentFragment extends BaseFragment{

    private RadioGroup mRadioGroup;

    private List<BasePager> mPagers;//五个标签页的集合

    private ViewPager vpContent;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content,null);
        vpContent = (ViewPager) view.findViewById(R.id.vp_content);
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
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
