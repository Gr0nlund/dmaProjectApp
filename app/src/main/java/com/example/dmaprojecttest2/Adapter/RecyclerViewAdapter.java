package com.example.dmaprojecttest2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmaprojecttest2.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[] types;
    int[] images;

    public RecyclerViewAdapter(String[] sortingTypes, int[] img){
        types = sortingTypes;
        images = img;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.type.setText(types[position]);
        holder.img.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return types.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            type = itemView.findViewById(R.id.textView);

        }
    }
}



