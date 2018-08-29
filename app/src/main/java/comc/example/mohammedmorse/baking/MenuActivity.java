package comc.example.mohammedmorse.baking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Interface.MasterFragmentClickListener;
import comc.example.mohammedmorse.baking.Interface.StepsClickListener;
import comc.example.mohammedmorse.baking.Retrofit.RetrofitInterface;
import comc.example.mohammedmorse.baking.Retrofit.StepsModel;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuActivity extends AppCompatActivity implements MasterFragmentClickListener {
Toolbar toolbar;
    FragmentManager manager;
     int Count=0;

    FragmentTransaction transaction;
FrameLayout DetailFragment;
FrameLayout ListFragment;
    FragmentManager Listmanager;
    MasterFragment fragment;
 ArrayList<TotalJsonDataModel> Data;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    toolbar=findViewById(R.id.CustomToolbar);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    toolbar.setTitle("Sweet Types");
    Data=new ArrayList<TotalJsonDataModel>();
    DetailFragment=findViewById(R.id.DetailFragment);
    ListFragment=findViewById(R.id.ListFragment);
    //TotalFragment=findViewById(R.id.TotalFragmentLayout);
    toolbar.setBackgroundColor(getResources().getColor(R.color.Toolbar));
    toolbar.setTitleTextColor(getResources().getColor(R.color.White));
//CheckMyNetwork
CheckNetwork();
Data=MakeRetofitReady();
    }
    public ArrayList<TotalJsonDataModel> MakeRetofitReady(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface retrofitInterface=retrofit.create(RetrofitInterface.class);
        Call<ArrayList<TotalJsonDataModel>> Call=retrofitInterface.GetDataFromUrl();
        Call.enqueue(new Callback<ArrayList<TotalJsonDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TotalJsonDataModel>> call, Response<ArrayList<TotalJsonDataModel>> response) {
                Data = response.body();
                    Log.i("Morse", "Success " + Data.size());
                    manager=getSupportFragmentManager();
                    transaction=manager.beginTransaction();
                     fragment=new MasterFragment();
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("Data",Data);
                    fragment.setArguments(bundle);
                    transaction.add(R.id.ListFragment,fragment);
                    transaction.commit();
            }
            @Override
            public void onFailure(Call<ArrayList<TotalJsonDataModel>> call, Throwable t) {
                call.request();
                Log.i("Morse","Fail because "+t.getMessage());
            }
        });
        return Data;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();


    }

    public void CheckNetwork(){
        ConnectivityManager manager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null&&info.isConnected()){
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        }
        else{
            AlertDialog.Builder dialog=new AlertDialog.Builder(this).
                    setTitle("Network Error").setMessage("Please Check your Network Connection , Open your WIFI or Open your Data .")
                    .setIcon(R.drawable.ic_network_wifi_black_24dp).setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            dialog.show();
        }
    }
    public int getPositionByName(String v){
        int Position=-1;
        if(v.equals(Data.get(0).getName())){Position=0;}
        else if(v.equals(Data.get(1).getName())){Position=1;}
        else if(v.equals(Data.get(2).getName())){Position=2;}
        else if(v.equals(Data.get(3).getName())){Position=3;}
        return Position;
    }
    @Override
    public void onItemClick(String v) {
        int Position = getPositionByName(v);
        Count=Position;
        if(DetailFragment==null){
               Intent RecipeAct=new Intent(this,RecipeActivity.class);
               RecipeAct.putExtra("ObjectData",Data.get(Position));
               startActivity(RecipeAct);
        }
        else{

            //add Detail one
            Intent RecipeAct=new Intent(this,SolutionActivity.class);
            RecipeAct.putExtra("ObjectData",Data.get(Position));
            startActivity(RecipeAct);
        }
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }
}
