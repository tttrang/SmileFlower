package com.example.tttra.smileflower.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.tttra.smileflower.Common.Common;
import com.example.tttra.smileflower.Interface.ItemClickListener;
import com.example.tttra.smileflower.Model.Request;
import com.example.tttra.smileflower.R;
import com.example.tttra.smileflower.viewholder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //init recyclerView
        recyclerView = findViewById(R.id.recyclerView_listOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        loadOrders(Common.currentUser.getPhone());

    }

    private void loadOrders(String phone) {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_item,
                OrderViewHolder.class,
                requests.orderByChild("phone")
                .equalTo(phone)
        ) {

            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.tvId.setText(adapter.getRef(position).getKey());
                viewHolder.tvPhone.setText(model.getPhone());
                viewHolder.tvAddress.setText(model.getAddress());

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int posistion, boolean isLongClick) {
                        Toast.makeText(OrderActivity.this, "I do it", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        };
        recyclerView.setAdapter(adapter);

    }

}
