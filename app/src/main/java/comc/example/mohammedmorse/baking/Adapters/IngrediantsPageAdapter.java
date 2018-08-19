package comc.example.mohammedmorse.baking.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.R;
import comc.example.mohammedmorse.baking.Retrofit.IngrediantsModel;

/**
 * Created by Mohammed Morse on 13/08/2018.
 */

public class IngrediantsPageAdapter extends PagerAdapter{
    Context context;
    ArrayList<IngrediantsModel> list;
    public IngrediantsPageAdapter(Context context,ArrayList<IngrediantsModel>list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.ingrediants_layout,container,false);
        TextView Ingrediant=view.findViewById(R.id.Ingrediant);
        container.addView(view);
        String IngrediantsString=position+1+" - "+list.get(position).getQuantity()+" "+list.get(position).getMeasure() +" Of "+list.get(position).getIngrediant();
        Ingrediant.setText(IngrediantsString);
        Ingrediant.setTextColor(Color.BLACK);
        Ingrediant.setTextSize(18);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
