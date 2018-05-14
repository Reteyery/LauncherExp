package com.reteyery.launcherexp.base.interfaces;

import android.view.View;

import com.reteyery.launcherexp.base.BaseRvAdapter;

public interface LauncherOnItemLongClickListener{
    void onRvItemLongClick(View itmeView, int position, BaseRvAdapter adapter);
    void onRvItemLongClick(View itmeView, int position);
}