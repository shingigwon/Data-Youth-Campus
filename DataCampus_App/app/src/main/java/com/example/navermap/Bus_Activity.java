package com.example.navermap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.navermap.Bus_Menu.Bus2_Activity;
import com.example.navermap.Bus_Menu.Bus1_Activity;
import com.example.navermap.Bus_Menu.Bus3_Activity;

public class Bus_Activity extends AppCompatActivity {
    Intent intent1 = null;
    Intent intent2 = null;
    Intent intent3 = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("무료 순환버스 조회");
        actionBar.setDisplayHomeAsUpEnabled(true);

        CardView cardview1 = (CardView) findViewById(R.id.cardview1);
        CardView cardview2 = (CardView) findViewById(R.id.cardview2);
        CardView cardview3 = (CardView) findViewById(R.id.cardview3);

        ImageView imageview1 = (ImageView) findViewById(R.id.busimageview1);
        ImageView imageview2 = (ImageView) findViewById(R.id.busimageview2);
        ImageView imageview3 = (ImageView) findViewById(R.id.busimageview3);


        cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(getApplicationContext(), Bus1_Activity.class);
                startActivity(intent1);
            }
        });

        cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(getApplicationContext(), Bus2_Activity.class);
                startActivity(intent2);
            }
        });

        cardview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(getApplicationContext(), Bus3_Activity.class);
                startActivity(intent3);
            }
        });

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(getApplicationContext(), Bus1_Activity.class);
                startActivity(intent1);
            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(getApplicationContext(), Bus2_Activity.class);
                startActivity(intent2);
            }
        });

        imageview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(getApplicationContext(), Bus3_Activity.class);
                startActivity(intent3);
            }
        });

    }
}
