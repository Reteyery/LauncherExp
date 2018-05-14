package com.reteyery.launcherexp.base.interfaces;

import android.view.View;

import com.reteyery.launcherexp.base.BaseRvAdapter;

public interface LauncherOnItemClickListener{
        void onRvItemClick(View itemView, int position, BaseRvAdapter adapter);
        void onRvItemClick(View itemView, int position);
}