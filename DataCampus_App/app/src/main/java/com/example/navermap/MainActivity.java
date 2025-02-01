package com.example.navermap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class MainActivity extends AppCompatActivity {
    Intent intent1 = null;
    Intent intent2 = null;
    Intent intent3 = null;
    Intent urlintent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView cardview1 = (CardView) findViewById(R.id.cardview1);
        CardView cardview2 = (CardView) findViewById(R.id.cardview2);
        CardView cardview3 = (CardView) findViewById(R.id.cardview3);
        CardView cardview4 = (CardView) findViewById(R.id.cardview4);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(getApplicationContext(), Texi_Activity.class);
                startActivity(intent1);
            }
        });

        cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(getApplicationContext(), Bus_Activity.class);
                startActivity(intent2);
            }
        });

        cardview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bms.daejeon.go.kr:7000/lowfloorbus/"));
                startActivity(urlintent);
            }
        });
        cardview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(getApplicationContext(), BusStop_Activity.class);
                startActivity(intent3);
            }
        });
        //-------------------------------------------------------------------------------------------------------------------
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(getApplicationContext(), Texi_Activity.class);
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(getApplicationContext(), Bus_Activity.class);
                startActivity(intent2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bms.daejeon.go.kr:7000/lowfloorbus/"));
                startActivity(urlintent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(getApplicationContext(), BusStop_Activity.class);
                startActivity(intent3);
            }
        });
    }
}
