package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.view.CircleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    List<Animation> animList = new ArrayList<>();
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

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                vLoading.setAnimation(scaleToBigAnimation);
                scaleToBigAnimation.start();
            }
        }, 0,1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                vLoading.setAnimation(scaleToSmallAnimation);
                vLoading2.setAnimation(scaleToBigAnimation2);
                scaleToSmallAnimation.start();
                scaleToBigAnimation2.start();
            }
        }, 250,1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                vLoading2.setAnimation(scaleToSmallAnimation2);
                vLoading3.setAnimation(scaleToBigAnimation3);
                scaleToSmallAnimation2.start();
                scaleToBigAnimation3.start();
            }
        }, 500,1000);

        scaleToBigAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                vLoading3.setAnimation(scaleToSmallAnimation3);
                scaleToSmallAnimation3.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
