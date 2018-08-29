package comc.example.mohammedmorse.baking;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import comc.example.mohammedmorse.baking.Exoplayer.ExoplayerHelper;
import comc.example.mohammedmorse.baking.Retrofit.StepsModel;

public class StepFragment extends Fragment {
   StepsModel Data;
   SimpleExoPlayerView exoPlayerView;
   SimpleExoPlayer player;
   TextView LongDesc;
   SharedPreferences preferences;
    ExoplayerHelper exoplayerHelper;
   LinearLayout StepLayoutl;
    int Position;
    long playPosition=0;
    public StepFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         preferences=getContext().getSharedPreferences("Player",Context.MODE_PRIVATE);
        Log.i("Morse", "onCreate: Step Fragment");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_step, container, false);
        exoPlayerView=view.findViewById(R.id.SimpleExoPlayerView);
        LongDesc=view.findViewById(R.id.StepDescription);
        StepLayoutl=view.findViewById(R.id.StepsLayout);
        Data= (StepsModel) getArguments().getSerializable("DataObjectToStepFrag");
        Position=getArguments().getInt("Position");
        exoplayerHelper=new ExoplayerHelper(getContext(),exoPlayerView);


        return view;
    }

    private void MakeUi(StepsModel data) {
        if((Position-1)==0){
            StepLayoutl.setBackgroundColor(getResources().getColor(R.color.Item1));
        }
        else if((Position-1)==1){StepLayoutl.setBackgroundColor(getResources().getColor(R.color.Item2));}
        else if((Position-1)==2){StepLayoutl.setBackgroundColor(getResources().getColor(R.color.Item3));}
        else if((Position-1)==3){StepLayoutl.setBackgroundColor(getResources().getColor(R.color.Item4));}
        if(preferences.getLong("Posit"+Data.getDescribtion(),0)!=0) {
            playPosition = preferences.getLong("Posit"+Data.getDescribtion(), 0);
            Log.i("Morse", "get from Preference : "+playPosition);
        }
        LongDesc.setText(data.getDescribtion());
        Toast.makeText(getContext(), ""+data.getDescribtion(), Toast.LENGTH_SHORT).show();
        player=exoplayerHelper.CreateExoPlayer();
        exoPlayerView.setPlayer(player);
        player.seekTo(playPosition);
        Log.i("Morse", "MakeUi: "+playPosition);
        Log.i("Morse","Player Setting")      ;
        MediaSource mediaSource=exoplayerHelper.PrepareMediaSource(Uri.parse(data.getVideoUrl()));
        player.setPlayWhenReady(true);
        player.prepare(mediaSource);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            playPosition=savedInstanceState.getLong("playPosition");
            SharedPreferences.Editor editor= preferences.edit();
            editor.putLong("Posit"+Data.getDescribtion(),playPosition);
            editor.commit();
            Log.i("Morse", "onRestoreInstanceState: "+playPosition);
        }
        MakeUi(Data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onPause() {
        super.onPause();
        player.stop();
    player.release();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        playPosition=player.getCurrentPosition();
    outState.putLong("playPosition",playPosition);
        Log.i("Morse", "onSaveInstanceState: "+playPosition);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
