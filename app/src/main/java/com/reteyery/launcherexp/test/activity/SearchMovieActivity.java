package com.reteyery.launcherexp.test.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.view.flowlayout.FlowLayout;
import com.reteyery.launcherexp.view.flowlayout.TagAdapter;
import com.reteyery.launcherexp.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 影视搜索
 */
public class SearchMovieActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;

    @BindView(R.id.iv_down)
    ImageView ivDown;

    List<String> itemList = new ArrayList<>();

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_media_search, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_down:

                break;
        }
    }

    @Override
    protected void initOperation() {
        ivDown.setOnClickListener(this);
        flowlayout.setAdapter(new TagAdapter<String>(itemList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getBaseContext()).inflate(R.layout.tv, flowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    @Override
    protected void initData() {
        itemList.add("我是传奇");
        itemList.add("魔兽");
        itemList.add("王者荣耀");
        itemList.add("塞尔达传说");
        itemList.add("喷射战士");
        itemList.add("生化危机");
        itemList.add("尼尔的机械纪元");
        itemList.add("街头霸王");
        itemList.add("银河护卫队");
        itemList.add("星球大战");
        itemList.add("蝙蝠侠：黑暗骑士的崛起");
        itemList.add("杀死比尔");
        itemList.add("我是传奇");
        itemList.add("魔兽");
        itemList.add("王者荣耀");
        itemList.add("塞尔达传说");
        itemList.add("喷射战士");
        itemList.add("生化危机");
        itemList.add("尼尔的机械纪元");
        itemList.add("街头霸王");
        itemList.add("银河护卫队");
        itemList.add("星球大战");
        itemList.add("蝙蝠侠：黑暗骑士的崛起");
        itemList.add("杀死比尔");
    }

}
