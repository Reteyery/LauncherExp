package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MediaSelectActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    List<String> dataList = new ArrayList<>();
    MediaAdapter adapter;
    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_media_select, null);
    }

    @Override
    protected void initOperation() {
        adapter = new MediaAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(layoutManager);
        for (int i = 0; i < 30; i ++){
            dataList.add(i + "");
        }
        adapter.setDataList(dataList);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

    private class MediaAdapter extends RecyclerView.Adapter<MediaHolder>{
        List<String> dataList = new ArrayList<>();
        @NonNull
        @Override
        public MediaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_movie, null);
            return new MediaHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MediaHolder holder, int position) {
            holder.tvMedia.setText("WEQEQ");
        }

        @Override
        public int getItemCount() {
            return null == dataList ? 0 : dataList.size();
        }

        void setDataList(List<String> dataList) {
            this.dataList = dataList;
        }
    }

    private class MediaHolder extends RecyclerView.ViewHolder{
        TextView tvMedia;
        MediaHolder(View itemView) {
            super(itemView);
            tvMedia = itemView.findViewById(R.id.tv_movie);
        }

    }
}
