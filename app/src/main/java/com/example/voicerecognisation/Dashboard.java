package com.example.voicerecognisation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    CardView t2scard,s2tcard,settingCard,profileCard,aboutCard;
    ImageView logout_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        s2tcard = findViewById(R.id.card1);
        t2scard = findViewById(R.id.card2);
        profileCard = findViewById(R.id.card3);
        settingCard = findViewById(R.id.card4);
        aboutCard = findViewById(R.id.card5);
        logout_btn = findViewById(R.id.logout_btn_dashboard);
        auth = FirebaseAuth.getInstance();
        s2tcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),S2TActivity.class);
                startActivity(intent1);
            }
        });
        t2scard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),T2SActivity.class);
                startActivity(intent2);
            }
        });
        settingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent3);
            }
        });
        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent4);
            }
        });
        aboutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getApplicationContext(),AboutUsActivity.class);
                startActivity(intent5);
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogout = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intentLogout);
                auth.signOut();
                finish();
            }
        });
    }
}