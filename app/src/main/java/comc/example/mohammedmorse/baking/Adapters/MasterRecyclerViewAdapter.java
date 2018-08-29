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
import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;

/**
 * Created by Mohammed Morse on 22/08/2018.
 */

public class MasterRecyclerViewAdapter extends RecyclerView.Adapter<MasterRecyclerViewAdapter.CustomHolder> {
    private ArrayList<TotalJsonDataModel>list;
    private Context context;
    private MasterFragmentClickListener listener;
    public MasterRecyclerViewAdapter(ArrayList<TotalJsonDataModel>list,Context context,MasterFragmentClickListener listener){
        this.list=list;
        this.context=context;
        this.listener=listener;
    }
    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        CustomHolder holder=new CustomHolder(view);
        Log.i("Morse","RecyclerViewOnCreatViewHolder")    ;
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, final int position) {
     if(list.get(position).getName().equals("Nutella Pie")){
        holder.SweetName.setText(list.get(position).getName());
        holder.SweetName.setTextSize(20);
        holder.SweetName.setTextColor(Color.WHITE);
        holder.SweetImage.setImageResource(R.drawable.notilapie);
        holder.Next.setBackground(context.getResources().getDrawable(R.drawable.item1));
        holder.SweetLayout.setBackground(context.getResources().getDrawable(R.drawable.item1));

     }
     else if(list.get(position).getName().equals("Brownies")){
         holder.SweetName.setText(list.get(position).getName());
         holder.SweetName.setTextSize(20);
         holder.SweetName.setTextColor(Color.WHITE);
         holder.SweetImage.setImageResource(R.drawable.praoniz);
         holder.Next.setBackground(context.getResources().getDrawable(R.drawable.item2));
         holder.SweetLayout.setBackground(context.getResources().getDrawable(R.drawable.item2));
     }
     else if(list.get(position).getName().equals("Yellow Cake")){
         holder.SweetName.setText(list.get(position).getName());
         holder.SweetName.setTextSize(20);
         holder.SweetName.setTextColor(Color.WHITE);
         holder.SweetImage.setImageResource(R.drawable.yellowcake);
         holder.Next.setBackground(context.getResources().getDrawable(R.drawable.item3));
         holder.SweetLayout.setBackground(context.getResources().getDrawable(R.drawable.item3));
     }
     else{
         holder.SweetName.setText(list.get(position).getName());
         holder.SweetName.setTextSize(20);
         holder.SweetName.setTextColor(Color.WHITE);
         holder.SweetImage.setImageResource(R.drawable.cheesecake);
         holder.Next.setBackground(context.getResources().getDrawable(R.drawable.item4));
         holder.SweetLayout.setBackground(context.getResources().getDrawable(R.drawable.item4));
     }
     holder.SweetLayout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             listener.onItemClick(list.get(position).getName());
         }
     });
        Log.i("Morse","RecyclerViewOnBindViewHolder")    ;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder{
        ConstraintLayout SweetLayout;
        ImageView SweetImage;
        TextView SweetName;
        ImageButton Next;
        public CustomHolder(View itemView) {
            super(itemView);
            SweetLayout=itemView.findViewById(R.id.MasterRecyclerViewSweetLayout);
            SweetImage=itemView.findViewById(R.id.MasterRecyclerViewSweetImage);
            SweetName=itemView.findViewById(R.id.MasterRecyclerViewSweetName);
        Next=itemView.findViewById(R.id.MasterRecyclerViewNext);
        }
    }
}
