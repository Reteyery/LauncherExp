package com.reteyery.launcherexp.buss.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reteyery.launcherexp.DetailListActivity;
import com.reteyery.launcherexp.MainActivity;
import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseFragment;
import com.reteyery.launcherexp.buss.adapter.SimpleAdapter;
import com.reteyery.launcherexp.util.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.entity.Category;
import fm.qingting.qtsdk.entity.Channel;

import static android.widget.Toast.LENGTH_SHORT;

@SuppressLint("ValidFragment")
public class RadioListFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener{
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.srl_refreshLayout)
    SmartRefreshLayout refreshLayout;

    List<Channel> channelList;
    SimpleAdapter listAdapter;
    int channelId, tabId;
    int pageIndex = 1;

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
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
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
            requestList(tabId, pageIndex, Constants.STATUS_REFRESH);
        } else {
            categoryArray = ((MainActivity) Objects.requireNonNull(getActivity())).getCategoryArray();
            requestList(categoryArray.get(0).getId(), pageIndex, Constants.STATUS_REFRESH);
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        pageIndex = 1;
        requestList(tabId, pageIndex, Constants.STATUS_REFRESH);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        pageIndex ++;
        requestList(tabId, pageIndex, Constants.STATUS_LOAD);
    }

    private void requestList(int categoryId, int pageIndex, int status) {
        QTSDK.requestChannelOnDemandList(categoryId, null, pageIndex, (result, e) -> {
            if (e == null && result != null) {
                if (status == Constants.STATUS_REFRESH) {
                    refreshLayout.finishRefresh();
                    channelList = result.getData();
                    listAdapter.items = channelList;
                    listAdapter.notifyDataSetChanged();
                }else {
                    refreshLayout.finishLoadMore();
                    listAdapter.items.addAll(result.getData());
                    listAdapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(getContext(), e != null ? e.getMessage() : null, LENGTH_SHORT).show();
            }
        });
    }

}
