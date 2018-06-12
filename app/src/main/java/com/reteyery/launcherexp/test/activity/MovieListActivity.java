package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.test.adapter.MovieListAdapter;
import com.reteyery.launcherexp.view.MovieRecyclerview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieListActivity extends FragmentActivity implements MovieRecyclerview.OnScrollToBottomListener{
    private List<DataModel> modelList = new ArrayList<>();
    View view;
    MovieRecyclerview recyclerView;
    MovieListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view  = View.inflate(this, R.layout.activity_movie_list, null);
        setContentView(view);
        initView();
        initData();
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new MovieListAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return modelList.get(position).getType();
            }
        });

        recyclerView.setScrollToBottomListener(this);
    }

    @Override
    public void loadMore() {
//        Toast.makeText(this, "I am Bottom", Toast.LENGTH_SHORT).show();
        Random random = new Random();
        int type;
        for (int i = 0; i < 30; i ++){
            type = random.nextInt(5) + 1;
            modelList.add(new DataModel(type, "item****" + type));
        }
        recyclerView.post(() -> {
            // Notify adapter with appropriate notify methods
            adapter.notifyItemRangeInserted(modelList.size(), modelList.size() - 1);
        });
    }

    private void initData() {
        Random random = new Random();
        int type;
        for (int i = 0; i < 30; i ++){
            type = random.nextInt(5) + 1;
            modelList.add(new DataModel(type, "item****" + type));
        }
        adapter.setModelList(modelList);
        adapter.notifyDataSetChanged();
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
