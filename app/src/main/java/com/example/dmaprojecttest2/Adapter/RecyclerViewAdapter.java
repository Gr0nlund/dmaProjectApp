package com.example.dmaprojecttest2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmaprojecttest2.Interface.ClickListener;
import com.example.dmaprojecttest2.R;

import java.lang.ref.WeakReference;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private final ClickListener listener;
    private final String[] types;
    private final int[] images;

    public RecyclerViewAdapter(String[] sortingTypes, int[] img, ClickListener listener){
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
        holder.type.setText(types[position]);
        holder.img.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return types.length;
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

            /*if (reportButton.getId() == v.getId()) {
                Toast.makeText(v.getContext(), "REPORT PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }*/
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
