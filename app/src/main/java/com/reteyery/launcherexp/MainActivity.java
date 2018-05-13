package com.reteyery.launcherexp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import fm.qingting.qtsdk.QTException;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.callbacks.QTCallback;
import fm.qingting.qtsdk.entity.Category;
import fm.qingting.qtsdk.entity.Channel;
import fm.qingting.qtsdk.entity.QTListEntity;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mTabLayout = findViewById(R.id.tl_title);
        QTSDK.requestChannelOnDemandCategories((result, e) -> {
            if (e == null) {
                if (result != null && result.size() > 0) {
                    for (Category category : result) {
                        TabLayout.Tab tab = mTabLayout.newTab();
                        tab.setText(category.getName());
                        tab.setTag(category);
                        mTabLayout.addTab(tab);
                    }
                    requestList(result.get(0).getId());
                }
            } else {
                Toast.makeText(getBaseContext(), e.getMessage(), LENGTH_SHORT).show();
            }
        });
    }

    private void requestList(int tabId) {
        QTSDK.requestChannelOnDemandList(tabId,null,1, (result, e) -> {
            if (e == null) {
                if (result != null) {
//                        listAdapter.items = result.getData();
//                        listAdapter.notifyDataSetChanged();
                }
            }else{
                Toast.makeText(getBaseContext(), e.getMessage(), LENGTH_SHORT).show();
            }
        });
    }
}
