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

public class Bus2_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("유성구 장애인종합복지관 순환버스");
        actionBar.setDisplayHomeAsUpEnabled(true);


        Button bus_btn2_1 = (Button) findViewById(R.id.bus_btn2_1);
        Button bus_btn2_2 = (Button) findViewById(R.id.bus_btn2_2);
        Button bus_btn2_3 = (Button) findViewById(R.id.bus_btn2_3);

        //버튼1
        bus_btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Map2_1Activity.class);
                startActivity(intent1);
            }
        });

        bus_btn2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Map2_1Activity.class);
                startActivity(intent2);
            }
        });

        bus_btn2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Map2_1Activity.class);
                startActivity(intent3);
            }
        });



    }
}
