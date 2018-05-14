package com.reteyery.launcherexp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.reteyery.launcherexp.base.interfaces.LauncherOnItemClickListener;
import com.reteyery.launcherexp.base.interfaces.LauncherOnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter封装
 * Created by reteyery on 2017/6/15.
 */

public abstract class BaseRvAdapter<M, VH extends BaseRvHolder> extends RecyclerView.Adapter<VH>{
    private List<M> mDatas = new ArrayList<>();
    private LauncherOnItemClickListener itemClickListener;
    private LauncherOnItemLongClickListener itemLongClickListener;
    private Context context;
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = View.inflate(context, getLayout(parent, viewType), null);
        return createViewHolder(view, context, itemClickListener, itemLongClickListener);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindViewData(holder, position, getEntity(position));
    }

    private M getEntity(int position) {
        return mDatas.get(position);
    }

    public abstract VH createViewHolder(View view, Context context, LauncherOnItemClickListener itemClickListener, LauncherOnItemLongClickListener itemLongClickListener);
    public abstract void onBindViewData(VH holder, int position, M entity);


    public abstract int getLayout(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<M> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void setItemClickListener(LauncherOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(LauncherOnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }
}