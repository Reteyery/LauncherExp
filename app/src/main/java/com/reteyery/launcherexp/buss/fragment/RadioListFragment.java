package com.reteyery.launcherexp.buss.fragment;

import android.os.Bundle;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import fm.qingting.qtsdk.entity.Channel;

public class RadioListFragment extends BaseFragment {

    List<Channel> channelList = new ArrayList<>();
    @Override
    protected int createRootView(Bundle savedInstanceState) {
        return R.layout.recyclerview_normal_list;
    }

    @Override
    public void initData() {

    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }
}
