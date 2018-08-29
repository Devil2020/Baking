package comc.example.mohammedmorse.baking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Interface.StepsClickListener;
import comc.example.mohammedmorse.baking.Retrofit.StepsModel;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;

public class SolutionActivity extends AppCompatActivity implements StepsClickListener{
        android.support.v7.widget.Toolbar toolbar;
        TotalJsonDataModel dataModel;
        SharedPreferences preferences;
        boolean isOpened=false;
        int d;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        toolbar=findViewById(R.id.CustomToolbar);
         Bundle bundleData=getIntent().getExtras();
         dataModel= (TotalJsonDataModel) bundleData.getSerializable("ObjectData");
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle(dataModel.getName());
            preferences =getSharedPreferences("Navigation",MODE_PRIVATE);
            if((dataModel.getId()-1)==0){
                toolbar.setBackgroundColor(getResources().getColor(R.color.Item1more));
            }
            else if((dataModel.getId()-1)==1){
                toolbar.setBackgroundColor(getResources().getColor(R.color.Item2more));
            }
            else if((dataModel.getId()-1)==2){
                toolbar.setBackgroundColor(getResources().getColor(R.color.Item3more));
            }
            else if((dataModel.getId()-1)==3){
                toolbar.setBackgroundColor(getResources().getColor(R.color.Item4more));
            }
            FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            DetailFragment detailFragment=new DetailFragment();
            Bundle MyBundle=new Bundle();
            MyBundle.putSerializable("DataObjectToDetailFrag",dataModel);
            detailFragment.setArguments(MyBundle);
            transaction.add(R.id.DetailFragment,detailFragment);
            transaction.commit();
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_navigate_next_black_24dp));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                finish();
                }
                });
        }

    @Override
    public void onClick(int Id) {
        Toast.makeText(this, "Step Clicked", Toast.LENGTH_SHORT).show();
       d=Id;
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        StepFragment detailFragment=new StepFragment();
        Bundle MyBundle=new Bundle();
        MyBundle.putSerializable("DataObjectToStepFrag",dataModel.getStepsList().get(Id));
        MyBundle.putInt("Position",dataModel.getId());
        detailFragment.setArguments(MyBundle);
        transaction.add(R.id.ListFragment,detailFragment);
        transaction.commit();
        isOpened=true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean("isOpened",isOpened);
        outState.putSerializable("Data",dataModel.getStepsList().get(d));
        outState.putInt("Id",dataModel.getId());
        }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.getBoolean("isOpened")==true){}
        StepsModel Data= (StepsModel) savedInstanceState.getSerializable("Data");
        d=savedInstanceState.getInt("Id");
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        StepFragment detailFragment=new StepFragment();
        Bundle MyBundle=new Bundle();
        MyBundle.putSerializable("DataObjectToStepFrag",Data);
        MyBundle.putInt("Position",d);
        detailFragment.setArguments(MyBundle);
        transaction.add(R.id.ListFragment,detailFragment);
        transaction.commit();
        }
}
