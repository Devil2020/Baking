package comc.example.mohammedmorse.baking.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.MenuActivity;
import comc.example.mohammedmorse.baking.Model.SweetImagesandName;
import comc.example.mohammedmorse.baking.R;

/**
 * Created by Mohammed Morse on 06/08/2018.
 */

public class CustomPagerAdapter extends PagerAdapter {
    Context context;
    Button Skip , Back;
    int [] Icons={R.drawable.backingicon, R.drawable.sweeticon};
    String  [] Titles ={" Baking App " ," Food Ready " };
    String [] Describtion={"Hey , It`s an application for Cooking and make a Sweet kinds of foods , There are a lot of Videos to help you to create your meal and Some of very good steps to do it , I wish it will be Useful for You ."
            ,"There are recipes ready for some of the varieties of desserts and way of making them such as bin cakes when donuts .... etc"};
    int[] Background={R.drawable.draw1,
    R.drawable.draw2};
    ViewPager viewPager;
    public CustomPagerAdapter(Context context, ViewPager viewPager){
        this.context=context;
        this.viewPager=viewPager;
    }

    public CustomPagerAdapter(MenuActivity menuActivity, int item, ArrayList<SweetImagesandName> list) {

    }

    @Override
    public int getCount() {
        return Icons.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.slider_layout,container,false);
        RelativeLayout layout=view.findViewById(R.id.RelativeLayout);
        ImageView imageView=view.findViewById(R.id.Icon);
        Skip=view.findViewById(R.id.Skip);
        Back=view.findViewById(R.id.Back);
        TextView Master , Detail;
        Master=view.findViewById(R.id.AppName);
        Detail=view.findViewById(R.id.Detail);

        container.addView(view);
      if(position==0){
            layout.setBackground(context.getResources().getDrawable(R.drawable.draw1));
          /*  */
            Log.i("baking", "instantiateItem: 1");
        }
        else {
            Log.i("baking", "instantiateItem: 2");
          /*  */
            layout.setBackground(context.getResources().getDrawable(R.drawable.draw2));
        }
        imageView.setImageResource(Icons[position]);
        Master.setText(Titles[position]);
        Detail.setText(Describtion[position]);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
