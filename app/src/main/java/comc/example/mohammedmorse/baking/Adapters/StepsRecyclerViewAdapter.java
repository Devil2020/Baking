package comc.example.mohammedmorse.baking.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Interface.StepsClickListener;
import comc.example.mohammedmorse.baking.R;
import comc.example.mohammedmorse.baking.Retrofit.IngrediantsModel;
import comc.example.mohammedmorse.baking.Retrofit.StepsModel;

/**
 * Created by Mohammed Morse on 22/08/2018.
 */

public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.CustomIngrediantHolder> {
    private ArrayList<StepsModel>list;
    private Context context;
    private StepsClickListener listener;
    public StepsRecyclerViewAdapter(ArrayList<StepsModel>list, Context context,StepsClickListener listener){
        this.list=list;
        this.context=context;
        this.listener=listener;
    }
    @Override
    public CustomIngrediantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.stepsitem,parent,false);
        CustomIngrediantHolder holder=new CustomIngrediantHolder(view);
        Log.i("Morse","RecyclerViewOnCreatViewHolder")    ;
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomIngrediantHolder holder, final int position) {
        holder.ReciepeDesc.setText(list.get(position).getShortDesc());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(position).getId());
            }
        });
        //Log.i("Morse","RecyclerViewOnBindViewHolder")    ;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomIngrediantHolder extends RecyclerView.ViewHolder{
        TextView ReciepeDesc;
        ConstraintLayout constraintLayout;
        public CustomIngrediantHolder(View itemView) {
            super(itemView);
            ReciepeDesc=itemView.findViewById(R.id.StepsRecipe);
            constraintLayout=itemView.findViewById(R.id.StepsLayout);
        }
    }
}
