package comc.example.mohammedmorse.baking;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Adapters.SweetTypesAdapter;
import comc.example.mohammedmorse.baking.Interface.ListFragmentClickListener;
import comc.example.mohammedmorse.baking.Model.SweetImagesandName;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;


public class MyListFragment extends Fragment implements ListFragmentClickListener{
    Context context;
    ArrayList<SweetImagesandName>list;
    RecyclerView CustomRecyclerView;
    RecyclerView.LayoutManager CustomLayoutManager;
    SweetTypesAdapter CustomAdapter;
    ListFragmentClickListener mylistener;
public MyListFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        context=getContext();
    list=new ArrayList<>();
    list.add(new SweetImagesandName("Nutella Pie",R.drawable.notilapie,getResources().getDrawable(R.drawable.item1)));
        list.add(new SweetImagesandName("Brownies",R.drawable.praoniz,getResources().getDrawable(R.drawable.item2)));
        list.add(new SweetImagesandName("Yellow Cake",R.drawable.yellowcake,getResources().getDrawable(R.drawable.item3)));
        list.add(new SweetImagesandName("Cheesecake",R.drawable.cheesecake,getResources().getDrawable(R.drawable.item4)));
        CustomAdapter=new SweetTypesAdapter(context,list,this);
        CustomLayoutManager =new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
}

    @Override
    public void onStart() {
    //EventBus.getDefault().register(this);
    super.onStart();
    }
          /* @Subscribe
           public void GetSweetRecipe(TotalJsonDataModel data){

               Log.i("Morse", "The Data is "+data.getName());
           }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_list, container, false);
        CustomRecyclerView=view.findViewById(R.id.myRecyclerView);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        CustomRecyclerView.setAdapter(CustomAdapter);
        CustomRecyclerView.setLayoutManager(CustomLayoutManager);
        CustomRecyclerView.addItemDecoration(itemDecoration);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        mylistener= (ListFragmentClickListener) context;
        super.onAttach(context);

    }
    @Override
    public void onDetach() {
    //EventBus.getDefault().unregister(this);
        super.onDetach();

    }

    @Override
    public void onDestroy() {
    super.onDestroy();
        }

    @Override
    public void onItemClick(String  v) {
        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();

        mylistener.onItemClick(v);
}
}

