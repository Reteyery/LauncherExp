package com.reteyery.launcherexp.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.reteyery.launcherexp.R;

public class FlexibleView extends View {
    Paint mPaint;
    RectF rectF;
    public FlexibleView(Context context) {
        super(context);
    }

    public FlexibleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexibleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint =  new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setAntiAlias(true);
        rectF = new RectF(-100, -100, 100, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectF, mPaint);
        canvas.save();
        canvas.rotate(90, 300, 500);
    }
}
