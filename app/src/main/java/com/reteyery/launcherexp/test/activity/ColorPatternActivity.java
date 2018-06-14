package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;

import butterknife.BindView;

public class ColorPatternActivity extends BaseActivity {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_mask, null);
    }

    @Override
    protected void initOperation() {
    }

    @Override
    protected void initData() {

    }

}
