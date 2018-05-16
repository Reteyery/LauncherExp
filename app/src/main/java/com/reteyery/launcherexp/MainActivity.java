package com.reteyery.launcherexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.buss.adapter.SimpleAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.qingting.qtsdk.QTException;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.callbacks.QTCallback;
import fm.qingting.qtsdk.entity.Category;
import fm.qingting.qtsdk.entity.Channel;
import fm.qingting.qtsdk.entity.ChannelProgram;
import fm.qingting.qtsdk.entity.Edition;
import fm.qingting.qtsdk.entity.QTListEntity;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.iv_previous)
    ImageView ivPrevious;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview_detail_list)
    RecyclerView detailRecyclerview;
    @BindView(R.id.tl_title)
    TabLayout mTabLayout;
    @BindView(R.id.recyclerview_radio_list)
    RecyclerView mRecyclerview;

    SimpleAdapter listAdapter, detailListAdapter;
    int channelId;

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

        ivPlay.setOnClickListener(view -> {
            Intent intent = new Intent(this, RadioMainActivity.class);
            MainActivity.this.startActivity(intent);
//            Toast.makeText(MainActivity.this, "click", LENGTH_SHORT).show();
        });
        listAdapter = new SimpleAdapter<Channel>() {
            @Override
            public void bindData(SimpleHolder holder, Channel object) {
                channelId = object.getId();
                holder.mTextView.setText(object.getTitle());
                Glide.with(holder.itemView.getContext()).load(object.getThumbs().getMediumThumb()).into(holder.mImageView);
                holder.mLinearLayout.setOnClickListener((View v) -> {
                        Intent intent = new Intent(v.getContext(), DetailListActivity.class);
                        intent.putExtra(DetailListActivity.CHANNEL_ID, object.getId());
                        v.getContext().startActivity(intent);
//                    requestChannelDetails(channelId);
//                    requestChannelPrograms(channelId);
                });
            }
        };

        detailListAdapter = new SimpleAdapter<ChannelProgram>() {
            @Override
            public void bindData(SimpleHolder holder, ChannelProgram object) {
                holder.mTextView.setText(object.getTitle());
                if (object.getThumbs() != null) {
                    Glide.with(holder.itemView.getContext()).load(object.getThumbs().getMediumThumb()).into(holder.mImageView);
                }
                holder.mLinearLayout.setOnClickListener(v -> QTSDK.requestProgramUrl(channelId, object.getId(), (result, e) -> {
                    if (e == null) {
//                        url.setText("播放地址：" + result.getEditions().get(0).getUrl().get(0));
                        ArrayList<Edition> editions = new ArrayList<>();
                        editions.addAll(result.getEditions());
//                        PlayerActivity.Companion.start(DetailsActivity.this,editions);
                    } else {
                        Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        };
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecyclerview.setAdapter(listAdapter);

        detailRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        detailRecyclerview.setAdapter(detailListAdapter);

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

    private void requestChannelPrograms(int channelId) {
        QTSDK.requestChannelOnDemandProgramList(channelId, 1, new QTCallback<QTListEntity<ChannelProgram>>() {
            @Override
            public void done(QTListEntity<ChannelProgram> result, QTException e) {
                if (e == null) {
                    detailListAdapter.items = result.getData();
                    detailListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void requestChannelDetails(int channelId) {
        QTSDK.requestChannelOnDemand(channelId, new QTCallback<Channel>() {
            @Override
            public void done(Channel result, QTException e) {
                if (e == null) {
                    tvTitle.setText(result.getTitle());
                    Glide.with(getBaseContext())
                            .load(result.getThumbs().getMediumThumb())
                            .into(ivCover);
                }
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
