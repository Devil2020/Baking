package comc.example.mohammedmorse.baking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import comc.example.mohammedmorse.baking.Adapters.IngrediantsRecyclerViewAdapter;
import comc.example.mohammedmorse.baking.Adapters.StepsRecyclerViewAdapter;
import comc.example.mohammedmorse.baking.Interface.StepsClickListener;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;

public class DetailFragment extends Fragment {
      ScrollView scrollView;
      int x,y;
           TotalJsonDataModel Data ;
           ImageView SweetImage;
           RecyclerView Ingrediants ,Steps;
           LinearLayout DetailLayout;
           IngrediantsRecyclerViewAdapter ingrediantsRecyclerViewAdapter;
           StepsRecyclerViewAdapter stepsRecyclerViewAdapter;
           StepsClickListener listener;
           LinearLayoutManager IngrediantsLayoutManager;
           LinearLayoutManager StepsLayoutManager;
    public DetailFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
        x=savedInstanceState.getInt("x");
        y=savedInstanceState.getInt("y");
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        scrollView=view.findViewById(R.id.Scrollview1);
        SweetImage=view.findViewById(R.id.DetailFragmentSweetImage);
        Ingrediants=view.findViewById(R.id.DetailFragmentIngrediantsRecyclerView);
        Steps=view.findViewById(R.id.DetailFragmentStepsRecyclerView);
        DetailLayout=view.findViewById(R.id.DetailFragmentLayout);
        IngrediantsLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        StepsLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        Data=new TotalJsonDataModel();
        Data= (TotalJsonDataModel) getArguments().getSerializable("DataObjectToDetailFrag");
        GetData(Data);
        return view;
    }
    @Subscribe
    public void GetData(TotalJsonDataModel Data){
        Bundle  bundle;
        ingrediantsRecyclerViewAdapter=new IngrediantsRecyclerViewAdapter(Data.getIngrediantList(),getContext());
        stepsRecyclerViewAdapter=new StepsRecyclerViewAdapter(Data.getStepsList(),getContext(),listener);
        if((Data.getId()-1)==0){
        SweetImage.setImageResource(R.drawable.notilapie);
        DetailLayout.setBackground(getResources().getDrawable(R.drawable.item1));
        Ingrediants.setBackgroundColor(getResources().getColor(R.color.Item1));
        Steps.setBackgroundColor(getResources().getColor(R.color.Item1));
        }
        else if((Data.getId()-1)==1){
            SweetImage.setImageResource(R.drawable.praoniz);
            DetailLayout.setBackground(getResources().getDrawable(R.drawable.item2));
            Ingrediants.setBackgroundColor(getResources().getColor(R.color.Item2));
            Steps.setBackgroundColor(getResources().getColor(R.color.Item2));
        }
        else if((Data.getId()-1)==2){
            SweetImage.setImageResource(R.drawable.yellowcake);
            DetailLayout.setBackground(getResources().getDrawable(R.drawable.item3));
            Ingrediants.setBackgroundColor(getResources().getColor(R.color.Item3));
            Steps.setBackgroundColor(getResources().getColor(R.color.Item3));
        }
        else if((Data.getId()-1)==3){
            SweetImage.setImageResource(R.drawable.cheesecake);
            DetailLayout.setBackground(getResources().getDrawable(R.drawable.item4));
            Ingrediants.setBackgroundColor(getResources().getColor(R.color.Item4));
            Steps.setBackgroundColor(getResources().getColor(R.color.Item4));
        }
       //Set up our IngrediantRecyclerView
        Ingrediants.setLayoutManager(IngrediantsLayoutManager);
        Ingrediants.setAdapter(ingrediantsRecyclerViewAdapter);
      //Set up our StepsRecyclerView
        Steps.setLayoutManager(StepsLayoutManager);
        Steps.setAdapter(stepsRecyclerViewAdapter);
        scrollView.scrollTo(x,y);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
        listener=(StepsClickListener) context;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        x=scrollView.getScrollX();
        y=scrollView.getScrollY();
        outState.putInt("x",x);
        outState.putInt("y",y);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }
}
