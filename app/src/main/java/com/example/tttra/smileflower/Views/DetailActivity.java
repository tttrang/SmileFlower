package com.example.tttra.smileflower.Views;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.tttra.smileflower.Database.Database;
import com.example.tttra.smileflower.Model.Flower;
import com.example.tttra.smileflower.Model.Order;
import com.example.tttra.smileflower.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    ImageView imgFlower;
    TextView tvName, tvPrice, tvDiscount, tvDescription;

    String flowerId;
    Flower currentFlower;

    CollapsingToolbarLayout collapsingToolbarLayout;

    FirebaseDatabase database;
    DatabaseReference flowerDetail;

    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();

        //init firebase
        database = FirebaseDatabase.getInstance();
        flowerDetail = database.getReference("Flower");

        //Get FlowerId from Intent
        if(getIntent()!= null){
            flowerId = getIntent().getStringExtra("FlowerId");
        }
        if (!flowerId.isEmpty() && flowerId != null){
            getDetailFlower(flowerId);
        }
        
        //init event for btnCart
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        flowerId,
                        currentFlower.getName(),
                        numberButton.getNumber(),
                        currentFlower.getPrice(),
                        currentFlower.getDiscount(),
                        currentFlower.getImage()
                ));
                Toast.makeText(DetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        btnCart = findViewById(R.id.btn_cart);
        numberButton = findViewById(R.id.number_button);
        imgFlower = findViewById(R.id.img_flower_detail);
        tvPrice = findViewById(R.id.tv_flower_price);
        tvDiscount = findViewById(R.id.tv_discount_flower);
        tvDescription = findViewById(R.id.tv_flower_description);
        collapsingToolbarLayout = findViewById(R.id.collapsing);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
    }

    private void getDetailFlower(String flowerId) {
        flowerDetail.child(flowerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentFlower = dataSnapshot.getValue(Flower.class);

                //Set Image
                Picasso.get().load(currentFlower.getImage()).into(imgFlower);

                collapsingToolbarLayout.setTitle(currentFlower.getName());

                tvPrice.setText(currentFlower.getPrice());
                tvDiscount.setText(currentFlower.getDiscount());
                tvDescription.setText(currentFlower.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
