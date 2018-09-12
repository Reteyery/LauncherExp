package com.reteyery.launcherexp.test.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingActivity extends BaseActivity {

    @BindView(R.id.v_loading)
    View vLoading;
    @BindView(R.id.v_loading2)
    View vLoading2;
    @BindView(R.id.v_loading3)
    View vLoading3;

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_loading, null);
    }

    @Override
    protected void initOperation() {
        ColorDrawable drawable = (ColorDrawable) vLoading.getBackground();
        drawable.setColor(getResources().getColor(R.color.greenyellow));
        new Thread(new Runnable() {
            @Override
            public void run() {
                vLoading.setBackground(drawable);
            }
        }).start();
    }

    @Override
    protected void initData() {

    }

}
