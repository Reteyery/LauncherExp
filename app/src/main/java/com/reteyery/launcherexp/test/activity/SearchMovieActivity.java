package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.view.View;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;

public class SearchMovieActivity extends BaseActivity {
    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_media_search, null);
    }

    @Override
    protected void initOperation() {

    }

    @Override
    protected void initData() {

    }
}
