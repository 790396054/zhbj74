package itheima.com.zhbj74;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import org.xutils.x;

import itheima.com.zhbj74.fragment.ContentFragment;
import itheima.com.zhbj74.fragment.LeftMenuFragment;

/**
 * 首页
 */
public class MainActivity extends SlidingFragmentActivity {

    private static final String TAG_CONTENT = "TAG_CONTENT";
    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.Ext.init(getApplication());//初始化xUtils

        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
        setContentView(R.layout.activity_main);


        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全局触摸
        slidingMenu.setBehindOffset(400);

        initFragment();
    }

    private void initFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 用fragment替换帧布局;参1:帧布局容器的id;参2:是要替换的fragment;参3:标记
        transaction.replace(R.id.fl_main,new ContentFragment(),TAG_CONTENT);
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),TAG_LEFT_MENU);

        transaction.commit();
    }

    /**
     * 返回 LeftMenuFragment
     * @return
     */
    public LeftMenuFragment getLeftMenuFragment(){
        FragmentManager manager = getSupportFragmentManager();
        return (LeftMenuFragment) manager.findFragmentByTag(TAG_LEFT_MENU);
    }

    /**
     * 返回 ContentFragment
     * @return
     */
    public ContentFragment getContentFragment(){
        FragmentManager manager = getSupportFragmentManager();
        return (ContentFragment) manager.findFragmentByTag(TAG_CONTENT);
    }
}
