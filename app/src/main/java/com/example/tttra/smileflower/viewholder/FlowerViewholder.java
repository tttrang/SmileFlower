package com.example.tttra.smileflower.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tttra.smileflower.Interface.ItemClickListener;
import com.example.tttra.smileflower.R;

public class FlowerViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvName;
    public ImageView imgFlower;
    private ItemClickListener itemClickListener;

    public FlowerViewholder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tv_name_flower);
        imgFlower = itemView.findViewById(R.id.img_flower);

        itemView.setOnClickListener(this);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
