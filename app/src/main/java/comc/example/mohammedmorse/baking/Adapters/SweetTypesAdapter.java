package comc.example.mohammedmorse.baking.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Interface.ListFragmentClickListener;
import comc.example.mohammedmorse.baking.Model.SweetImagesandName;
import comc.example.mohammedmorse.baking.R;

/**
 * Created by Mohammed Morse on 12/08/2018.
 */

public class SweetTypesAdapter extends RecyclerView.Adapter<SweetTypesAdapter.SweetViewHolder> {
    Context context;
    ListFragmentClickListener listener;
    ArrayList<SweetImagesandName> list;
    public SweetTypesAdapter(Context context,ArrayList<SweetImagesandName> list,ListFragmentClickListener listener){
        this.context=context;
        this.listener=listener;
        this.list=list;
    }
    @Override
    public SweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
    SweetViewHolder holder=new SweetViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SweetViewHolder holder, final int position) {
     holder.SweetImage.setImageResource(list.get(position).getImage());
     holder.SweetName.setText(list.get(position).getName());
     holder.SweetLayout.setBackground(list.get(position).getColor());
     holder.Next.setBackground(list.get(position).getColor());
     holder.SweetLayout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             listener.onItemClick(list.get(position).getName());
         }
     });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SweetViewHolder extends RecyclerView.ViewHolder {
      ImageView SweetImage;
      TextView SweetName;
      ConstraintLayout SweetLayout;
      ImageButton Next;
        public SweetViewHolder(View itemView) {
            super(itemView);
            SweetImage=itemView.findViewById(R.id.SweetImage);
            SweetName=itemView.findViewById(R.id.SweetName);
            SweetLayout=itemView.findViewById(R.id.SweetLayout);
        Next=itemView.findViewById(R.id.Next);
        }
    }
}
