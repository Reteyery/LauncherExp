package com.reteyery.launcherexp.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class FlexibleImageView extends View {
    public FlexibleImageView(Context context) {
        this(context, null);
    }

    public FlexibleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexibleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {

    }
}
