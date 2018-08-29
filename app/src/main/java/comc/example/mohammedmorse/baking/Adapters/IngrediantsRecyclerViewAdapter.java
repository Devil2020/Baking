package comc.example.mohammedmorse.baking.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Interface.MasterFragmentClickListener;
import comc.example.mohammedmorse.baking.R;
import comc.example.mohammedmorse.baking.Retrofit.IngrediantsModel;
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;

/**
 * Created by Mohammed Morse on 22/08/2018.
 */

public class IngrediantsRecyclerViewAdapter extends RecyclerView.Adapter<IngrediantsRecyclerViewAdapter.CustomIngrediantHolder> {
    private ArrayList<IngrediantsModel>list;
    private Context context;
    public IngrediantsRecyclerViewAdapter(ArrayList<IngrediantsModel>list, Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public CustomIngrediantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.ingrediantitem,parent,false);
        CustomIngrediantHolder holder=new CustomIngrediantHolder(view);
        Log.i("Morse","RecyclerViewOnCreatViewHolder")    ;
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomIngrediantHolder holder, final int position) {
        String Data=position+"- We Need "+list.get(position).getQuantity()+" "+list.get(position).getMeasure()+" of "+
                list.get(position).getIngrediant()+".";
        holder.SweetName.setText(Data);
        //Log.i("Morse","RecyclerViewOnBindViewHolder")    ;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomIngrediantHolder extends RecyclerView.ViewHolder{
        TextView SweetName;
        public CustomIngrediantHolder(View itemView) {
            super(itemView);
            SweetName=itemView.findViewById(R.id.IngrediantRecipe);

        }
    }
}
