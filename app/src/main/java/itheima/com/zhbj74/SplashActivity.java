package itheima.com.zhbj74;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import itheima.com.zhbj74.utils.PreUtils;

/**
 * 闪屏动画
 */
public class SplashActivity extends Activity {

    private RelativeLayout mRlroot;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;

        mRlroot = (RelativeLayout) findViewById(R.id.rl_root);

        //旋转动画
        RotateAnimation animRotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animRotate.setDuration(1000);
        animRotate.setFillAfter(true);

        //缩放动画
        ScaleAnimation animScale = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);

        //渐变动画
        AlphaAnimation animAlpha = new AlphaAnimation(0,1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        //动画集合
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animRotate);
        set.addAnimation(animScale);
        set.addAnimation(animAlpha);

        //启动动画
        mRlroot.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束执行
                //判断是否是第一次进入
                //第一次进入，进入新手引导页面，否则，进入主页
                Intent intent;
                if (PreUtils.getBoolean(mContext,"is_first_enter",true)){
                    //第一次进入，进入引导页
                    intent = new Intent(mContext,GuideActivity.class);
                    //设置是否第一次进入为false
                    //PreUtils.setBoolean(mContext,"is_first_enter",false);
                }else{
                    //进入主页
                    intent = new Intent(mContext,MainActivity.class);
                }
                startActivity(intent);

                //关闭当前页
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
