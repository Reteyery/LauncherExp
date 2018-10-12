package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.view.View;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;

public class LoadingActivity extends BaseActivity {
    final String TAG = this.getClass().getName();

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_loading, null);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initOperation() {

    }

}
