package com.reteyery.launcherexp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = onCreateView(savedInstanceState);
        setContentView(view);
        ButterKnife.bind(this);
        initData();
        initOperation();
    }

    protected abstract View onCreateView(Bundle savedInstanceState);

    protected abstract void initOperation();

    protected abstract void initData();
    protected void toast(String content){
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
