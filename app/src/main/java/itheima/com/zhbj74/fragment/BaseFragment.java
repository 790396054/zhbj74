package itheima.com.zhbj74.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment 的基类
 * Created by Administrator on 2016/8/15.
 */

public abstract class BaseFragment extends Fragment {

    public Activity mActivity;//这个 activity 就是 mainActivity

    //Fragment 的创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();//获取当前 Fragment 所依赖的 activity
    }

    //初始化 Fragment 的布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    //Fragment 所依赖的 activity 的 onCreate 方法执行完毕
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
    }

    public abstract View initView();

    public abstract void initData();
}
