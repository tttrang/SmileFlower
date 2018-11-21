package com.example.tttra.smileflower.Views;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tttra.smileflower.Common.Common;
import com.example.tttra.smileflower.Database.Database;
import com.example.tttra.smileflower.Model.Order;
import com.example.tttra.smileflower.Model.Request;
import com.example.tttra.smileflower.R;
import com.example.tttra.smileflower.viewholder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class CartActivity extends AppCompatActivity {
    private List<Order> cartList;
    private RecyclerView recyclerView;
    private CartAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference requests;

    TextView tvTotalPrice;
    FButton btnPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        //init Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //init event for btnPlace
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        
        loadListFlower();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        tvTotalPrice = findViewById(R.id.total);
        btnPlace = findViewById(R.id.btn_place_order);
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);
        alertDialog.setTitle("Điền địa chỉ giao hoa: ");

        final EditText edtAddress = new EditText(CartActivity.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
         edtAddress.setLayoutParams(layoutParams);
         alertDialog.setView(edtAddress);
         alertDialog.setIcon(R.drawable.ic_shopping_cart);

         alertDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int position) {
                 //create new Request
                 Request request = new Request(
                         Common.currentUser.getPhone(),
                         Common.currentUser.getName(),
                         edtAddress.getText().toString(),
                         tvTotalPrice.getText().toString(),
                         cartList);

                 //submit to Firebase, using System.currentTimeMillis to Key
                 requests.child(String.valueOf(System.currentTimeMillis()))
                         .setValue(request);
                 //delete cart
                 new Database(getBaseContext()).cleanCart();
                 Toast.makeText(CartActivity.this, "Cảm ơn quý khách hàng!", Toast.LENGTH_SHORT).show();
                    finish();
             }
         });
         alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });

         alertDialog.show();

        }

    private void loadListFlower() {
        cartList = new ArrayList<>();
        cartList = new Database(this).getCarts();
        adapter = new CartAdapter( CartActivity.this, cartList);
        recyclerView.setAdapter(adapter);

        //Calculate total price
        int total = 0;
        for (Order order: cartList)
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));

        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        tvTotalPrice.setText(format.format(total));
    }

}