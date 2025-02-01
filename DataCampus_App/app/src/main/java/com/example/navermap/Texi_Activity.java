package com.example.navermap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Texi_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texi);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("당일 택시 운행 현황");

        actionBar.setDisplayHomeAsUpEnabled(true);

        CardView cardview = (CardView)findViewById(R.id.cardview4);
        ImageView imageview4 = (ImageView)findViewById(R.id.imageView4);

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.djcall.or.kr/receipt.php"));
                startActivity(urlintent);
            }
        });

        imageview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.djcall.or.kr/receipt.php"));
                startActivity(urlintent);
            }
        });

    }
}
