package com.reteyery.launcherexp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.buss.adapter.SimpleAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import fm.qingting.qtsdk.QTException;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.callbacks.QTCallback;
import fm.qingting.qtsdk.entity.Channel;
import fm.qingting.qtsdk.entity.ChannelProgram;
import fm.qingting.qtsdk.entity.Edition;
import fm.qingting.qtsdk.entity.QTListEntity;

public class DetailListActivity extends BaseActivity {
    @BindView(R.id.cover)
    ImageView imageView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.url)
    TextView url;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    SimpleAdapter listAdapter;
    int channelId;
    public final static String CHANNEL_ID = "channel_id";

    @Override
    public int getLayoutContentViewID() {
        return R.layout.activity_detail_list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        channelId = getIntent().getIntExtra(CHANNEL_ID, 0);
        if (channelId == 0) {
            return;
        }
        title = findViewById(R.id.title);
        url = findViewById(R.id.url);
        imageView = findViewById(R.id.cover);
        recyclerView = findViewById(R.id.list);
        listAdapter = new SimpleAdapter<ChannelProgram>() {
            @Override
            public void bindData(SimpleHolder holder, ChannelProgram object) {
                holder.mTextView.setText(object.getTitle());
                if (object.getThumbs() != null) {
                    Glide.with(holder.itemView.getContext()).load(object.getThumbs().getMediumThumb()).into(holder.mImageView);
                }
                holder.mLinearLayout.setOnClickListener(v -> QTSDK.requestProgramUrl(channelId, object.getId(), (result, e) -> {
                    if (e == null) {
                        url.setText("播放地址：" + result.getEditions().get(0).getUrl().get(0));
                        ArrayList<Edition> editions=new ArrayList<>();
                        editions.addAll(result.getEditions());
//                        PlayerActivity.Companion.start(DetailsActivity.this,editions);
                    }else{
                        Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(listAdapter);
        requestChannelDetails(channelId);
        requestChannelPrograms(channelId);
    }

    private void requestChannelDetails(int channelId) {
        QTSDK.requestChannelOnDemand(channelId, new QTCallback<Channel>() {
            @Override
            public void done(Channel result, QTException e) {
                if (e == null) {
                    title.setText(result.getTitle());
                    Glide.with(getBaseContext())
                            .load(result.getThumbs().getMediumThumb())
                            .into(imageView);
                }
            }
        });
    }

    private void requestChannelPrograms(int channelId) {
        QTSDK.requestChannelOnDemandProgramList(channelId, 1, new QTCallback<QTListEntity<ChannelProgram>>() {
            @Override
            public void done(QTListEntity<ChannelProgram> result, QTException e) {
                if (e == null) {
                    listAdapter.items = result.getData();
                    listAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
