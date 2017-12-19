package com.example.dadasaheb.exercise2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
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

import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String MY_PREFS= "sharedprefs";
    int count;
    Intent in;
    MyAdapter customAdapter;
    RecyclerView recyclerView;
    Typeface font;
    TextView pricetv,changedtv;
    ArrayList<Coin> coinList= new ArrayList<Coin>();
    FloatingActionButton fab1,fab2,fab3,fab4,fab5;
    TextView fab1tv,fab2tv,fab3tv,fab4tv,fab5tv;
    OkHttpClient client = new OkHttpClient();
    public static String selectedButton ="usd";
    public static String pricesort ="down";
    public static String changesort ="down";
    public static String selectedsort ="price";
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
        font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
//        Intent startintent = new Intent(this, MyService3.class);
//        startintent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
//        startService(startintent);

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


        fab1tv = (TextView)findViewById( R.id.fab1tv );
        fab2tv = (TextView)findViewById( R.id.fab2tv );
        fab3tv = (TextView)findViewById( R.id.fab3tv );
        fab4tv = (TextView)findViewById( R.id.fab4tv );
        fab5tv = (TextView)findViewById( R.id.fab5tv );
        pricetv = (TextView)findViewById( R.id.pricetv );
        changedtv = (TextView)findViewById( R.id.changedtv );
        fab1tv.setTypeface(font);
        fab2tv.setTypeface(font);
        fab3tv.setTypeface(font);
        fab4tv.setTypeface(font);
        fab5tv.setTypeface(font);
        pricetv.setTypeface(font);
        changedtv.setTypeface(font);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ScrollView scrollView =(ScrollView)findViewById(R.id.recscroll);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        ViewCompat.setNestedScrollingEnabled(scrollView, false);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
         // set the Adapter to RecyclerView
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab4 = (FloatingActionButton)findViewById(R.id.fab4);
        // fab5=findViewById(R.id.floatingActionButton5);

        //eurotv=findViewById(R.id.eurotv);

        pricetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            selectedsort="price";
            if(pricesort.equals("down")) pricesort="up";
            else if(pricesort.equals("up")) pricesort="down";
            mpricesort();
            customAdapter.notifyDataSetChanged();
                    }
        });

        changedtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            selectedsort="change";
            if(changesort.equals("down")) changesort="up";
            else if(changesort.equals("up")) changesort="down";
            mchangesort();
            customAdapter.notifyDataSetChanged();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton="usd";
                usdClick();

                }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton="euro";
                euroClick();

                }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton="btc";
                btcClick();

               }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton="inr";
                inrClick();
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

        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        if(pricesort.equals("down"))
        pricetv.setText(getString(R.string.fa_sort_down)+" "+getString(R.string.usdt));
        else if(pricesort.equals("up"))
        pricetv.setText(getString(R.string.fa_sort_up)+" "+getString(R.string.usdt));
        changedtv.setText("CHANGE");
        fab1tv.setTextColor(getResources().getColor(R.color.colorwhite));
        fab2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
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
            Intent in =new Intent(this,ChartActivity.class);
            startActivity(in);
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
            Intent in =new Intent(this,ChartActivity.class);
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
        Boolean first=true;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

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

                    Thread.sleep(2000);
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

            try {
                JSONObject job=new JSONObject(s);
                JSONArray jsonArray =job.getJSONArray("data");
                coinList.clear();
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
                /*Collections.sort(coinList, new Comparator<Coin>() {
                    @Override
                    public int compare(Coin coin, Coin t1) {
                        String s1 = coin.SortOrder;
                        String s2 = t1.SortOrder;
                        return s1.compareToIgnoreCase(s2);
                    }

                });*/
                if(selectedsort.equals("price")) mpricesort();
                else if(selectedsort.equals("change")) mchangesort();
                if(first==true) {
                    customAdapter = new MyAdapter(MainActivity.this, coinList);
                    recyclerView.setAdapter(customAdapter);
                    first=false;
                }
                else if(first==false) customAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }
    }
    void usdClick(){
        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab1tv.setTextColor(getResources().getColor(R.color.colorwhite));
        fab2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        if(selectedsort.equals("price")){
            if(pricesort.equals("down"))
                pricetv.setText(getString(R.string.fa_sort_down)+getString(R.string.usdt));
            else if(pricesort.equals("up"))
                pricetv.setText(getString(R.string.fa_sort_up)+getString(R.string.usdt));
            changedtv.setText("CHANGE");
        }
        else if(selectedsort.equals("change")){
            if(changesort.equals("down"))
                changedtv.setText(getString(R.string.fa_sort_down)+"CHANGE");
            else if(changesort.equals("up"))
                changedtv.setText(getString(R.string.fa_sort_up)+"CHANGE");
            pricetv.setText(getString(R.string.usdt));
        }
        customAdapter.notifyDataSetChanged();
    }
    void euroClick(){
        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab2tv.setTextColor(getResources().getColor(R.color.colorwhite));
        fab3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        if(selectedsort.equals("price")){
            if(pricesort.equals("down"))
                pricetv.setText(getString(R.string.fa_sort_down)+getString(R.string.eutot));
            else if(pricesort.equals("up"))
                pricetv.setText(getString(R.string.fa_sort_up)+getString(R.string.eutot));
            changedtv.setText("CHANGE");
        }
        else if(selectedsort.equals("change")){
            if(changesort.equals("down"))
                changedtv.setText(getString(R.string.fa_sort_down)+"CHANGE");
            else if(changesort.equals("up"))
                changedtv.setText(getString(R.string.fa_sort_up)+"CHANGE");
            pricetv.setText(getString(R.string.eutot));
        }
        customAdapter.notifyDataSetChanged();
    }
    void btcClick(){
        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab3tv.setTextColor(getResources().getColor(R.color.colorwhite));
        fab4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        if(selectedsort.equals("price")){
            if(pricesort.equals("down"))
                pricetv.setText(getString(R.string.fa_sort_down)+getString(R.string.btct));
            else if(pricesort.equals("up"))
                pricetv.setText(getString(R.string.fa_sort_up)+getString(R.string.btct));
            changedtv.setText("CHANGE");
        }
        else if(selectedsort.equals("change")){
            if(changesort.equals("down"))
                changedtv.setText(getString(R.string.fa_sort_down)+"CHANGE");
            else if(changesort.equals("up"))
                changedtv.setText(getString(R.string.fa_sort_up)+"CHANGE");
            pricetv.setText(getString(R.string.btct));
        }
        customAdapter.notifyDataSetChanged();
   }


    void inrClick(){
        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        fab4tv.setTextColor(getResources().getColor(R.color.colorwhite));
        if(selectedsort.equals("price")){
            if(pricesort.equals("down"))
                pricetv.setText(getString(R.string.fa_sort_down)+getString(R.string.inrt));
            else if(pricesort.equals("up"))
                pricetv.setText(getString(R.string.fa_sort_up)+getString(R.string.inrt));
            changedtv.setText("CHANGE");
        }
        else if(selectedsort.equals("change")){
            if(changesort.equals("down"))
                changedtv.setText(getString(R.string.fa_sort_down)+"CHANGE");
            else if(changesort.equals("up"))
                changedtv.setText(getString(R.string.fa_sort_up)+"CHANGE");
            pricetv.setText(getString(R.string.inrt));
        }
        customAdapter.notifyDataSetChanged();
    }
    void mpricesort(){
        Collections.sort(coinList, new Comparator<Coin>() {
            @Override
            public int compare(Coin coin, Coin t1) {
                String s1="",s2="";
                if(selectedButton.equals("usd")){
                    s1 = coin.Price_USD;
                    s2 = t1.Price_USD;
                }
                else if(selectedButton.equals("euro")){
                    s1 = coin.Price_EUR;
                    s2 = t1.Price_EUR;
                }
                else if(selectedButton.equals("btc")){
                    s1 = coin.Price_BTC;
                    s2 = t1.Price_BTC;
                }
                else if(selectedButton.equals("inr")){
                    s1 = coin.Price_ETH;
                    s2 = t1.Price_ETH;
                }
                if(pricesort.equals("down")) {
                    return -s1.compareToIgnoreCase(s2);
                }
                else if (pricesort.equals("up")) {
                    return s1.compareToIgnoreCase(s2);
                }
                else return 0;
            }

        });
        if(pricesort.equals("down")){

            if(selectedButton.equals("usd"))
                pricetv.setText(getString(R.string.fa_sort_down)+" "+getString(R.string.usdt));
            else if(selectedButton.equals("euro"))
                pricetv.setText(getString(R.string.fa_sort_down)+" "+getString(R.string.eutot));
            else if(selectedButton.equals("btc"))
                pricetv.setText(getString(R.string.fa_sort_down)+" "+getString(R.string.btct));
            else if(selectedButton.equals("inr"))
                pricetv.setText(getString(R.string.fa_sort_down)+" "+getString(R.string.inrt));
        }
        else if(pricesort.equals("up")){

            if(selectedButton.equals("usd"))
                pricetv.setText(getString(R.string.fa_sort_up)+" "+getString(R.string.usdt));
            else if(selectedButton.equals("euro"))
                pricetv.setText(getString(R.string.fa_sort_up)+" "+getString(R.string.eutot));
            else if(selectedButton.equals("btc"))
                pricetv.setText(getString(R.string.fa_sort_up)+" "+getString(R.string.btct));
            else if(selectedButton.equals("inr"))
                pricetv.setText(getString(R.string.fa_sort_up)+" "+getString(R.string.inrt));
        }
        changedtv.setText("CHANGE");
    }
    void mchangesort(){
        Collections.sort(coinList, new Comparator<Coin>() {
            @Override
            public int compare(Coin coin, Coin t1) {
                String s1="",s2="";
                if(selectedButton.equals("usd")){
                    s1 = coin.Change_USD;
                    s2 = t1.Change_USD;

                }
                else if(selectedButton.equals("euro")){
                    s1 = coin.Change_EUR;
                    s2 = t1.Change_EUR;
                }
                else if(selectedButton.equals("btc")){
                    s1 = coin.Change_BTC;
                    s2 = t1.Change_BTC;
                }
                else if(selectedButton.equals("inr")){
                    s1 = coin.Change_ETH;
                    s2 = t1.Change_ETH;
                }
                if(changesort.equals("down")) {
                    return -s1.compareToIgnoreCase(s2);
                }
                else if (changesort.equals("up")) {
                    return s1.compareToIgnoreCase(s2);
                }
                else return 0;
            }
        });
        if(changesort.equals("down")){

            if(selectedButton.equals("usd"))
                changedtv.setText(getString(R.string.fa_sort_down)+" CHANGE");
            else if(selectedButton.equals("euro"))
                changedtv.setText(getString(R.string.fa_sort_down)+" CHANGE");
            else if(selectedButton.equals("btc"))
                changedtv.setText(getString(R.string.fa_sort_down)+" CHANGE");
            else if(selectedButton.equals("inr"))
                changedtv.setText(getString(R.string.fa_sort_down)+" CHANGE");
        }
        else if(changesort.equals("up")){

            if(selectedButton.equals("usd"))
                changedtv.setText(getString(R.string.fa_sort_up)+" CHANGE");
            else if(selectedButton.equals("euro"))
                changedtv.setText(getString(R.string.fa_sort_up)+" CHANGE");
            else if(selectedButton.equals("btc"))
                changedtv.setText(getString(R.string.fa_sort_up)+" CHANGE");
            else if(selectedButton.equals("inr"))
                changedtv.setText(getString(R.string.fa_sort_up)+" CHANGE");
        }

        if(selectedButton.equals("usd"))
            pricetv.setText(getString(R.string.usdt));
        else if(selectedButton.equals("euro"))
            pricetv.setText(getString(R.string.eutot));
        else if(selectedButton.equals("btc"))
            pricetv.setText(getString(R.string.btct));
        else if(selectedButton.equals("inr"))
            pricetv.setText(getString(R.string.inrt));
    }
}
