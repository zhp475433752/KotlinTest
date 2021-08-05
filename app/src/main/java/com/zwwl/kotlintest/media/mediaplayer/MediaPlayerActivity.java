package com.zwwl.kotlintest.media.mediaplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zwwl.kotlintest.R;
import com.zwwl.kotlintest.notification.NotificationActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MediaPlayer播放示例
 *
 * @author hpzhang
 * 2021年08月03日19:01:45
 */
public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnErrorListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnInfoListener, OnPlayItemClickListener {

    private static final String TAG = "MediaPlayerActivity";

    private final String url = "https://www.apple.com/105/media/us/iphone-x/2017/01df5b43-28e4-4848-bf20-490c34a926a7/films/feature/iphone-x-feature-tpl-cc-us-20170912_1280x720h.mp4";
    private final String url2 = "https://v-cdn.zjol.com.cn/276982.mp4";
    private final String url3 = "https://v-cdn.zjol.com.cn/276984.mp4";
    private final String url4 = "https://v-cdn.zjol.com.cn/276986.mp4";
    private final String url5 = "https://v-cdn.zjol.com.cn/276987.mp4";
    private final String url6 = "https://v-cdn.zjol.com.cn/277000.mp4";

    private SurfaceView surfaceView;
    private ImageView btnPlay;
    private TextView tvCurrentTime, tvTotalTime;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private View bottomLayout;
    private int seekBarDragProgress;
    private ProgressBar loading;

    private boolean isBack = false, isPlayComplete = false;
    private int durationMilSec;//视频总长度：毫秒
    private final int MSG_WHAT_UPDATE = 1, MSG_WHAT_NOTIFICATION = 2;

    private RecyclerView recyclerView;
    private List<PlayBean> data;
    private PlayAdapter adapter;
    private int currentPosition = 0;//当前播放列表索引


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        initView();
    }

    private void initView() {
        surfaceView = findViewById(R.id.surfaceView);
        btnPlay = findViewById(R.id.play);
        seekBar = findViewById(R.id.seekBar);
        tvCurrentTime = findViewById(R.id.currentTime);
        tvTotalTime = findViewById(R.id.totalTime);
        loading = findViewById(R.id.loading);
        recyclerView = findViewById(R.id.recyclerView);
        bottomLayout = findViewById(R.id.bottomLayout);

        btnPlay.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);

        data = new ArrayList<>();
        data.add(new PlayBean(url, "测试视频 - 1"));
        data.add(new PlayBean(url2, "测试视频 - 2"));
        data.add(new PlayBean(url3, "测试视频 - 3"));
        data.add(new PlayBean(url4, "测试视频 - 4"));
        data.add(new PlayBean(url5, "测试视频 - 5"));
        data.add(new PlayBean(url6, "测试视频 - 6"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new PlayAdapter(data, this);
        adapter.setCurrentPosition(currentPosition);
        recyclerView.setAdapter(adapter);


        mediaPlayer = new MediaPlayer();
        mediaPlayer.setScreenOnWhilePlaying(true);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnInfoListener(this);//loading

        try {
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback2() {
            @Override
            public void surfaceRedrawNeeded(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d(TAG, "surfaceCreated");
                mediaPlayer.setDisplay(holder);//当surface创建完成后在给播放器设置画面，否则会有异常
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        handler.sendEmptyMessageDelayed(MSG_WHAT_NOTIFICATION, 2000);
    }

    private void prepare() throws IOException {
        try {
            mediaPlayer.setDataSource(data.get(currentPosition).getUrl());
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.play) {
            if (isPlayComplete) {
                try {
                    adapter.setCurrentPosition(currentPosition);
                    prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        }
    }

    /**
     * 点击播放某一个视频
     *
     * @param playBean
     */
    @Override
    public void onItemClick(PlayBean playBean, int position) {
        mediaPlayer.reset();
        this.currentPosition = position;
        adapter.setCurrentPosition(currentPosition);
        try {
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
//            Log.d(TAG, "handleMessage --------------- ");
            if (msg.what == MSG_WHAT_UPDATE) {
                // 在此更新seekBar和播放时间
                int currentPosition = mediaPlayer.getCurrentPosition();
                int progress = (int) ((currentPosition / (float) durationMilSec) * 100);
                if (progress < 0) {
                    progress = 0;
                }
                if (progress > 100) {
                    progress = 100;
                }
                seekBar.setProgress(progress);
                tvCurrentTime.setText(String.valueOf(currentPosition / 1000));
                handler.sendEmptyMessageDelayed(MSG_WHAT_UPDATE, 1000);
            } else if (msg.what == MSG_WHAT_NOTIFICATION) {
                sendNotification();
            }
            return false;
        }
    });

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(TAG, "播放完成");
        isPlayComplete = true;
        handler.removeCallbacksAndMessages(null);
        tvCurrentTime.setText(String.valueOf(0));
        seekBar.setProgress(0);
        seekBar.setSecondaryProgress(0);
        btnPlay.setImageResource(android.R.drawable.ic_media_play);
        mp.reset();
        currentPosition++;
        if (currentPosition >= data.size()) {
            Toast.makeText(this, "列表播放完毕！", Toast.LENGTH_LONG).show();
            currentPosition = 0;
            adapter.setCurrentPosition(-1);
            return;
        }
        try {
            adapter.setCurrentPosition(currentPosition);
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
//        Log.d(TAG, "缓冲进度 = " + percent);
        seekBar.setSecondaryProgress(percent);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
        btnPlay.setImageResource(android.R.drawable.ic_media_pause);
        int currentSec = 0;
        durationMilSec = mediaPlayer.getDuration();
        Log.d(TAG, "onPrepared - duration - " + durationMilSec);
        tvCurrentTime.setText(String.valueOf(currentSec));
        tvTotalTime.setText(String.valueOf(durationMilSec / 1000));
        handler.sendEmptyMessageDelayed(MSG_WHAT_UPDATE, 1000);
        isPlayComplete = false;
        bottomLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 判断是否在缓冲以及恢复
     *
     * @param mp
     * @param what
     * @param extra
     * @return
     */
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "onInfo = what = " + what + " ,extra = " + extra);
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                Log.d(TAG, "onInfo = loading...");
                loading.setVisibility(View.VISIBLE);
                break;
            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                Log.d(TAG, "onInfo = loading - 消失...");
                loading.setVisibility(View.GONE);
                break;
        }
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        Log.d(TAG, "onSeekComplete");
        tvCurrentTime.setText(String.valueOf(mp.getCurrentPosition() / 1000));
        handler.sendEmptyMessageDelayed(MSG_WHAT_UPDATE, 1000);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "onError = what = " + what + " extra = " + extra);
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (isBack && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            btnPlay.setImageResource(android.R.drawable.ic_media_pause);
            isBack = false;
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btnPlay.setImageResource(android.R.drawable.ic_media_play);
        }

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        isBack = true;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    /**
     * @param seekBar
     * @param progress 0-100
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        Log.d(TAG, "onProgressChanged -- progress - " + progress + ",fromUser - " + fromUser);
        seekBarDragProgress = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.d(TAG, "onStartTrackingTouch");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.d(TAG, "onStopTrackingTouch --- 拖动结束 ---  " + seekBarDragProgress);
        handler.removeMessages(MSG_WHAT_UPDATE);
        int msec = (int) (durationMilSec * (seekBarDragProgress / 100d));
        mediaPlayer.seekTo(msec);
    }

    /**
     * 发送通知
     */
    private void sendNotification() {
        String channel_id = "channel_1000000";
        String channel_name = "channel_名称";
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        intent.putExtra("data", "这是MediaPlayer页面正在播放视频时发送的通知");
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 100, intent, PendingIntent.FLAG_ONE_SHOT);
        Notification notification;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, channel_name, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
            Notification.Builder builder = new Notification.Builder(getApplicationContext(), channel_id);
            builder.setContentIntent(pendingIntent)
                    .setContentTitle("这是测试title")
                    .setContentText("您正在观看视频")
                    .setSmallIcon(R.mipmap.ic_launcher);
            notification = builder.build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;//点击之后就消失，默认不消失
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id);
            builder.setContentIntent(pendingIntent)
                    .setContentTitle("这是测试title")
                    .setContentText("您正在观看视频")
                    .setSmallIcon(R.mipmap.ic_launcher);
            notification = builder.build();
        }
        notificationManager.notify(999, notification);
    }


}