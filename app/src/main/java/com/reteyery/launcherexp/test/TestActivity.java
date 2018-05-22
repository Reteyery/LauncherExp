package com.reteyery.launcherexp.test;

import android.os.Bundle;
import android.view.View;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;

public class TestActivity extends BaseActivity {
    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_test, null);
    }

    @Override
    protected void initOperation() {

    }

    @Override
    protected void initData() {

    }
}
