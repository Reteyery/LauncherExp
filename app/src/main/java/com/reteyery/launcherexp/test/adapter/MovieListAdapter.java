package com.reteyery.launcherexp.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.test.activity.MovieListActivity;
import com.reteyery.launcherexp.test.activity.SearchMovieActivity;
import com.reteyery.launcherexp.test.entity.MovieListFilterObj;
import com.reteyery.launcherexp.util.Constants;
import com.reteyery.launcherexp.view.tablayout.TabLayout;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListHolder> {
    private List<MovieListActivity.DataModel> modelList;
    private static final int IS_HEADER = 10000;
    private Context context;
    private List<MovieListFilterObj.DataBean.AreaItemListBean> area_item_list;
    private List<MovieListFilterObj.DataBean.OrderItemListBean> order_item_list;
    private List<MovieListFilterObj.DataBean.TagItemListBean> tag_item_list;
    private List<MovieListFilterObj.DataBean.YearItemListBean> year_item_list;
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
        initData(tabOrder, 1);
        TabLayout tabType = view.findViewById(R.id.tbl_type);
        initData(tabType, 2);
        TabLayout tabRegion = view.findViewById(R.id.tbl_region);
        initData(tabRegion, 3);
        TabLayout tabTime = view.findViewById(R.id.tbl_time);
        initData(tabTime, 4);
        ImageButton searchBtn = view.findViewById(R.id.ib_search);
        searchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, SearchMovieActivity.class);
            context.startActivity(intent);
        });
    }

    private void initData(TabLayout tabLayout, int type) {
        Gson gson = new Gson();
        MovieListFilterObj filterObj = gson.fromJson(Constants.ITEM_FILTER, MovieListFilterObj.class);
        if (null != filterObj){
            area_item_list = filterObj.getData().getArea_item_list();
            order_item_list = filterObj.getData().getOrder_item_list();
            tag_item_list = filterObj.getData().getTag_item_list();
            year_item_list = filterObj.getData().getYear_item_list();
        }
        switch (type){
            case 1:
                for (MovieListFilterObj.DataBean.AreaItemListBean areaItemListBean : area_item_list){
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setText(areaItemListBean.getItem_name());
                    tabLayout.addTab(tab);
                }
                break;
            case 2:
                for (MovieListFilterObj.DataBean.OrderItemListBean orderItemListBean : order_item_list){
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setText(orderItemListBean.getItem_name());
                    tabLayout.addTab(tab);
                }
                break;
            case 3:
                for (MovieListFilterObj.DataBean.TagItemListBean tagItemListBean : tag_item_list){
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setText(tagItemListBean.getItem_name());
                    tabLayout.addTab(tab);
                }
                break;
            case 4:
                for (MovieListFilterObj.DataBean.YearItemListBean yearItemListBean : year_item_list){
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setText(yearItemListBean.getItem_name());
                    tabLayout.addTab(tab);
                }
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0){
//            return IS_HEADER;
//        }else {
            return modelList.get(position).getType();
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListHolder holder, int position) {
        if (position > 0){
            holder.tvName.setText(modelList.get(position).getValue());
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
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
