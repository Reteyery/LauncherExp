package com.reteyery.launcherexp.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.reteyery.launcherexp.R;

import java.util.List;

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter{
    public List<T> items;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        BaseRvAdapter.SimpleHolder holder = new BaseRvAdapter.SimpleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
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
        public ImageView mImageView;
        public LinearLayout mLinearLayout;
        public TextView mTextView;

        public SimpleHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.cover);
            mTextView = itemView.findViewById(R.id.title);
            mLinearLayout = itemView.findViewById(R.id.container);
        }

        public void bind(T object) {
            bindData(this, object);
        }
    }
}
