package com.example.tttra.smileflower.Views;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tttra.smileflower.R;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {
    TextView tvSlogan, tvWelcomeShop;
    FButton btnSignIn, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        // init Event for Button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignIn.setButtonColor(getResources().getColor(R.color.btnSignActive));
        btnSignUp = findViewById(R.id.btn_sign_up);
        btnSignUp.setButtonColor(getResources().getColor(R.color.btnSignUp));

        tvSlogan = findViewById(R.id.tv_slogan);
        tvWelcomeShop = findViewById(R.id.tv_name_shop);
        Typeface face = Typeface.createFromAsset(this.getAssets(), "fonts/Nabila.ttf");
        tvSlogan.setTypeface(face);
        tvWelcomeShop.setTypeface(face);
    }
}
