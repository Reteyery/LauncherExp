package com.reteyery.launcherexp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.reteyery.launcherexp.base.interfaces.LauncherOnItemClickListener;
import com.reteyery.launcherexp.base.interfaces.LauncherOnItemLongClickListener;

/**
 * ViewHolder封装
 * Created by reteyery on 2017/6/15.
 */

public class BaseRvHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    private Context context;
    SparseArray<View> sparseArray = new SparseArray<>();
    private LauncherOnItemClickListener itemClickListener;
    private LauncherOnItemLongClickListener itemLongClickListener;

    public BaseRvHolder(View itemView, Context context, LauncherOnItemClickListener itemClickListener, LauncherOnItemLongClickListener itemLongClickListener) {
        super(itemView);
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.itemLongClickListener = itemLongClickListener;
        initView();
    }

    public void initView(){}

    @Override
    public void onClick(View v) {
        if (v != null && itemClickListener != null){
            itemClickListener.onRvItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v != null && itemLongClickListener != null){
            itemLongClickListener.onRvItemLongClick(v, getAdapterPosition());
        }
        return false;
    }
}
