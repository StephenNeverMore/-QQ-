package com.example.shuang.animator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class ThirdActivity extends Activity implements View.OnClickListener {

    private int[] res = new int[]{R.id.iv_a, R.id.iv_b, R.id.iv_c, R.id.iv_d, R.id.iv_e, R.id.iv_f, R.id.iv_g, R.id.iv_h, R.id.iv_i};
    private List<ImageView> lists = new ArrayList<>();

    private MySlidingMenu slidingMenu;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initViews();
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.viewanim);
//        LayoutAnimationController lac = new LayoutAnimationController(animation);
//        ((RelativeLayout)findViewById(R.id.layout_third)).setLayoutAnimation(lac);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.statusbar_bg);//通知栏所需颜色


        WindowManager manager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        width = manager.getDefaultDisplay().getWidth();
        height = manager.getDefaultDisplay().getHeight();

        slidingMenu = (MySlidingMenu) findViewById(R.id.slidingmenu);
    }

    private void initViews() {
        for (int i = 0; i < res.length; i++) {
            final ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationY", -1000, 0);
            animator.setDuration(600);
            animator.setStartDelay(20 * i);
            animator.setInterpolator(new AnticipateOvershootInterpolator());
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    imageView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            animator.start();
            lists.add(imageView);
        }
    }

    @Override
    public void onClick(View v) {
//        for (int i = res.length; i > 0; i--) {
//            ObjectAnimator animator = ObjectAnimator.ofFloat(lists.get(i - 1), "translationY", 0, -1000);
//            animator.setDuration(600);
//            animator.setStartDelay(20 * i);
//            animator.setInterpolator(new AnticipateInterpolator());
//            animator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    finish();
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//                }
//            });
//            animator.start();
//        }
        slidingMenu.toggle();
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}

