package com.reteyery.launcherexp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MovieRecyclerview extends RecyclerView {

    private OnScrollToBottomListener scrollToBottomListener;

    public interface OnScrollToBottomListener {
        void loadMore();
    }

    public void setScrollToBottomListener(OnScrollToBottomListener scrollToBottomListener) {
        this.scrollToBottomListener = scrollToBottomListener;
    }

    public MovieRecyclerview(Context context) {
        this(context, null);
    }

    public MovieRecyclerview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieRecyclerview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        if(isSlideToBottom()){
            scrollToBottomListener.loadMore();
        }
    }

    private boolean isSlideToBottom() {
        return this.computeVerticalScrollExtent() + this.computeVerticalScrollOffset() >= this.computeVerticalScrollRange();
    }
}
