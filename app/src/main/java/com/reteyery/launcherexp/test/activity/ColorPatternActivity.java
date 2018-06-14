package com.reteyery.launcherexp.test.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.util.BitmapUtils;

import butterknife.BindView;

public class ColorPatternActivity extends BaseActivity {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.v_mask)
    View vMask;
    @BindView(R.id.iv_crop)
    ImageView ivCrop;
    Bitmap bitmap;
    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_mask, null);

    }

    @Override
    protected void initOperation() {
        bitmap = BitmapUtils.ReadBitmapById(this, R.mipmap.bg_test);//320,510
        /* createBitmap裁剪参数，前两个为裁剪点（x，y）, 后面为裁剪宽度和高度
         * 被裁剪bitmap顶点为(0, 0)，并非xml布局中位置
         * x + width = 320, y + height = 510
         */
        Bitmap cropBitmap = Bitmap.createBitmap(bitmap, 0, 390, 320, 120);
        ivCrop.setImageBitmap(cropBitmap);
    }

    @Override
    protected void initData() {

    }

}
