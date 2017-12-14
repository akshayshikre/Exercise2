package com.example.dadasaheb.exercise2;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.SupportActivity;
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
import android.view.ViewOutlineProvider;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MY_PREFS= "sharedprefs";
    int count;
    Intent in;
    MyAdapter customAdapter;
    RecyclerView recyclerView;
    TextView pricetv;
    ArrayList<Coin> coinList= new ArrayList<Coin>();
    FloatingActionButton fab1,fab2,fab3,fab4,fab5;
    //CuboidButton cb1,cb2,cb3,cb4;
    OkHttpClient client = new OkHttpClient();
    public static String selectedButton ="btc";
    public static String url= "https://cryptocurrencyapp.herokuapp.com/getCoinList";

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
        //sv.scrollTo(0, 0);
    }
/*
    @Override
    protected void onDestroy() {
        stopService(in);
        super.onDestroy();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent startintent = new Intent(this, MyService3.class);
        startintent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        startService(startintent);



        /**buit code**/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent in=new Intent(MainActivity.this,AboutUsActivity.class);
                startActivity(in);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
/***built code end***/




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
         // set the Adapter to RecyclerView
        fab1 = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab2 = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        fab3 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        fab4 = (FloatingActionButton)findViewById(R.id.floatingActionButton4);
        // fab5=findViewById(R.id.floatingActionButton5);
        pricetv = (TextView) findViewById(R.id.pricetv);
        //eurotv=findViewById(R.id.eurotv);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton="btc";
                btcClick();
                }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton="eth";
                ethClick();
                }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton="euro";
                euroClick();
               }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton="usd";
                usdClick();
            }
        });
       /* fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab5.setBackgroundColor(Color.WHITE);

            }
        });*/
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);



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

        if (id == R.id.home) {
           Intent in =new Intent(this,MainActivity.class);
           startActivity(in);

        } else if (id == R.id.settings) {
            Intent in =new Intent(this,SettingActivity.class);
            startActivity(in);
        } else if (id == R.id.testdata) {
            Intent in =new Intent(this,TestDataActivity.class);
            startActivity(in);
        } else if (id == R.id.aboutus) {
            Intent in =new Intent(this,AboutUsActivity.class);
            startActivity(in);
        } else if (id == R.id.support) {
            Intent in =new Intent(this,MySupportActivity.class);
            startActivity(in);
        } else if (id == R.id.help) {
            Intent in =new Intent(this,HelpActivity.class);
            startActivity(in);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class OkHttpHandler extends AsyncTask<String,String,String>{



        @Override
        protected String doInBackground(String...params) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS).build();


            for(int i=0;i<999;i++)
                try {

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Thread.sleep(1000);
                    Log.i("count"," "+i+" "+count);
                    count++;
                    publishProgress(response.body().string());

                } catch (Exception e) {
                    e.printStackTrace();
                }


            return "end";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String s=values[0];

            if(s!=null)
                Log.i("data",s);
            else Log.i("data","data not found");
            coinList.clear();
            try {
                JSONObject job=new JSONObject(s);
                JSONArray jsonArray =job.getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject json_obj = jsonArray.getJSONObject(i);
                    coinList.add(new Coin(
                            //    "Id":"4321","Url":"/coins/42/overview",
//            "ImageUrl":"https://www.cryptocompare.com/media/12318415/42.png",
//            "Name":"42","Symbol":"42","CoinName":"42 Coin","FullName":"42 Coin (42)",
//            "Algorithm":"Scrypt","ProofType":"PoW/PoS","FullyPremined":"0",
//            "TotalCoinSupply":"42","PreMinedValue":"N/A",
//            "TotalCoinsFreeFloat":"N/A","SortOrder":"34","Sponsored":false,
//            "Price_USD":38245.779,"Change_USD":-11.7,"Volume_USD":0,"Price_EUR":32180.031,
//            "Change_EUR":-11.7,"Volume_EUR":0,"Price_BTC":2.34,"Change_BTC":-11.7,
//            "Volume_BTC":0.15,"Price_ETH":52.3139,"Change_ETH":-11.7,"Volume_ETH":0},
                            json_obj.getString("Id"),
                            json_obj.getString("Url"),
                            json_obj.getString("ImageUrl"),
                            json_obj.getString("Name"),
                            json_obj.getString("Symbol"),
                            json_obj.getString("CoinName"),
                            json_obj.getString("FullName"),
                            json_obj.getString("Algorithm"),
                            json_obj.getString("ProofType"),
                            json_obj.getString("FullyPremined"),
                            json_obj.getString("TotalCoinSupply"),
                            json_obj.getString("PreMinedValue"),
                            json_obj.getString("TotalCoinsFreeFloat"),
                            json_obj.getString("SortOrder"),
                            json_obj.getString("Price_USD"),
                            json_obj.getString("Change_USD"),
                            json_obj.getString("Volume_USD"),
                            json_obj.getString("Price_EUR"),
                            json_obj.getString("Change_EUR"),
                            json_obj.getString("Volume_EUR"),
                            json_obj.getString("Price_BTC"),
                            json_obj.getString("Change_BTC"),
                            json_obj.getString("Volume_BTC"),
                            json_obj.getString("Price_ETH"),
                            json_obj.getString("Change_ETH"),
                            json_obj.getString("Volume_ETH"),
                            json_obj.getString("Sponsored").equals("true")?true:false
                    ));

                }//end for
                Coin c;
                for(int i=0;i<coinList.size();i++){
                    c=coinList.get(i);
                    Log.i("coinList data",
                            c.Id+" "+
                                    c.Url+" "+
                                    c.ImageUrl+" "+
                                    c.Name+" "+
                                    c.Symbol+" "+
                                    c.CoinName+" "+
                                    c.FullName+" "+
                                    c.Algorithm+" "+
                                    c.ProofType+" "+
                                    c.FullyPremined+" "+
                                    c.TotalCoinSupply+" "+
                                    c.PreMinedValue+" "+
                                    c.TotalCoinsFreeFloat+" "+
                                    c.SortOrder+" "+
                                    c.Price_USD+" "+
                                    c.Change_USD+" "+
                                    c.Volume_USD+" "+
                                    c.Price_EUR+" "+
                                    c.Change_EUR+" "+
                                    c.Volume_EUR+" "+
                                    c.Price_BTC+" "+
                                    c.Change_BTC+" "+
                                    c.Volume_BTC+" "+
                                    c.Price_ETH+" "+
                                    c.Change_ETH+" "+
                                    c.Volume_ETH+" "+
                                    c.Sponsored+" "
                    );
                }
                Collections.sort(coinList, new Comparator<Coin>() {
                    @Override
                    public int compare(Coin coin, Coin t1) {
                        String s1 = coin.SortOrder;
                        String s2 = t1.SortOrder;
                        return s1.compareToIgnoreCase(s2);
                    }

                });
                customAdapter = new MyAdapter(MainActivity.this, coinList);
                recyclerView.setAdapter(customAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }
    }
    void btcClick(){
        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
//        coinList.clear();
//        for(int i=0;i<20;i++)
//            if(i%2==1)
//                rows.add(new row("abc","฿ "+String.format("%.2f", i*21.23*2),String.format("%.2f", i*3.9),true));
//            else
//                rows.add(new row("abc","฿ "+String.format("%.2f", i*15.6*2),String.format("%.2f", i*6.8),false));

        recyclerView.setAdapter(new MyAdapter(MainActivity.this,coinList));
        pricetv.setText(getString(R.string.btct));
        fab1.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorwhite),PorterDuff.Mode.SRC_IN);
        fab2.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab3.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab4.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
    }
    void ethClick(){
        // eurotv.setTextColor(Color.WHITE);
        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
//        rows.clear();
//        for(int i=0;i<20;i++)
//            if(i%2==1)
//                rows.add(new row("abc","ð "+String.format("%.2f", i*21.23*3),String.format("%.2f", i*3.9),true));
//            else
//                rows.add(new row("abc","ð "+String.format("%.2f", i*15.6*3),String.format("%.2f", i*6.8),false));
//
        recyclerView.setAdapter(new MyAdapter(MainActivity.this,coinList));
        pricetv.setText(getString(R.string.etht));
        fab1.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab2.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorwhite),PorterDuff.Mode.SRC_IN);
        fab3.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab4.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
    }
    void euroClick(){
        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
//        rows.clear();
//        for(int i=0;i<20;i++)
//            if(i%2==1)
//                rows.add(new row("abc","€ "+String.format("%.2f", i*21.23*4),String.format("%.2f", i*3.9),true));
//            else
//                rows.add(new row("abc","€ "+String.format("%.2f", i*15.6*4),String.format("%.2f", i*6.8),false));
        recyclerView.setAdapter(new MyAdapter(MainActivity.this,coinList));
        pricetv.setText(getString(R.string.eutot));
        fab1.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab2.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab3.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorwhite),PorterDuff.Mode.SRC_IN);
        fab4.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
    }
    void usdClick(){
        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
//        coinList.clear();
//        for(int i=0;i<20;i++)
//            if(i%2==1)
//                rows.add(new row("abc","$ "+String.format("%.2f", i*21.23*5),String.format("%.2f", i*3.9),true));
//            else
//                rows.add(new row("abc","$ "+String.format("%.2f", i*15.6*5),String.format("%.2f", i*6.8),false));
        recyclerView.setAdapter(new MyAdapter(MainActivity.this,coinList));
        pricetv.setText(getString(R.string.usdt));
        fab1.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab2.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab3.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
        fab4.getDrawable().mutate().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorwhite),PorterDuff.Mode.SRC_IN);
    }

}
