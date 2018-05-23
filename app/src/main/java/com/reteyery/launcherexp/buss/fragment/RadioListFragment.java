package com.reteyery.launcherexp.buss.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reteyery.launcherexp.DetailListActivity;
import com.reteyery.launcherexp.MainActivity;
import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseFragment;
import com.reteyery.launcherexp.buss.adapter.SimpleAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.entity.Category;
import fm.qingting.qtsdk.entity.Channel;

import static android.widget.Toast.LENGTH_SHORT;

@SuppressLint("ValidFragment")
public class RadioListFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.srl_refreshLayout)
    SmartRefreshLayout refreshLayout;

    List<Channel> channelList;
    SimpleAdapter listAdapter;
    int channelId, tabId;
    Unbinder unbinder;

    @SuppressLint("ValidFragment")
    public RadioListFragment(int tabId) {
        this.tabId = tabId;
    }

    SparseArray<Category> categoryArray = new SparseArray<>();

    @Override
    protected int createRootView(Bundle savedInstanceState) {
        return R.layout.recyclerview_normal_list;
    }

    @Override
    public void initData() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(getContext())));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
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
                });
            }
        };
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(listAdapter);
        if (tabId != 0) {
            requestList(tabId);
        } else {
            categoryArray = ((MainActivity) Objects.requireNonNull(getActivity())).getCategoryArray();
            requestList(categoryArray.get(0).getId());
        }

    }

    private void requestList(int tabId) {
        QTSDK.requestChannelOnDemandList(tabId, null, 1, (result, e) -> {
            if (e == null) {
                if (result != null) {
                    channelList = result.getData();
                    listAdapter.items = channelList;
                    listAdapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(getContext(), e.getMessage(), LENGTH_SHORT).show();
            }
        });
    }

}
