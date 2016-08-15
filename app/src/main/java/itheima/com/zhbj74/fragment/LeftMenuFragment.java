package itheima.com.zhbj74.fragment;

import android.view.View;

import itheima.com.zhbj74.R;

/**
 * 侧滑菜单的 Fragment
 * Created by Administrator on 2016/8/15.
 */

public class LeftMenuFragment extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.left_menu_fragment,null);
        return view;
    }

    @Override
    public void initData() {

    }
}
