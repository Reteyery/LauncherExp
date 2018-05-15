package com.reteyery.launcherexp.buss.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseRvAdapter;
import com.reteyery.launcherexp.base.interfaces.LauncherOnItemClickListener;
import com.reteyery.launcherexp.base.interfaces.LauncherOnItemLongClickListener;
import com.reteyery.launcherexp.buss.holder.RadioListHolder;

import fm.qingting.qtsdk.entity.Channel;

public class RadioListAdapter extends BaseRvAdapter<Channel, RadioListHolder>{
    @Override
    public RadioListHolder createViewHolder(View view, Context context, LauncherOnItemClickListener itemClickListener, LauncherOnItemLongClickListener itemLongClickListener) {
        return new RadioListHolder(view, context, itemClickListener, itemLongClickListener);
    }

    @Override
    public void onBindViewData(RadioListHolder holder, int position, Channel entity) {
        holder.mTextView.setText(entity.getTitle());
        Glide.with(holder.itemView.getContext()).load(entity.getThumbs().getMediumThumb()).into(holder.mImageView);
    }

    @Override
    public int getLayout(ViewGroup parent, int viewType) {
        return R.layout.item_view;
    }
}
