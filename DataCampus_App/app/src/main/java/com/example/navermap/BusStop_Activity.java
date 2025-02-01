package com.example.navermap;
import com.example.navermap.Map.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navermap.Bus_Menu.Bus1_Activity;
import com.example.navermap.Bus_Menu.Bus2_Activity;
import com.example.navermap.Bus_Menu.Bus3_Activity;

public class BusStop_Activity extends AppCompatActivity {
    Intent intent1 = null;
    Intent intent2 = null;
    Intent intent3 = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busstop);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("무장애 정류장 조회");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button bus_btn1 = (Button) findViewById(R.id.bus_btn4_1);
        Button bus_btn2 = (Button) findViewById(R.id.bus_btn4_2);
        Button bus_btn3 = (Button) findViewById(R.id.bus_btn4_3);

        //버튼1
        bus_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent1 = new Intent(getApplicationContext(), MapStation1_Activity.class);
                startActivity(intent1);
            }
        });

        bus_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent2 = new Intent(getApplicationContext(), MapStation2_Activity.class);
                startActivity(intent2);
            }
        });

        bus_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent3 = new Intent(getApplicationContext(), MapStation3_Activity.class);
                startActivity(intent3);
            }
        });



    }
}
