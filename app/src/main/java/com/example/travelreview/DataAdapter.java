package com.example.travelreview;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements View.OnClickListener {
    ArrayList<ItemTravel> listTravel;
    Context context;

    public DataAdapter(ArrayList<ItemTravel> listTravel, Context context) {
        this.listTravel = listTravel;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(listTravel.get(position).getName());
        holder.imgImage.setImageResource(listTravel.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return listTravel.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textName;
        ImageView imgImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            imgImage = itemView.findViewById(R.id.imgImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextContent(textName.getText().toString());
                }
            });
        }
    }
    public void nextContent(String value){
        Intent intent = new Intent (context, Information.class);
        intent.putExtra("name", value);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
};