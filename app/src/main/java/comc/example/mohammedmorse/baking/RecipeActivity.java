package comc.example.mohammedmorse.baking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Interface.StepsClickListener;
import comc.example.mohammedmorse.baking.Retrofit.StepsModel;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;

public class RecipeActivity extends AppCompatActivity implements StepsClickListener{
    android.support.v7.widget.Toolbar toolbar;
    TotalJsonDataModel Data;
    boolean boolFrag;
    int Id;
    SharedPreferences preferences;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        toolbar=findViewById(R.id.CustomToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        Intent intent=getIntent();
        preferences =getSharedPreferences("Navigation",MODE_PRIVATE);
        Bundle bundle=intent.getExtras();
        Data= (TotalJsonDataModel) bundle.getSerializable("ObjectData");

        if((Data.getId()-1)==0){
            toolbar.setBackgroundColor(getResources().getColor(R.color.Item1more));
        }
        else if((Data.getId()-1)==1){
            toolbar.setBackgroundColor(getResources().getColor(R.color.Item2more));
        }
        else if((Data.getId()-1)==2){
            toolbar.setBackgroundColor(getResources().getColor(R.color.Item3more));
        }
        else if((Data.getId()-1)==3){
            toolbar.setBackgroundColor(getResources().getColor(R.color.Item4more));
        }
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        DetailFragment detailFragment=new DetailFragment();
        Bundle MyBundle=new Bundle();
        MyBundle.putSerializable("DataObjectToDetailFrag",Data);
        detailFragment.setArguments(MyBundle);
        transaction.add(R.id.DetailFragment,detailFragment);
        transaction.commit();

        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("Position",1);
        editor.apply();
        Log.i("Morse","Recipe Activity on Create "+Data.getName());
        toolbar.setTitle(Data.getName());
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_navigate_next_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Position=preferences.getInt("Position",-1);
                if(Position==1) {
                    finish();
                }
                else if(Position==2){
                    FragmentManager manager=getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    DetailFragment detailFragment=new DetailFragment();
                    Bundle MyBundle=new Bundle();
                    MyBundle.putSerializable("DataObjectToDetailFrag",Data);
                    detailFragment.setArguments(MyBundle);
                    transaction.replace(R.id.DetailFragment,detailFragment);
                    transaction.commit();
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putInt("Position",1);
                    editor.apply();
                }
                else
                {
                    Toast.makeText(RecipeActivity.this, "-1", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //PostData(Data);
    }

    private void PostData(TotalJsonDataModel data) {
        EventBus.getDefault().post(Data);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(int Id) {
        this.Id=Id;
        Toast.makeText(this, "Step Clicked", Toast.LENGTH_SHORT).show();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        StepFragment detailFragment=new StepFragment();
        Bundle MyBundle=new Bundle();
        MyBundle.putSerializable("DataObjectToStepFrag",Data.getStepsList().get(Id));
        MyBundle.putInt("Position",Data.getId());
        detailFragment.setArguments(MyBundle);
        transaction.replace(R.id.DetailFragment,detailFragment);
        transaction.commit();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("Position",2);
        editor.apply();
        boolFrag=true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("boolFrag",boolFrag);
        outState.putInt("Id",Id);
        outState.putSerializable("Data",Data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolFrag=savedInstanceState.getBoolean("boolFrag");
        if(boolFrag==true){
            Data= (TotalJsonDataModel) savedInstanceState.getSerializable("Data");
            Id=savedInstanceState.getInt("Id");
           FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            StepFragment detailFragment=new StepFragment();
            Bundle MyBundle=new Bundle();
            MyBundle.putSerializable("DataObjectToStepFrag",Data.getStepsList().get(Id));
            MyBundle.putInt("Position",Data.getId());
            detailFragment.setArguments(MyBundle);
            transaction.replace(R.id.DetailFragment,detailFragment);
            transaction.commitNow();
            Log.i("Morse", "onRestoreInstanceState: Recipe Act < cREATE Step Frag >");
        }
    }
}
