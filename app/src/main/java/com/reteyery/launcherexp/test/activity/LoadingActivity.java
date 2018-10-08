package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.view.CircleView;

import butterknife.BindView;

public class LoadingActivity extends BaseActivity {
    final String TAG = this.getClass().getName();
    @BindView(R.id.v_loading)
    CircleView vLoading;
    @BindView(R.id.v_loading2)
    CircleView vLoading2;
    @BindView(R.id.v_loading3)
    CircleView vLoading3;

    Animation scaleToBigAnimation, scaleToBigAnimation2, scaleToBigAnimation3,
            scaleToSmallAnimation, scaleToSmallAnimation2, scaleToSmallAnimation3;
    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_loading, null);
    }

    @Override
    protected void initOperation() {
        scaleToBigAnimation = AnimationUtils.loadAnimation(this, R.anim.loading_to_big);
        scaleToSmallAnimation = AnimationUtils.loadAnimation(this, R.anim.loading_to_small);
        vLoading.setAnimation(scaleToBigAnimation);

        scaleToBigAnimation2 = AnimationUtils.loadAnimation(this, R.anim.loading_to_big);
        scaleToSmallAnimation2 = AnimationUtils.loadAnimation(this, R.anim.loading_to_small);
        scaleToBigAnimation2.setStartOffset(250);
        vLoading2.setAnimation(scaleToBigAnimation2);

        scaleToBigAnimation3 = AnimationUtils.loadAnimation(this, R.anim.loading_to_big);
        scaleToSmallAnimation3 = AnimationUtils.loadAnimation(this, R.anim.loading_to_small);
        scaleToBigAnimation3.setStartOffset(500);
        vLoading3.setAnimation(scaleToBigAnimation3);

        scaleToBigAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToBigAnimation.cancel();
                vLoading.setAnimation(scaleToSmallAnimation);
                scaleToSmallAnimation.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleToSmallAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToSmallAnimation.cancel();
                vLoading.clearAnimation();
                vLoading2.setAnimation(scaleToSmallAnimation2);
                scaleToBigAnimation2.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleToBigAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToBigAnimation2.cancel();
                scaleToBigAnimation.reset();
                scaleToSmallAnimation.reset();
                vLoading2.setAnimation(scaleToSmallAnimation2);
                scaleToSmallAnimation2.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleToSmallAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToSmallAnimation2.cancel();
                vLoading2.clearAnimation();
//                vLoading3.setAnimation(scaleToSmallAnimation3);
//                scaleToBigAnimation3.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleToBigAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToBigAnimation3.cancel();
                vLoading3.setAnimation(scaleToSmallAnimation3);
                scaleToSmallAnimation3.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleToSmallAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToSmallAnimation3.cancel();
                vLoading3.clearAnimation();
//                vLoading.setAnimation(scaleToBigAnimation);
//                scaleToBigAnimation.setStartOffset(330);
//                scaleToBigAnimation.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleToBigAnimation.start();

    }

    @Override
    protected void initData() {

    }

}
