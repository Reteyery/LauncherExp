package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.test.adapter.MovieListAdapter;
import com.reteyery.launcherexp.test.entity.MovieListFilterObj;
import com.reteyery.launcherexp.test.utils.AppBarStateChangeListener;
import com.reteyery.launcherexp.util.Constants;
import com.reteyery.launcherexp.view.MovieRecyclerview;
import com.reteyery.launcherexp.view.tablayout.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends FragmentActivity implements MovieRecyclerview.OnScrollToBottomListener {
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.tbl_order)
    TabLayout tblOrder;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.tv_all_type)
    TextView tvAllType;
    @BindView(R.id.tbl_type)
    TabLayout tblType;
    @BindView(R.id.tv_all_region)
    TextView tvAllRegion;
    @BindView(R.id.tbl_region)
    TabLayout tblRegion;
    @BindView(R.id.tv_all_time)
    TextView tvAllTime;
    @BindView(R.id.tbl_time)
    TabLayout tblTime;
    @BindView(R.id.toolbar_collapsing)
    Toolbar toolbarCollapsing;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.recyclerview)
    MovieRecyclerview recyclerview;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.tabTitle)
    TabLayout tabTitle;
    private List<DataModel> modelList = new ArrayList<>();
    View view;
    MovieRecyclerview recyclerView;
    MovieListAdapter adapter;
    private List<MovieListFilterObj.DataBean.AreaItemListBean> area_item_list;
    private List<MovieListFilterObj.DataBean.OrderItemListBean> order_item_list;
    private List<MovieListFilterObj.DataBean.TagItemListBean> tag_item_list;
    private List<MovieListFilterObj.DataBean.YearItemListBean> year_item_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this, R.layout.activity_movie_list, null);
        setContentView(view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        recyclerView = (MovieRecyclerview) view.findViewById(R.id.recyclerview);
        adapter = new MovieListAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == (modelList.size() - 1)) {
                    return 5;
                } else{
                    return modelList.get(position - 1).getType();
                }
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state) {
                    case EXPANDED:
                        tabTitle.setVisibility(View.GONE);
                        break;
                    case IDLE:

                        break;
                    case COLLAPSED:
                        tabTitle.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        recyclerView.setScrollToBottomListener(this);
        initTab();
    }

    private void initTab() {
        initData(tblOrder, 1);
        initData(tblType, 2);
        initData(tblRegion, 3);
        initData(tblTime, 4);
    }

    @Override
    public void loadMore() {
        Random random = new Random();
        int type;
        for (int i = 0; i < 30; i++) {
            type = random.nextInt(5) + 1;
            modelList.add(new DataModel(type, "item****" + i));
        }
        recyclerView.post(() -> {
            // Notify adapter with appropriate notify methods
            adapter.notifyItemRangeInserted(modelList.size(), modelList.size() - 1);
        });
    }

    private void initData() {
        Random random = new Random();
        int type;
        for (int i = 0; i < 30; i++) {
            type = random.nextInt(5) + 1;
            modelList.add(new DataModel(type, "item****" + type));
        }
        adapter.setModelList(modelList);
        adapter.notifyDataSetChanged();
    }

    private void initData(TabLayout tabLayout, int type) {
        Gson gson = new Gson();
        MovieListFilterObj filterObj = gson.fromJson(Constants.ITEM_FILTER, MovieListFilterObj.class);
        if (null != filterObj) {
            area_item_list = filterObj.getData().getArea_item_list();
            order_item_list = filterObj.getData().getOrder_item_list();
            tag_item_list = filterObj.getData().getTag_item_list();
            year_item_list = filterObj.getData().getYear_item_list();
        }
        switch (type) {
            case 1:
                for (MovieListFilterObj.DataBean.AreaItemListBean areaItemListBean : area_item_list) {
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setText(areaItemListBean.getItem_name());
                    tabLayout.addTab(tab);

                    //临时测试title的显示和隐藏
                    TabLayout.Tab tab2 = tabTitle.newTab();
                    tab2.setText(areaItemListBean.getItem_name());
                    tabTitle.addTab(tab2);
                }
                break;
            case 2:
                for (MovieListFilterObj.DataBean.OrderItemListBean orderItemListBean : order_item_list) {
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setText(orderItemListBean.getItem_name());
                    tabLayout.addTab(tab);
                }
                break;
            case 3:
                for (MovieListFilterObj.DataBean.TagItemListBean tagItemListBean : tag_item_list) {
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setText(tagItemListBean.getItem_name());
                    tabLayout.addTab(tab);
                }
                break;
            case 4:
                for (MovieListFilterObj.DataBean.YearItemListBean yearItemListBean : year_item_list) {
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setText(yearItemListBean.getItem_name());
                    tabLayout.addTab(tab);
                }
                break;
        }

    }

    public class DataModel {
        int type;
        String value;

        DataModel(int type, String value) {
            this.type = type;
            this.value = value;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
