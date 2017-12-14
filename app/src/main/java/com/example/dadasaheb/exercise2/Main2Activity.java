package com.example.dadasaheb.exercise2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
TextView nametv,pricetv,volumetv,changetv,uptv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nametv=(TextView) findViewById(R.id.textView6);
        pricetv=(TextView) findViewById(R.id.textView7);
        volumetv=(TextView) findViewById(R.id.textView8);
        changetv=(TextView) findViewById(R.id.textView9);
        uptv=(TextView) findViewById(R.id.textView10);
        nametv.setText(getIntent().getStringExtra("Name"));
        pricetv.setText(getIntent().getStringExtra("Price"));
        volumetv.setText(getIntent().getStringExtra("Volume"));
        changetv.setText(getIntent().getStringExtra("Change"));
        uptv.setText(getIntent().getStringExtra("Up"));

    }
}
