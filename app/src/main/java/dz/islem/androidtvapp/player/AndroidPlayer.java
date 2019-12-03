package dz.islem.androidtvapp.player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;

public class AndroidPlayer {
    private Context mContext;
    private PlayerView mPlayerView;
    private Uri currUri;
    private ExoPlayer player = null;
    private long playbackPosition = 0;
    private int currentWindowIndex =0;
    public Boolean playWhenReady = true;


    public AndroidPlayer(Context mContext, PlayerView mPlayerView){
        this.mContext = mContext;
        this.mPlayerView = mPlayerView;
    }

    //Play single video
    public void play(Uri uri){
        if(uri != null) currUri = uri;
        initPlayer();
        preparePlayer(currUri);
        player.setPlayWhenReady(playWhenReady);
        hideSystemUi();
    }

    private void initPlayer(){
        if(player == null){
            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(mContext),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl());
            loadState();
            mPlayerView.setPlayer(player);
        }else{
            loadState();
        }
    }

    //Build MediaSource for one video and prepare player
    private void preparePlayer(Uri uri){
        MediaSource mediaSource = new MediaSourceBuilder().build(uri);
        player.prepare(mediaSource, true, false);
    }


    public void saveState(){
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindowIndex = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
        }
    }

    public void loadState(){
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindowIndex, playbackPosition);
    }

    public void releasePlayer() {
        if (player != null) {
            saveState();
            player.release();
            player = null;
        }
    }


    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
        | View.SYSTEM_UI_FLAG_FULLSCREEN
        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


}
