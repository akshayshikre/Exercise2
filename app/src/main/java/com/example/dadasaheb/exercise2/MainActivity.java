package com.example.dadasaheb.exercise2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    MyAdapter customAdapter;
    RecyclerView recyclerView;
    TextView pricetv;
     TextView eurotv ;
    ArrayList<row> rows=new ArrayList<row>();
FloatingActionButton fab1,fab2,fab3,fab4,fab5;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
        sv.scrollTo(0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        for(int i=0;i<10;i++)
            if(i%2==1)
            rows.add(new row("abc",String.format("%.2f", i*21.23),String.valueOf(i*15),String.format("%.2f", i*3.9),true));
        else
            rows.add(new row("abc",String.format("%.2f", i*15.6),String.valueOf(i*12),String.format("%.2f", i*6.8),false));
         recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        customAdapter = new MyAdapter(MainActivity.this, rows);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        fab1=findViewById(R.id.floatingActionButton);
        fab2=findViewById(R.id.floatingActionButton2);
        fab3=findViewById(R.id.floatingActionButton3);
        fab4=findViewById(R.id.floatingActionButton4);
        fab5=findViewById(R.id.floatingActionButton5);
        pricetv=findViewById(R.id.pricetv);
        //eurotv=findViewById(R.id.eurotv);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
                fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                rows.clear();
                for(int i=0;i<10;i++)
                    if(i%2==1)
                        rows.add(new row("abc","฿ "+String.format("%.2f", i*21.23*2),String.valueOf(i*15),String.format("%.2f", i*3.9),true));
                    else
                        rows.add(new row("abc","฿ "+String.format("%.2f", i*15.6*2),String.valueOf(i*12),String.format("%.2f", i*6.8),false));

                recyclerView.setAdapter(new MyAdapter(MainActivity.this,rows));
                pricetv.setText(getString(R.string.btct));
                fab1.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorwhite),PorterDuff.Mode.SRC_IN);
                fab2.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab3.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab4.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);}
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // eurotv.setTextColor(Color.WHITE);
                fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
                fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                rows.clear();
                for(int i=0;i<10;i++)
                    if(i%2==1)
                        rows.add(new row("abc","ð "+String.format("%.2f", i*21.23*3),String.valueOf(i*15),String.format("%.2f", i*3.9),true));
                    else
                        rows.add(new row("abc","ð "+String.format("%.2f", i*15.6*3),String.valueOf(i*12),String.format("%.2f", i*6.8),false));

                recyclerView.setAdapter(new MyAdapter(MainActivity.this,rows));
                pricetv.setText(getString(R.string.etht));
                fab1.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab2.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorwhite),PorterDuff.Mode.SRC_IN);
                fab3.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab4.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);}
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
                fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                rows.clear();
                for(int i=0;i<10;i++)
                    if(i%2==1)
                        rows.add(new row("abc","€ "+String.format("%.2f", i*21.23*4),String.valueOf(i*15),String.format("%.2f", i*3.9),true));
                    else
                        rows.add(new row("abc","€ "+String.format("%.2f", i*15.6*4),String.valueOf(i*12),String.format("%.2f", i*6.8),false));
                recyclerView.setAdapter(new MyAdapter(MainActivity.this,rows));
                pricetv.setText(getString(R.string.eutot));
                fab1.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab2.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab3.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorwhite),PorterDuff.Mode.SRC_IN);
                fab4.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);}
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
                rows.clear();
                for(int i=0;i<10;i++)
                    if(i%2==1)
                        rows.add(new row("abc","$ "+String.format("%.2f", i*21.23*5),String.valueOf(i*15),String.format("%.2f", i*3.9),true));
                    else
                        rows.add(new row("abc","$ "+String.format("%.2f", i*15.6*5),String.valueOf(i*12),String.format("%.2f", i*6.8),false));
                recyclerView.setAdapter(new MyAdapter(MainActivity.this,rows));
                pricetv.setText(getString(R.string.usdt));
                fab1.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab2.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab3.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
                fab4.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorwhite),PorterDuff.Mode.SRC_IN);}
        });
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab5.setBackgroundColor(Color.WHITE);

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
