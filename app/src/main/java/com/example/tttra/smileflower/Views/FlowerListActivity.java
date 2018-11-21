package com.example.tttra.smileflower.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tttra.smileflower.Interface.ItemClickListener;
import com.example.tttra.smileflower.Model.Flower;
import com.example.tttra.smileflower.R;
import com.example.tttra.smileflower.viewholder.FlowerViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FlowerListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference flowerList;
    FirebaseRecyclerAdapter<Flower, FlowerViewholder> adapter;

    String flowerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_list);

        //init Firebase
        database = FirebaseDatabase.getInstance();
        flowerList = database.getReference("Flower");

        //init recyclerView
        recyclerView = findViewById(R.id.recyclerView_flower);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Get intent here
        if(getIntent()!= null){
            flowerId = getIntent().getStringExtra("CategoryId");
        }
        if (!flowerId.isEmpty() && flowerId != null){
            loadFlowerList(flowerId);
        }
    }

    private void loadFlowerList(String flowerId) {
        adapter = new FirebaseRecyclerAdapter<Flower, FlowerViewholder>(Flower.class,
                R.layout.flower_item,
                FlowerViewholder.class,
                flowerList.orderByChild("FlowerId").equalTo(flowerId)){    //select * from flower where FlowerId = {
            @Override
            protected void populateViewHolder(FlowerViewholder viewHolder, Flower model, final int position) {
                viewHolder.tvName.setText(model.getName());
                Picasso.get().load(model.getImage()).fit().centerInside().into(viewHolder.imgFlower);
                final Flower currentFlower = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int posistion, boolean isLongClick) {
                        Intent intent = new Intent(FlowerListActivity.this, DetailActivity.class);
                        intent.putExtra("FlowerId", adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}

