package com.example.tttra.smileflower.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.tttra.smileflower.Interface.ItemClickListener;
import com.example.tttra.smileflower.R;


public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvId, tvPhone, tvAddress;

        private ItemClickListener itemClickListener;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.order_id);
            tvPhone = itemView.findViewById(R.id.order_phone);
            tvAddress = itemView.findViewById(R.id.order_address);


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
