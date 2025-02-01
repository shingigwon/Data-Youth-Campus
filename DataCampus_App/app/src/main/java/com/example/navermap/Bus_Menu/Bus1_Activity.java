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

public class Bus1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("서구 건강체련관 순환버스");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button bus_btn1 = (Button) findViewById(R.id.bus_btn1_1);
        Button bus_btn2 = (Button) findViewById(R.id.bus_btn1_2);
        Button bus_btn3 = (Button) findViewById(R.id.bus_btn1_3);

        //버튼1
        bus_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Map1_1Activity.class);
                startActivity(intent1);
            }
        });

        bus_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Map1_2Activity.class);
                startActivity(intent2);
            }
        });

        bus_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Map1_3Activity.class);
                startActivity(intent3);
            }
        });



    }
}
