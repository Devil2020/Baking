package comc.example.mohammedmorse.baking.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Exoplayer.ExoplayerHelper;
import comc.example.mohammedmorse.baking.R;
import comc.example.mohammedmorse.baking.Retrofit.StepsModel;

/**
 * Created by Mohammed Morse on 13/08/2018.
 */

public class StepsPageAdapter extends PagerAdapter {
   Context context;
   ArrayList<StepsModel> list;
   ExoplayerHelper exoplayerHelper;
    public SimpleExoPlayerView playerView;
   public StepsPageAdapter(Context context,ArrayList<StepsModel>list){
       this.context=context;
       this.list=list;

   }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view=inflater.inflate(R.layout.steps_layout,container,false);
        TextView ShortStep , LongStep;
        LongStep=view.findViewById(R.id.LongStep);
        LongStep.setText(list.get(position).getDescribtion());
         playerView=view.findViewById(R.id.MediaController);
        exoplayerHelper=new ExoplayerHelper(context,playerView);
        SimpleExoPlayer player=exoplayerHelper.CreateExoPlayer();
        playerView.setPlayer(player);
        player.setPlayWhenReady(true);
        MediaSource mediaSource=exoplayerHelper.PrepareMediaSource(Uri.parse(list.get(position).getVideoUrl()));
        player.prepare(mediaSource);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(LinearLayout)object;
    }
}
