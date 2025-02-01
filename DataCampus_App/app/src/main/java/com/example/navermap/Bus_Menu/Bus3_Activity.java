package com.example.navermap.Bus_Menu;
import com.example.navermap.*;
import com.example.navermap.Map.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Bus3_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_3);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("대전광역시립체육재활원 순환버스");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button bus_btn3_1 = (Button) findViewById(R.id.bus_btn3_1);
        Button bus_btn3_2 = (Button) findViewById(R.id.bus_btn3_2);
        Button bus_btn3_3 = (Button) findViewById(R.id.bus_btn3_3);
        Button bus_btn3_4 = (Button) findViewById(R.id.bus_btn3_4);
        Button bus_btn3_5 = (Button) findViewById(R.id.bus_btn3_5);
        Button bus_btn3_6 = (Button) findViewById(R.id.bus_btn3_6);
        Button bus_btn3_7 = (Button) findViewById(R.id.bus_btn3_7);
        Button bus_btn3_8 = (Button) findViewById(R.id.bus_btn3_8);


//        //버튼1
        bus_btn3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Map3_1Activity.class);
                startActivity(intent1);
            }
        });

        bus_btn3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Map3_1Activity.class);
                startActivity(intent2);
            }
        });

        bus_btn3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Map3_1Activity.class);
                startActivity(intent3);
            }
        });

        bus_btn3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Map3_1Activity.class);
                startActivity(intent3);
            }
        });

        bus_btn3_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Map3_1Activity.class);
                startActivity(intent3);
            }
        });

        bus_btn3_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Map3_1Activity.class);
                startActivity(intent3);
            }
        });

        bus_btn3_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Map3_1Activity.class);
                startActivity(intent3);
            }
        });
        bus_btn3_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Map3_1Activity.class);
                startActivity(intent3);
            }
        });


    }
}
