package com.reteyery.launcherexp.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.reteyery.launcherexp.MainActivity;
import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.test.activity.ColorPatternActivity;
import com.reteyery.launcherexp.test.activity.MediaSelectActivity;
import com.reteyery.launcherexp.test.activity.MovieListActivity;
import com.reteyery.launcherexp.test.activity.SearchMovieActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_movie)
    TextView tvMovie;
    @BindView(R.id.tv_radio)
    TextView tvRadio;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_snapTest)
    TextView tvSnapTest;
    @BindView(R.id.tv_maskTest)
    TextView tvMaskTest;

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_test, null);
    }

    @Override
    protected void initOperation() {
        tvMovie.setOnClickListener(this);
        tvRadio.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        tvSnapTest.setOnClickListener(this);
        tvMaskTest.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_movie:
                intent = new Intent(this, MovieListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_search:
                intent = new Intent(this, SearchMovieActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_radio:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_snapTest:
                intent = new Intent(this, MediaSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_maskTest:
                intent = new Intent(this, ColorPatternActivity.class);
                startActivity(intent);
                break;
        }
    }

}
