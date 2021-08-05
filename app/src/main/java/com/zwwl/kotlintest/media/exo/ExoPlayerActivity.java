package com.zwwl.kotlintest.media.exo;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.Util;
import com.zwwl.kotlintest.R;

/**
 * @author hpzhang
 * ExoPlayer示例
 * 2021年08月05日14:26:29
 */
public class ExoPlayerActivity extends AppCompatActivity {

    private static final String DEFAULT_MEDIA_URI =
            "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv";

    @Nullable
    private PlayerView playerView;
    @Nullable
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);
        playerView = findViewById(R.id.player_view);
        initializePlayer();
    }

    private void initializePlayer() {
        Uri uri = Uri.parse(DEFAULT_MEDIA_URI);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this);
        MediaSource mediaSource;
        @C.ContentType int type = Util.inferContentType(uri);
        if (type == C.TYPE_DASH) {
            mediaSource =
                    new DashMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(MediaItem.fromUri(uri));
        } else if (type == C.TYPE_OTHER) {
            mediaSource =
                    new ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(MediaItem.fromUri(uri));
        } else {
            throw new IllegalStateException();
        }

        DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
        trackSelector.setParameters(new DefaultTrackSelector.ParametersBuilder(this));

        player = new SimpleExoPlayer.Builder(this, new DefaultRenderersFactory(this))
                .setMediaSourceFactory(new DefaultMediaSourceFactory(dataSourceFactory))
                .setTrackSelector(trackSelector)
                .build();
        player.addAnalyticsListener(new EventLogger(trackSelector));
        player.setAudioAttributes(AudioAttributes.DEFAULT, true);
        player.setPlayWhenReady(true);
        Assertions.checkNotNull(playerView).setPlayer(player);
        player.setMediaSource(mediaSource);
        player.prepare();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        assert player != null;
        player.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        assert player != null;
        player.pause();
        releasePlayer();
    }

    private void releasePlayer() {
        Assertions.checkNotNull(playerView).setPlayer(null);
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        }
    }
}