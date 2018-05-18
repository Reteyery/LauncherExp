package com.reteyery.launcherexp.buss.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reteyery.launcherexp.DetailListActivity;
import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.RadioMainActivity;
import com.reteyery.launcherexp.base.BaseFragment;
import com.reteyery.launcherexp.buss.adapter.SimpleAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import fm.qingting.qtsdk.QTException;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.callbacks.QTCallback;
import fm.qingting.qtsdk.entity.Category;
import fm.qingting.qtsdk.entity.Channel;
import fm.qingting.qtsdk.entity.QTListEntity;

import static android.widget.Toast.LENGTH_SHORT;

public class RadioListFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    List<Channel> channelList;
    SimpleAdapter listAdapter;
    int channelId;
    @SuppressLint("UseSparseArrays")
    Map<Integer, Category> categoryMap = new HashMap<>();

    @Override
    protected int createRootView(Bundle savedInstanceState) {
        return R.layout.recyclerview_normal_list;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

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
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(listAdapter);
        categoryMap = ((RadioMainActivity) Objects.requireNonNull(getActivity())).getCategoryMap();
        requestList(categoryMap.get(0).getId());
    }

    private void requestList(int tabId) {
        QTSDK.requestChannelOnDemandList(tabId,null,1,new QTCallback<QTListEntity<Channel>>() {
            @Override
            public void done(QTListEntity<Channel> result, QTException e) {
                if (e == null) {
                    if (result != null) {
                        channelList = result.getData();
                        listAdapter.items = channelList;
                        listAdapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(getContext(), e.getMessage(), LENGTH_SHORT).show();
                }
            }

        });
    }

}
