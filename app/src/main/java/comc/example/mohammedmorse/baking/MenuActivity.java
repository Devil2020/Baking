package comc.example.mohammedmorse.baking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Adapters.IngrediantsPageAdapter;
import comc.example.mohammedmorse.baking.Adapters.StepsPageAdapter;
import comc.example.mohammedmorse.baking.Interface.ListFragmentClickListener;
import comc.example.mohammedmorse.baking.Retrofit.RetrofitInterface;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuActivity extends AppCompatActivity implements ListFragmentClickListener {
Toolbar toolbar;
    FragmentManager manager;
    static int Count=0;
    FragmentTransaction transaction;
FrameLayout ListFragment, DetailFragment;
ArrayList<TotalJsonDataModel>Data;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    toolbar=findViewById(R.id.CustomToolbar);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    toolbar.setTitle("Sweet Types");
    Data=new ArrayList<TotalJsonDataModel>();
    toolbar.setBackgroundColor(getResources().getColor(R.color.Toolbar));
    toolbar.setTitleTextColor(getResources().getColor(R.color.White));
//CheckMyNetwork
CheckNetwork();
Data=MakeRetofitReady();
    //get fragments
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();

     ListFragment=findViewById(R.id.ListFragment);
     DetailFragment=findViewById(R.id.DetailFragment);

    if(ListFragment!=null){
        MyListFragment fragment=new MyListFragment();
        transaction.add(R.id.ListFragment,fragment);
        transaction.commit();
    }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    @Override
    public void onItemClick(String v) {
        int Which = WhichItemClicked(v);
        Log.i("Morse", "Which Result is " + Which);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle(v);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("Sweet Types");
                toolbar.setNavigationIcon(null);
                MyListFragment fragment = new MyListFragment();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ListFragment, fragment);
                transaction.commit();

            }
        });
        if (DetailFragment == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("Which", Which);
            MyDetailFragment fragments = new MyDetailFragment();
            fragments.setArguments(bundle);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.ListFragment, fragments);
            transaction.commitNow();
        } else {

            Bundle bundle = new Bundle();
            bundle.putInt("Which", Which);
            MyDetailFragment fragment = new MyDetailFragment();
            fragment.setArguments(bundle);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.DetailFragment, fragment);
            transaction.commitNow();
            //  EventBus.getDefault().post(Data.get(Which-1));
        }
        try {
            PostData(Data.get(Which-1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void PostData(TotalJsonDataModel Data) throws InterruptedException {

      //  Thread.sleep(10000);
        EventBus.getDefault().post(Data);
    }
    public int WhichItemClicked(String DataString){
        int Which=-1;
        if(DataString.equals("Nutella Pie")){Which=1;
            toolbar.setTitle(DataString);
       // EventBus.getDefault().post(Data.get(0));
        }
        else if(DataString.equals("Brownies")){Which=2;
            toolbar.setTitle(DataString);
        //    EventBus.getDefault().post(Data.get(1));
        }
        else if(DataString.equals("Yellow Cake")){Which=3;
            toolbar.setTitle(DataString);
         //   EventBus.getDefault().post(Data.get(2));
        }
        else if(DataString.equals("Cheesecake")){Which=4;
            toolbar.setTitle(DataString);
         //   EventBus.getDefault().post(Data.get(3));
            }
        return Which;
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
            }
            @Override
            public void onFailure(Call<ArrayList<TotalJsonDataModel>> call, Throwable t) {
                call.request();
                Log.i("Morse","Fail because "+t.getMessage());
            }
        });
        Log.i("Morse"," : "+Data.size());
        return Data;
    }

}
