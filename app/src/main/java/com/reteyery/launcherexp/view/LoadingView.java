package com.reteyery.launcherexp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.ScaleAnimation;

import com.reteyery.launcherexp.R;

public class LoadingView extends View{
    private Paint mPaint;
    private int mWidth, mHeight;
    private ScaleAnimation scaleAnimation;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2, mHeight/2);
//        canvas.drawCircle(-80, 0, 10, mPaint);
        canvas.drawCircle(0, 0, 10, mPaint);
//        canvas.drawCircle(80, 0, 10, mPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}