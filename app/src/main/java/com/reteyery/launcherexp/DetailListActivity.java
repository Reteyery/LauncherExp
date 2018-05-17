package com.reteyery.launcherexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.buss.adapter.RadioListAdapter;
import com.reteyery.launcherexp.widget.PlayerSeekBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.qingting.qtsdk.QTException;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.callbacks.QTCallback;
import fm.qingting.qtsdk.entity.Channel;
import fm.qingting.qtsdk.entity.ChannelProgram;
import fm.qingting.qtsdk.entity.Edition;
import fm.qingting.qtsdk.entity.QTListEntity;

/**
 * 栏目详情列表
 */
public class DetailListActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.cover)
    ImageView imageView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.play_seek)
    PlayerSeekBar playSeek;
    @BindView(R.id.iv_previous)
    ImageView ivPrevious;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.iv_next)
    ImageView ivNext;

    RadioListAdapter listAdapter;
    int channelId;
    public final static String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_detail_list, null);
    }

    @Override
    protected void initData() {
        channelId = getIntent().getIntExtra(CHANNEL_ID, 0);
        if (channelId == 0) {
            return;
        }
        listAdapter = new RadioListAdapter<ChannelProgram>() {

            @Override
            public void bindData(SimpleHolder holder, ChannelProgram object) {
                holder.tvTitle.setText(object.getTitle());
                holder.mConstraintLayout.setOnClickListener(v -> QTSDK.requestProgramUrl(channelId, object.getId(), (result, e) -> {
                    if (e == null) {
                        ArrayList<Edition> editions = new ArrayList<>();
                        editions.addAll(result.getEditions());
                        Intent intent = new Intent(DetailListActivity.this, PlayerActivity.class);
                        intent.putExtra(CHANNEL_ID, editions);
                        DetailListActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(DetailListActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(listAdapter);
        requestChannelDetails(channelId);
        requestChannelPrograms(channelId);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initOperation() {
        ivPlay.setOnClickListener(this);
        ivPrevious.setOnClickListener(this);
        ivNext.setOnClickListener(this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
