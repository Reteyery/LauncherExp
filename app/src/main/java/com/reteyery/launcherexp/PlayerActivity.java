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
import butterknife.ButterKnife;
import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.entity.Edition;
import fm.qingting.qtsdk.player.QTPlayer;

import static com.reteyery.launcherexp.DetailListActivity.CHANNEL_ID;

public class PlayerActivity extends BaseActivity implements QTPlayer.StateChangeListener {
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

        if(sizeProgram <= 0){
            Toast.makeText(this, "播放列表为空", Toast.LENGTH_SHORT).show();
            checkIndex = false;
        }else if (currentIndex > sizeProgram - 1){
            currentIndex = sizeProgram - 1;
            Toast.makeText(this, "已经是最后一首", Toast.LENGTH_SHORT).show();
            checkIndex = false;
        }else if (currentIndex < 0){
            currentIndex = 0;
            Toast.makeText(this, "已经是第一首", Toast.LENGTH_SHORT).show();
            checkIndex = false;
        }else {
            checkIndex = true;
        }
        radioPlay(checkIndex);
    }

    private void radioPlay(boolean checkIndex) {
        if (checkIndex && null != editions.get(currentIndex)){
            qtPlay.prepare(editions.get(currentIndex));
        }
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
