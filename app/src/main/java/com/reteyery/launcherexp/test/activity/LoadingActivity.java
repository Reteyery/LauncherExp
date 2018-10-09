package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.view.CircleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    List<Animation> animList = new ArrayList<>();
    boolean cirOneToTurn = false, cirTwoToTurn = false, cirThreeToTurn = false;
    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_loading, null);
    }

    @Override
    protected void initData() {
        scaleToBigAnimation = AnimationUtils.loadAnimation(this, R.anim.loading_to_big);
        scaleToSmallAnimation = AnimationUtils.loadAnimation(this, R.anim.loading_to_small);
        scaleToBigAnimation2 = AnimationUtils.loadAnimation(this, R.anim.loading_to_big);
        scaleToSmallAnimation2 = AnimationUtils.loadAnimation(this, R.anim.loading_to_small);
        scaleToBigAnimation3 = AnimationUtils.loadAnimation(this, R.anim.loading_to_big);
        scaleToSmallAnimation3 = AnimationUtils.loadAnimation(this, R.anim.loading_to_small);
        animList.add(scaleToBigAnimation);
        animList.add(scaleToSmallAnimation);
        animList.add(scaleToBigAnimation2);
        animList.add(scaleToSmallAnimation2);
        animList.add(scaleToBigAnimation3);
        animList.add(scaleToSmallAnimation3);

        for (int i = 0; i < animList.size(); i++){
            animList.get(i).setFillAfter(true);
        }
    }

    @Override
    protected void initOperation() {
        vLoading.setAnimation(scaleToBigAnimation);

        scaleToBigAnimation2.setStartOffset(250);
        vLoading2.setAnimation(scaleToBigAnimation2);

        scaleToBigAnimation3.setStartOffset(500);
        vLoading3.setAnimation(scaleToBigAnimation3);

        scaleToBigAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "scale*****cirOneEndToTurn:" + "  " + cirOneToTurn);
                vLoading.clearAnimation();
                vLoading.setAnimation(scaleToSmallAnimation);
                scaleToSmallAnimation.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimation repeat round 1 to big");
            }
        });

        scaleToSmallAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToSmallAnimation.cancel();
                scaleToBigAnimation2.setStartOffset(250);
                vLoading2.setAnimation(scaleToBigAnimation2);
                scaleToBigAnimation2.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimation repeat round 1 to small");
            }
        });

        scaleToBigAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "scaleToBigAnimation2*****start");
                if (cirTwoToTurn){
                    Log.d(TAG, "scaleToBigAnimation2*****start");
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (cirTwoToTurn){
                    Log.d(TAG, "scaleToBigAnimation2*****start");
                }
                scaleToBigAnimation2.cancel();
                vLoading2.clearAnimation();
                vLoading2.setAnimation(scaleToSmallAnimation2);
                scaleToSmallAnimation2.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimation repeat round 2 to big");
            }
        });

        scaleToSmallAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cirTwoToTurn = true;
                scaleToSmallAnimation2.cancel();
//                vLoading2.clearAnimation();
                scaleToBigAnimation3.setStartOffset(500);
                vLoading3.setAnimation(scaleToBigAnimation3);
                scaleToBigAnimation3.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimation repeat round 2 to small");
            }
        });

        scaleToBigAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToBigAnimation3.cancel();
                vLoading3.clearAnimation();
                vLoading3.setAnimation(scaleToSmallAnimation3);
                scaleToSmallAnimation3.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimation repeat round 3 to big");
            }
        });

        scaleToSmallAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleToSmallAnimation3.cancel();
                vLoading.setAnimation(scaleToBigAnimation);
                cirOneToTurn = true;
                Log.d(TAG, "scale3*****cirOneToTurn:" + "  " + cirOneToTurn);
                scaleToBigAnimation.setStartOffset(250);
                scaleToBigAnimation.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimation repeat round 3 to small");
            }
        });

        scaleToBigAnimation.start();

    }

}
