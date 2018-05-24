package com.reteyery.launcherexp;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.reteyery.launcherexp.base.BaseActivity;
import com.reteyery.launcherexp.buss.adapter.RadioListAdapter;
import com.reteyery.launcherexp.widget.PlayerSeekBar;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.entity.ChannelProgram;
import fm.qingting.qtsdk.entity.Edition;
import fm.qingting.qtsdk.player.QTPlayer;

/**
 * 栏目详情列表
 */
public class DetailListActivity extends BaseActivity implements QTPlayer.StateChangeListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.cover)
    ImageView imageView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.play_seek)
    PlayerSeekBar seekbar;
    @BindView(R.id.iv_previous)
    ImageButton ivPrevious;
    @BindView(R.id.iv_play)
    ImageButton ivPlay;
    @BindView(R.id.iv_pause)
    ImageButton ivPause;
    @BindView(R.id.iv_next)
    ImageButton ivNext;


    RadioListAdapter listAdapter;
    int channelId, currentIndex = 0;
    public final static String CHANNEL_ID = "CHANNEL_ID";
    ArrayList<Edition> editions = new ArrayList<>();

    boolean isSeeking = false;
    QTPlayer qtPlay;

    int oldPosId = -1;
    @SuppressLint("UseSparseArrays")
    TreeMap<Integer, Integer> clickMap = new TreeMap<>();

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_detail_list, null);
    }

    @Override
    protected void initData() {
        qtPlay = QTSDK.getPlayer();
        qtPlay.addListener(this);
        seekbar.setOnSeekBarChangeListener(this);
        channelId = getIntent().getIntExtra(CHANNEL_ID, 0);
        if (channelId == 0) {
            return;
        }
        listAdapter = new RadioListAdapter<ChannelProgram>() {

            @Override
            public void bindData(SimpleHolder holder, ChannelProgram channelProgram) {
                holder.tvTitle.setText(channelProgram.getTitle());
                AnimationDrawable animationDrawable = (AnimationDrawable) holder.ivPlay.getDrawable();
                holder.mConstraintLayout.setOnClickListener(v -> QTSDK.requestProgramUrl(channelId, channelProgram.getId(), (result, e) -> {
                    if (e == null) {
                        //动画播放显示逻辑， treeMap保存k,v,初始 k=v,点击后k!=v,如果点击的是新一首，旧k的数据设置为k==v
                        for (Map.Entry<Integer, Integer> entry: clickMap.entrySet()){
                            if (!entry.getKey().equals(entry.getValue())){
                                oldPosId = entry.getKey();
                            }
                        }
                        if(oldPosId != -1){
                            if (oldPosId != holder.getAdapterPosition()){
                                clickMap.put(oldPosId, oldPosId);
                                clickMap.put(holder.getAdapterPosition(), holder.getAdapterPosition() + 100);
                                oldPosId = holder.getAdapterPosition();
                            }
                        }else {
                            clickMap.put(holder.getAdapterPosition(), holder.getAdapterPosition() + 100);
                            oldPosId = holder.getAdapterPosition();
                        }
                        listAdapter.notifyDataSetChanged();
                        editions = new ArrayList<>(result.getEditions());
                        //播放当前选中的电台节目
                        initQTPlay();
                    } else {
                        Toast.makeText(DetailListActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }));
                //控制动画互斥显示
                if (clickMap.get(holder.getAdapterPosition()).equals(holder.getAdapterPosition())){
                    holder.ivPlay.setVisibility(View.INVISIBLE);
                    animationDrawable.stop();
                }else {
                    animationDrawable.start();
                    holder.ivPlay.setVisibility(View.VISIBLE);
                }
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(listAdapter);
        requestChannelDetails(channelId);
        requestChannelPrograms(channelId);
    }

    private void initQTPlay() {
        int sizeProgram = editions.size();
        radioPlay(checkIndexStatus(sizeProgram));
    }

    /**
     * int NONE = -1;
     * int LOADING = 0;
     * int PLAYING = 1;
     * int PAUSED = 2;
     * int ERROR = 3;
     * int EOF = 4;
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_previous:
                playPre();
                break;
            case R.id.iv_play:
                ivPlay.setVisibility(View.GONE);
                ivPause.setVisibility(View.VISIBLE);
                switch (qtPlay.getState()) {
                    case -1:
                        //无状态就从头开始播放
                        if (null != editions && editions.size() > 0){
                            qtPlay.prepare(editions.get(0));
                        }
                        break;
                    case 2:
                        qtPlay.play();
                        break;
                }
                break;
            case R.id.iv_pause:
                ivPause.setVisibility(View.GONE);
                ivPlay.setVisibility(View.VISIBLE);
                qtPlay.pause();
                break;
            case R.id.iv_next:
                playNext();
                break;
        }
    }

    @Override
    protected void initOperation() {
        ivPlay.setOnClickListener(this);
        ivPrevious.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivPause.setOnClickListener(this);
    }

    private void requestChannelDetails(int channelId) {
        QTSDK.requestChannelOnDemand(channelId, (result, e) -> {
            if (e == null) {
                title.setText(result.getTitle());
                Glide.with(DetailListActivity.this.getBaseContext())
                        .load(result.getThumbs().getMediumThumb())
                        .into(imageView);
            }
        });
    }

    private void requestChannelPrograms(int channelId) {
        QTSDK.requestChannelOnDemandProgramList(channelId, 1, (result, e) -> {
            if (e == null) {
                listAdapter.items = result.getData();
                listAdapter.notifyDataSetChanged();

                for(int i = 0; i < result.getData().size(); i++){
                    clickMap.put(i, i);
                }
            }
        });
    }

    private boolean checkIndexStatus(int sizeProgram) {
        if (sizeProgram <= 0) {
            Toast.makeText(this, R.string.play_list_null, Toast.LENGTH_SHORT).show();
            return false;
        } else if (currentIndex > sizeProgram - 1) {
            currentIndex = sizeProgram - 1;
            Toast.makeText(this, R.string.already_is_first, Toast.LENGTH_SHORT).show();
            return false;
        } else if (currentIndex < 0) {
            currentIndex = 0;
            Toast.makeText(this, R.string.already_is_last, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void radioPlay(boolean checkIndex) {
        if (checkIndex && null != editions.get(currentIndex)) {
            ivPause.setVisibility(View.VISIBLE);
            ivPlay.setVisibility(View.GONE);
            qtPlay.prepare(editions.get(currentIndex));
        }
    }

    /**
     * sdk中的demo有问题，无法确定当前播放的是第几个电台节目，所以上一首下一首是无法使用的
     * 默认进来的index是0，无论从中间哪一首播放，点击上一首直接就会显示是第一首， bug
     */
    void playPre() {
        if (currentIndex > 0){
            --currentIndex;
            qtPlay.prepare(editions.get(currentIndex));
        }
        else
            Toast.makeText(this, R.string.already_is_first, Toast.LENGTH_SHORT).show();
    }

    void playNext() {
        if (currentIndex < editions.size() - 1){
            ++currentIndex;
            Logger.i(currentIndex + "");
            qtPlay.prepare(editions.get(currentIndex));
        }
        else
            Toast.makeText(this, R.string.already_is_last, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isSeeking = false;
        qtPlay.seekTo(seekbar.getProgress());
    }

    @Override
    public void onPlayStateChange(int i) {
        if (i == QTPlayer.PlayState.EOF) {
            playNext();
        }
    }

    @Override
    public void onPlayProgressChange(int i) {
        if (!isSeeking) {
            seekbar.setProgress(i);
        }
    }

    @Override
    public void onPlayDurationChange(int i) {
        seekbar.setMax(i);
    }

}
