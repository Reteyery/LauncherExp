package com.reteyery.launcherexp.test.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchMovieActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search_bar)
    LinearLayout llSearchBar;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ll_search_recommend)
    LinearLayout llSearchRecommend;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ll_search_result)
    LinearLayout llSearchResult;

    List<String> searchList = new ArrayList<>();

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_media_search, null);
    }

    @Override
    protected void initOperation() {
        etSearch.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        searchList.add("最近更新");
        searchList.add("最高人气");
        searchList.add("最受好评");
        searchList.add("最热动漫");
        searchList.add("最热武侠");
        searchList.add("最火偶像");
        searchList.add("最受好评");
        searchList.add("最热动漫");
        searchList.add("最热武侠");
        searchList.add("最火偶像");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_search:
                llSearchResult.setVisibility(View.GONE);
                showPopupWindow();
                break;
        }
    }

    private void showPopupWindow() {
        View contentView= View.inflate(this,  R.layout.popupwindow_search, null);
        RecyclerView recyclerView = contentView.findViewById(R.id.rv_content);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        SearchRecommondListAdapter adapter = new SearchRecommondListAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setRecommendList(searchList);
        adapter.notifyDataSetChanged();
        PopupWindow window=new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, 450, false);
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAsDropDown(ivSearch);
    }

    private class SearchRecommendListHolder extends RecyclerView.ViewHolder{
        protected TextView tvSearchItem;
        SearchRecommendListHolder(View itemView) {
            super(itemView);
            tvSearchItem = itemView.findViewById(R.id.tv_search_text);
        }
    }
    private class SearchRecommondListAdapter extends RecyclerView.Adapter<SearchRecommendListHolder>{
        List<String> recommendList = new ArrayList<>();
        @NonNull
        @Override
        public SearchRecommendListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SearchRecommendListHolder(View.inflate(parent.getContext(), R.layout.item_search_list, null));
        }

        @Override
        public void onBindViewHolder(@NonNull SearchRecommendListHolder holder, int position) {
            holder.tvSearchItem.setText(recommendList.get(position).substring(0, 4));
        }

        @Override
        public int getItemCount() {
            return null == recommendList ? 0 : recommendList.size();
        }

        void setRecommendList(List<String> recommendList) {
            this.recommendList = recommendList;
        }
    }
}
