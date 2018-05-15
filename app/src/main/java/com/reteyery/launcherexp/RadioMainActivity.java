package com.reteyery.launcherexp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.base.BaseFragment;
import com.reteyery.launcherexp.buss.adapter.RadioListAdapter;
import com.reteyery.launcherexp.buss.fragment.RadioListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.qingting.qtsdk.QTException;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.callbacks.QTCallback;
import fm.qingting.qtsdk.entity.Category;
import fm.qingting.qtsdk.entity.Channel;
import fm.qingting.qtsdk.entity.QTListEntity;

import static android.widget.Toast.LENGTH_SHORT;

public class RadioMainActivity extends BaseActivity {
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.iv_previous)
    ImageView ivPrevious;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.tl_title)
    TabLayout tabLayout;
    @BindView(R.id.vp_content)
    ViewPager viewPager;

    int tabSize;
    List<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    public int getLayoutContentViewID() {
        return R.layout.activity_radio_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        requestCategory();
    }

    private void requestCategory() {
        QTSDK.requestChannelOnDemandCategories((result, e) -> {
            if (e == null) {
                if (result != null && result.size() > 0) {
                    tabSize = result.size();
                    for (Category category : result) {
                        TabLayout.Tab tab = tabLayout.newTab();
                        tab.setText(category.getName());
                        tab.setTag(category);
                        tabLayout.addTab(tab);
                        fragmentList.add(new RadioListFragment());
                    }
                    viewPager.setAdapter(new RadioPagerAdapter(getSupportFragmentManager()));
//                    tabLayout.setupWithViewPager(viewPager);
                    requestList(result.get(0).getId());
                }
            } else {
                Toast.makeText(getBaseContext(), e.getMessage(), LENGTH_SHORT).show();
            }
        });
    }

    private void requestList(int tabId) {
        QTSDK.requestChannelOnDemandList(tabId,null,1,new QTCallback<QTListEntity<Channel>>() {
            @Override
            public void done(QTListEntity<Channel> result, QTException e) {
                if (e == null) {
                    if (result != null) {
                        RadioListFragment radioListFragment = (RadioListFragment) fragmentList.get(0);
                        
                        radioListFragment.setChannelList(result.getData());
                    }
                }else{
                    Toast.makeText(getBaseContext(), e.getMessage(), LENGTH_SHORT).show();
                }
            }

        });
    }

    class RadioPagerAdapter extends FragmentPagerAdapter{

        public RadioPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
