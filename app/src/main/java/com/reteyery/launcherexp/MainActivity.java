package com.reteyery.launcherexp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.base.BaseFragment;
import com.reteyery.launcherexp.buss.fragment.RadioListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.entity.Category;
import fm.qingting.qtsdk.entity.Channel;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    @BindView(R.id.tl_title)
    TabLayout tabLayout;
    @BindView(R.id.vp_content)
    ViewPager viewPager;

    int tabSize;
    List<BaseFragment> fragmentList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    RadioPagerAdapter pagerAdapter;
    List<Channel> channelList = new ArrayList<>();
    SparseArray<Category> categoryArray = new SparseArray<>();

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return  View.inflate(this, R.layout.activity_radio_main, null);
    }

    @Override
    protected void initData() {
        requestCategory();
        viewPager.addOnPageChangeListener(this);
        pagerAdapter = new RadioPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);
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
                        titleList.add(category.getName());
                        tabLayout.addTab(tab);
                        fragmentList.add(new RadioListFragment(category.getId()));
                    }

                    for (int i = 0; i < result.size(); i++){
                        categoryArray.append(i, result.get(i));
                    }
                    pagerAdapter.setFragmentList(fragmentList);
                    pagerAdapter.notifyDataSetChanged();
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
                    channelList = result.getData();
                }
            }else{
                Toast.makeText(getBaseContext(), e.getMessage(), LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initOperation() {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Category category = (Category) tab.getTag();
        if (category != null) {
            requestList(category.getId());
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public SparseArray<Category> getCategoryArray() {
        return categoryArray;
    }
    class RadioPagerAdapter extends FragmentPagerAdapter {
        List<BaseFragment> mFragmentList;
        RadioPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return null == fragmentList ? 0 : fragmentList.size();
        }

        void setFragmentList(List<BaseFragment> mFragmentList) {
            this.mFragmentList = mFragmentList;
        }
    }

}
