package com.reteyery.launcherexp.test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.test.activity.MovieListActivity;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListHolder> {
    private List<MovieListActivity.DataModel> modelList;
    @NonNull
    @Override
    public MovieListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieListHolder(View.inflate(parent.getContext(), R.layout.item_movie, null));
    }

    @Override
    public int getItemViewType(int position) {
        return modelList.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListHolder holder, int position) {
        holder.tvName.setText(modelList.get(position).getValue());
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

        MovieListHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
