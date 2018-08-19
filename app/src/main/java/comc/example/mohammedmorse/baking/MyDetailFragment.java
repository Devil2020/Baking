package comc.example.mohammedmorse.baking;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Adapters.IngrediantsPageAdapter;
import comc.example.mohammedmorse.baking.Adapters.StepsPageAdapter;
import comc.example.mohammedmorse.baking.Retrofit.RetrofitInterface;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyDetailFragment extends Fragment {
    int Choise = -1;
    LinearLayout DetailLayout;
    ImageView mySweetImage;
    ViewPager Ingrediants;
    LinearLayout Dots1;
    ViewPager Steps;
    ViewPager.OnPageChangeListener IngrediantsListener;
    ViewPager.OnPageChangeListener StepsListener;
    TextView[] Dot;
    LinearLayout Dots2;
    TotalJsonDataModel Data;
    IngrediantsPageAdapter ingrediantsPageAdapter;
    StepsPageAdapter stepsPageAdapter;


    public MyDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Data = new TotalJsonDataModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_detail, container, false);
        mySweetImage = view.findViewById(R.id.mySweetImage);
        Ingrediants = view.findViewById(R.id.MyIngrdiants);
        DetailLayout = view.findViewById(R.id.DetailLayout);
        Dots1 = view.findViewById(R.id.MyCustomDots1);
        Steps = view.findViewById(R.id.MySteps);
        Dots2 = view.findViewById(R.id.MyCustomDots2);
        //
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //MakeRetofitReady();
    }


    @Subscribe
    public void GetSweetRecipe(TotalJsonDataModel data) {
        Data = data;
        MakeDots1(Data.getIngrediantList().size(), -1);
        MakeDots2(Data.getStepsList().size(), -1);
        MakeMyUiByArguments(Data.getName());
        ingrediantsPageAdapter = new IngrediantsPageAdapter(getContext(), Data.getIngrediantList());
        Ingrediants.setAdapter(ingrediantsPageAdapter);
        stepsPageAdapter = new StepsPageAdapter(getContext(), Data.getStepsList());
        Steps.setAdapter(stepsPageAdapter);
        // Steps.addOnPageChangeListener(this);
        IngrediantsListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                MakeDots1(Data.getIngrediantList().size(), position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        Ingrediants.addOnPageChangeListener(IngrediantsListener);
        StepsListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MakeDots2(Data.getStepsList().size(), position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        Steps.addOnPageChangeListener(StepsListener);
        Log.i("Morse", "The Data is " + Data.getName());
    }
    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void MakeMyUiByArguments(String Name) {
        //Choise=getArguments().getInt("Which");
        if (Name.equals("Nutella Pie")) {
            mySweetImage.setImageResource(R.drawable.notilapie);
            DetailLayout.setBackground(getResources().getDrawable(R.drawable.item1));

        } else if (Name.equals("Brownies")) {
            mySweetImage.setImageResource(R.drawable.praoniz);
            DetailLayout.setBackground(getResources().getDrawable(R.drawable.item2));
        } else if (Name.equals("Yellow Cake")) {
            mySweetImage.setImageResource(R.drawable.yellowcake);
            DetailLayout.setBackground(getResources().getDrawable(R.drawable.item3));
        } else if (Name.equals("Cheesecake")) {
            mySweetImage.setImageResource(R.drawable.cheesecake);
            DetailLayout.setBackground(getResources().getDrawable(R.drawable.item4));
        }
    }
    public void MakeDots1(int Size, int Position) {

        Dot = new TextView[Size];
        Dots1.removeAllViews();
        for (int i = 0; i < Size; i++) {
            Dot[i] = new TextView(getContext());
            Dot[i].setText(Html.fromHtml("&#8226"));
            Dot[i].setTextColor(getResources().getColor(R.color.TransplantWhite));
            Dot[i].setTextSize(35);
            Dots1.addView(Dot[i]);
        }
        if (Position > -1) {
            Dot[Position].setTextColor(Color.WHITE);
            Dot[Position].setTextSize(45);
        }
    }
    public void MakeDots2(int Size, int Position) {
        Dot = new TextView[Size];
        Dots2.removeAllViews();
        for (int i = 0; i < Size; i++) {
            Dot[i] = new TextView(getContext());
            Dot[i].setText(Html.fromHtml("&#8226"));
            Dot[i].setTextColor(getResources().getColor(R.color.TransplantWhite));
            Dot[i].setTextSize(35);
            Dots2.addView(Dot[i]);

        }
        if (Position > -1) {
            Dot[Position].setTextColor(Color.WHITE);
            Dot[Position].setTextSize(45);
        }
    }

}
