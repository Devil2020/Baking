package comc.example.mohammedmorse.baking.Exoplayer;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by Mohammed Morse on 16/08/2018.
 */

public class ExoplayerHelper {
    SimpleExoPlayerView View ;
    SimpleExoPlayer player;
    Context context;
    TrackSelector selector;
    LoadControl loadControl;
    MediaSource mediaSource;
    public ExoplayerHelper(Context context,SimpleExoPlayerView simpleExoPlayerView){
        View=simpleExoPlayerView;
        this.context=context;
    }
    public SimpleExoPlayer CreateExoPlayer(){

        selector=new DefaultTrackSelector();
        loadControl=new DefaultLoadControl();
        player= ExoPlayerFactory.newSimpleInstance(context,selector,loadControl);
        return player;
    }
   public MediaSource PrepareMediaSource(Uri uri){
        mediaSource=new ExtractorMediaSource(uri,
                new DefaultDataSourceFactory(context, Util.getUserAgent(context,"Baking"))
                        ,new DefaultExtractorsFactory(),null,null);
        return mediaSource;
   }
}
