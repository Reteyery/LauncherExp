package com.reteyery.launcherexp.buss.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseRvHolder;
import com.reteyery.launcherexp.base.interfaces.LauncherOnItemClickListener;
import com.reteyery.launcherexp.base.interfaces.LauncherOnItemLongClickListener;

public class RadioListHolder extends BaseRvHolder{
    public ImageView mImageView;
    public TextView mTextView;
    public RadioListHolder(View itemView, Context context, LauncherOnItemClickListener itemClickListener, LauncherOnItemLongClickListener itemLongClickListener) {
        super(itemView, context, itemClickListener, itemLongClickListener);
        mTextView = itemView.findViewById(R.id.title);
        mImageView = itemView.findViewById(R.id.cover);
    }
}
