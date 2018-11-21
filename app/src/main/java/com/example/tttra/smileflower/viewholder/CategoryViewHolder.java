package com.example.tttra.smileflower.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tttra.smileflower.Interface.ItemClickListener;
import com.example.tttra.smileflower.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvName;
    public ImageView imgCategory;
    private ItemClickListener itemClickListener;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tv_name_flower_type);
        imgCategory = itemView.findViewById(R.id.img_flower_type);
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
