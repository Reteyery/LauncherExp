package com.reteyery.launcherexp.buss.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseFragment;
import com.reteyery.launcherexp.buss.adapter.RadioListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.qingting.qtsdk.entity.Channel;

public class RadioListFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    RadioListAdapter adapter;
    List<Channel> channelList = new ArrayList<>();

    @Override
    public View getLayoutView() {
        return View.inflate(getContext(), R.layout.recyclerview_normal_list, null);
    }

    @Override
    public void initData() {
//        adapter = new RadioListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);

        recyclerview.setAdapter(adapter);
        if (null != channelList && channelList.size() > 0){
//            adapter.setDatas(channelList);
            adapter.notifyDataSetChanged();
        }
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }
}
