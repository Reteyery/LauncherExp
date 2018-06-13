package com.reteyery.launcherexp.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.test.activity.MovieListActivity;
import com.reteyery.launcherexp.test.activity.SearchMovieActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListHolder> {
    private List<MovieListActivity.DataModel> modelList;
    private static final int IS_HEADER = 10000;
    Context context;
    @NonNull
    @Override
    public MovieListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        context = parent.getContext();
        if (viewType != IS_HEADER){
            view = View.inflate(parent.getContext(), R.layout.item_movie, null);
        }else {
            view = View.inflate(parent.getContext(), R.layout.header_movie, null);
            initHeader(view);
        }
        return new MovieListHolder(view, viewType);
    }

    private void initHeader(View view) {
        TabLayout tabOrder = view.findViewById(R.id.tbl_order);
        initData(tabOrder);
        TabLayout tabType = view.findViewById(R.id.tbl_type);
        initData(tabType);
        TabLayout tabRegion = view.findViewById(R.id.tbl_region);
        initData(tabRegion);
        TabLayout tabTime = view.findViewById(R.id.tbl_time);
        initData(tabTime);
        ImageButton searchBtn = view.findViewById(R.id.ib_search);
        searchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, SearchMovieActivity.class);
            context.startActivity(intent);
        });
    }

    private void initData(TabLayout tabLayout) {
        List<String> titleList = new ArrayList<>();
        titleList.add("最近更新");
        titleList.add("最高人气");
        titleList.add("最受好评");
        titleList.add("最热动漫");
        titleList.add("最热武侠");
        titleList.add("最火偶像");
        for (String s : titleList){
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(s);
            tabLayout.addTab(tab);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return IS_HEADER;
        }else {
            return modelList.get(position - 1).getType();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListHolder holder, int position) {
        if (position > 0){
            holder.tvName.setText(modelList.get(position - 1).getValue());
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size() + 1;
    }

    public void setModelList(List<MovieListActivity.DataModel> modelList) {
        this.modelList = modelList;
    }

    class MovieListHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        MovieListHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == IS_HEADER){

            }else {
                tvName = itemView.findViewById(R.id.tv_name);
            }
        }
    }
}
