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

        nametv=findViewById(R.id.textView6);
        pricetv=findViewById(R.id.textView6);
        volumetv=findViewById(R.id.textView6);
        changetv=findViewById(R.id.textView6);
        uptv=findViewById(R.id.textView6);
        nametv.setText(getIntent().getStringExtra("Name"));
        pricetv.setText(getIntent().getStringExtra("Price"));
        volumetv.setText(getIntent().getStringExtra("Volume"));
        changetv.setText(getIntent().getStringExtra("Change"));
        if(getIntent().getBooleanExtra("Up",false))
        uptv.setText("Up");
        else uptv.setText("Down");
    }
}
