package com.example.dmaprojecttest2.Adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.example.dmaprojecttest2.Fragments.MenuFragment;
import com.example.dmaprojecttest2.Interface.ClickListener;
import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private final ClickListener listener;
    private final List<String> types;
    private final List<Integer> images;

    public RecyclerViewAdapter(List<String> sortingTypes, List<Integer> img, ClickListener listener){
        this.listener = listener;
        types = sortingTypes;
        images = img;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.type.setText(types.get(position));
        holder.img.setImageResource(images.get(position));
        int red = Color.RED;
        int green = Color.GREEN;
        if(Integer.parseInt(MainActivity.fetchTypeResult.get(position)[1]) == 0){
            holder.reportButton.setBackgroundTintList(ColorStateList.valueOf(green));
            holder.reportButton.setText("Report full");
        } else {
            holder.reportButton.setBackgroundTintList(ColorStateList.valueOf(red));
            holder.reportButton.setText("Report clear");
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final WeakReference<ClickListener> listenerRef;
        ImageView img;
        TextView type;
        Button reportButton;

        public ViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);

            listenerRef = new WeakReference<>(listener);
            img = itemView.findViewById(R.id.imageView);
            type = itemView.findViewById(R.id.textView);
            reportButton =  itemView.findViewById(R.id.buttonReport);

            reportButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
