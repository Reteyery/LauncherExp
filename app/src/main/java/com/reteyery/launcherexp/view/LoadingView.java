package com.reteyery.launcherexp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.reteyery.launcherexp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingView extends RelativeLayout{
    CircleView vLoading, vLoading2, vLoading3;
    Animation scaleToBigAnimation, scaleToBigAnimation2, scaleToBigAnimation3,
            scaleToSmallAnimation, scaleToSmallAnimation2, scaleToSmallAnimation3;

    List<Animation> animList = new ArrayList<>();

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_loading, this);
        vLoading = view.findViewById(R.id.v_loading);
        vLoading2 = view.findViewById(R.id.v_loading2);
        vLoading3 = view.findViewById(R.id.v_loading3);
        scaleToBigAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.loading_to_big);
        scaleToSmallAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.loading_to_small);
        scaleToBigAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.loading_to_big);
        scaleToSmallAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.loading_to_small);
        scaleToBigAnimation3 = AnimationUtils.loadAnimation(getContext(), R.anim.loading_to_big);
        scaleToSmallAnimation3 = AnimationUtils.loadAnimation(getContext(), R.anim.loading_to_small);
        initData();
    }

    private void initData() {
        animList.add(scaleToBigAnimation);
        animList.add(scaleToSmallAnimation);
        animList.add(scaleToBigAnimation2);
        animList.add(scaleToSmallAnimation2);
        animList.add(scaleToBigAnimation3);
        animList.add(scaleToSmallAnimation3);

        for (int i = 0; i < animList.size(); i++){
            animList.get(i).setFillAfter(true);
        }

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
