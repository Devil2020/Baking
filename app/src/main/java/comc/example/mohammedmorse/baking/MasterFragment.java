package comc.example.mohammedmorse.baking;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Adapters.MasterRecyclerViewAdapter;
import comc.example.mohammedmorse.baking.Interface.MasterFragmentClickListener;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;

public class MasterFragment extends Fragment {
      ArrayList<TotalJsonDataModel> list;
      MasterRecyclerViewAdapter adapter;
      MasterFragmentClickListener listener;
      RecyclerView recyclerView;
      RecyclerView.LayoutManager manager;
    CustomProgressDialog fragment;
    Parcelable Result;
      Context context;
    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment=new CustomProgressDialog();
        fragment.show(getFragmentManager(),"Morse");
        list= (ArrayList<TotalJsonDataModel>) getArguments().getSerializable("Data");
        manager=new LinearLayoutManager(context);
        if(savedInstanceState!=null){
            Result=savedInstanceState.getParcelable("State");
            Log.i("Morse", "onActivityCreated: Rotate");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_master, container, false);
        recyclerView=view.findViewById(R.id.MasterRecyclerView);
        GetDataFromMenuActivity(list);

    return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        listener= (MasterFragmentClickListener) context;
        EventBus.getDefault().register(this);

    }
    @Subscribe
    public void GetDataFromMenuActivity(ArrayList<TotalJsonDataModel>list){

        Log.i("Morse", "GetDataFromMenuActivity: "+this.list.size());
        adapter=new MasterRecyclerViewAdapter(list,getContext(),listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter.notifyDataSetChanged();
        manager.onRestoreInstanceState(Result);
    }

    @Override
    public void onResume() {
        super.onResume();
    fragment.dismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable bundle=  manager.onSaveInstanceState();
        outState.putParcelable("State",bundle);
        Log.i("Morse", "onSaveInstanceState: Save");
    }
}
