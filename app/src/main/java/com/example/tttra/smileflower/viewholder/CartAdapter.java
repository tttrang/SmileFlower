package com.example.tttra.smileflower.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.tttra.smileflower.R;

import com.example.tttra.smileflower.Model.Order;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private Context mContext;
    private List<Order> orderList;

    public CartAdapter(Context mContext, List<Order> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Order currentOrder = orderList.get(position);
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(currentOrder.getPrice()))*(Integer.parseInt(currentOrder.getQuantity()));
        holder.tvPrice.setText(format.format(price));
        holder.tvName.setText(currentOrder.getProductName());

        TextDrawable drawable = TextDrawable.builder()
                .buildRound("" + currentOrder.getQuantity(), Color.RED);
        holder.imgCount.setImageDrawable(drawable);

        Picasso.get().load(currentOrder.getImgCart()).resize(80, 80).centerCrop().into(holder.imgCart);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice;
        ImageView imgCount, imgCart;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.cart_item_name);
            tvPrice = itemView.findViewById(R.id.cart_item_price);
            imgCount = itemView.findViewById(R.id.cart_item_count);
            imgCart = itemView.findViewById(R.id.img_cart);

        }
    }
}
