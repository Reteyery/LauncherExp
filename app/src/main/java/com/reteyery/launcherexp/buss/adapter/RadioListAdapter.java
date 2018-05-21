package com.reteyery.launcherexp.buss.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.reteyery.launcherexp.R;

import java.util.List;

public abstract class RadioListAdapter<T> extends RecyclerView.Adapter {

    public List<T> items;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_radio_list, parent, false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SimpleHolder) holder).bind(items.get(position));
    }

    public abstract void bindData(SimpleHolder holder, T object);

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public class SimpleHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivPlay;
        public ConstraintLayout mConstraintLayout;

        public SimpleHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivPlay = itemView.findViewById(R.id.iv_play);
            mConstraintLayout = itemView.findViewById(R.id.cl_layout);
        }

        public void bind(T object) {
            bindData(this, object);
        }
    }
}
