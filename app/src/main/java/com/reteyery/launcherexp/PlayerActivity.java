package com.reteyery.launcherexp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.reteyery.launcherexp.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.entity.Edition;
import fm.qingting.qtsdk.player.QTPlayer;

import static com.reteyery.launcherexp.DetailListActivity.CHANNEL_ID;

public class PlayerActivity extends BaseActivity implements QTPlayer.StateChangeListener, View.OnClickListener {
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_program_index)
    TextView tvProgramIndex;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.btn_pre)
    ImageButton btnPre;
    @BindView(R.id.btn_play)
    ImageButton btnPlay;
    @BindView(R.id.btn_pause)
    ImageButton btnPause;
    @BindView(R.id.btn_next)
    ImageButton btnNext;

    ArrayList<Edition> editions=new ArrayList<>();
    boolean checkIndex = false;
    int currentIndex = 0;
    QTPlayer qtPlay;

    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_play, null);
    }

    @Override
    protected void initData() {
        btnPre.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        Intent intent = getIntent();
        if (null != intent){
            editions = (ArrayList<Edition>) intent.getSerializableExtra(CHANNEL_ID);
        }
    }

    @Override
    protected void initOperation() {
        int sizeProgram = editions.size();
        qtPlay = QTSDK.getPlayer();
        qtPlay.addListener(this);

        radioPlay(checkIndexStatus(sizeProgram));
    }

    private boolean checkIndexStatus(int sizeProgram){
        if(sizeProgram <= 0){
            Toast.makeText(this, "播放列表为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (currentIndex > sizeProgram - 1){
            currentIndex = sizeProgram - 1;
            Toast.makeText(this, "已经是最后一首", Toast.LENGTH_SHORT).show();
            return false;
        }else if (currentIndex < 0){
            currentIndex = 0;
            Toast.makeText(this, "已经是第一首", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
    private void radioPlay(boolean checkIndex) {
        if (checkIndex && null != editions.get(currentIndex)){
            qtPlay.prepare(editions.get(currentIndex));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pre:
                playPre();
                break;
            case R.id.btn_play:

                break;
            case R.id.btn_pause:

                break;
            case R.id.btn_next:
                playNext();
                break;
        }
    }

    /**
     * sdk中的demo有问题，无法确定当前播放的是第几个电台节目，所以上一首下一首是无法使用的
     * 默认进来的index是0，无论从中间哪一首播放，点击上一首直接就会显示是第一首， bug
     */
    void playPre(){
        --currentIndex;
        if (currentIndex >= 0)
            qtPlay.prepare(editions.get(currentIndex));
        else
            Toast.makeText(this, "已经是第一首", Toast.LENGTH_SHORT).show();
    }

    void playNext(){
        ++currentIndex;
        if (currentIndex < editions.size() - 1)
            qtPlay.prepare(editions.get(currentIndex));
        else
            Toast.makeText(this, "已经是最后一首", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlayStateChange(int i) {

    }

    @Override
    public void onPlayProgressChange(int i) {

    }

    @Override
    public void onPlayDurationChange(int i) {

    }

}
