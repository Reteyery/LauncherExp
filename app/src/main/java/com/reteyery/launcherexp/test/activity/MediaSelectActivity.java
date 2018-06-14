package com.reteyery.launcherexp.test.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.test.entity.BusEntity;
import com.reteyery.launcherexp.util.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MediaSelectActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    LinearSnapHelper linearSnapHelper;
    List<MovieEntity> dataList = new ArrayList<>();
    MediaAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_media_select, null);
    }

    @Override
    protected void initOperation() {
        adapter = new MediaAdapter();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(layoutManager);
        MovieEntity entity;
        for (int i = 0; i < 30; i ++){
            entity = new MovieEntity(i, i + "");
            dataList.add(entity);
        }
        linearSnapHelper = new LinearSnapHelper(){
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                return super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
            }
        };
        linearSnapHelper.attachToRecyclerView(recyclerview);
        recyclerview.setAdapter(adapter);
        adapter.setDataList(dataList);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        final int[] position = {-1};
        RxBus.getInstance().toObservable().map(o -> (BusEntity) o).subscribe(eventMsg -> {
            if (eventMsg != null) {
                position[0] = eventMsg.getCodeId();
                Toast.makeText(MediaSelectActivity.this, position[0] + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class MediaAdapter extends RecyclerView.Adapter<MediaHolder>{
        List<MovieEntity> dataList = new ArrayList<>();
        int oldSelectPos = -1;
        Context context;
        @NonNull
        @Override
        public MediaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
            View view = View.inflate(parent.getContext(), R.layout.item_chapter, null);
            return new MediaHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MediaHolder holder, int position) {
            holder.tvMedia.setText(dataList.get(position).getMovieChapter());
            holder.tvMedia.setOnClickListener(v -> {
                if (holder.tvMedia.getCurrentTextColor() == getResources().getColor(R.color.white)){
                    holder.tvMedia.setTextColor(getResources().getColor(R.color.greenyellow));
                }else{
                    holder.tvMedia.setTextColor(getResources().getColor(R.color.white));
                }
                oldSelectPos = position;
            });
        }

        @Override
        public int getItemCount() {
            return null == dataList ? 0 : dataList.size();
        }

        void setDataList(List<MovieEntity> dataList) {
            this.dataList = dataList;
        }
    }

    class MovieEntity {
        int id;
        String movieChapter;

        MovieEntity(int id, String movieChapter) {
            this.id = id;
            this.movieChapter = movieChapter;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieChapter() {
            return movieChapter;
        }

        public void setMovieChapter(String movieChapter) {
            this.movieChapter = movieChapter;
        }
    }
    private class MediaHolder extends RecyclerView.ViewHolder{
        TextView tvMedia;
        MediaHolder(View itemView) {
            super(itemView);
            tvMedia = itemView.findViewById(R.id.tv_chapter);
        }

    }
}
