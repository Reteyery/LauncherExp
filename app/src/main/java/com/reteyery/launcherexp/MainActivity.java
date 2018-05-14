package com.reteyery.launcherexp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.base.BaseRvAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.entity.Category;
import fm.qingting.qtsdk.entity.Channel;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    TabLayout mTabLayout;
    RecyclerView mRecyclerview;
    BaseRvAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mTabLayout = findViewById(R.id.tl_title);
        mRecyclerview = findViewById(R.id.list);

        ivPlay.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "click", LENGTH_SHORT).show();
        });
        listAdapter = new BaseRvAdapter<Channel>() {
            @Override
            public void bindData(SimpleHolder holder, Channel object) {
                holder.mTextView.setText(object.getTitle());
                Glide.with(holder.itemView.getContext()).load(object.getThumbs().getMediumThumb()).into(holder.mImageView);
                holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(v.getContext(), DetailsActivity.class);
//                        intent.putExtra(DetailsActivity.CHANNEL_ID, object.getId());
//                        v.getContext().startActivity(intent);
                    }
                });
            }
        };

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecyclerview.setAdapter(listAdapter);

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

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });
    }

    private void requestList(int tabId) {
        QTSDK.requestChannelOnDemandList(tabId, null, 1, (result, e) -> {
            if (e == null) {
                if (result != null) {
                    listAdapter.items = result.getData();
                    listAdapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(getBaseContext(), e.getMessage(), LENGTH_SHORT).show();
            }
        });
    }
}
