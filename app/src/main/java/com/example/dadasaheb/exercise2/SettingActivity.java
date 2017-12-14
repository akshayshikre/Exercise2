package com.example.dadasaheb.exercise2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SettingActivity extends AppCompatActivity
        {
     public static final String MY_PREFS= "sharedprefs";
     Intent in;
     MyAdapter customAdapter;
     RecyclerView recyclerView;
     TextView pricetv;
     TextView eurotv ;
     ArrayList<Coin> coinList= new ArrayList<Coin>();
     OkHttpClient client = new OkHttpClient();
     public static String selectedButton ="btc";
     public String url= "https://cryptocurrencyapp.herokuapp.com/getCoinList";
     com.cuboid.cuboidcirclebutton.CuboidButton cb1,cb2,cb3,cb4;
//     @Override
//     public void onWindowFocusChanged(boolean hasFocus) {
//         //ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
//         //sv.scrollTo(0, 0);
//     }

//     @Override
//     protected void onDestroy() {
//         stopService(in);
//         super.onDestroy();
//     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.temp2);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

       /*  Intent startintent = new Intent(this, MyService3.class);
         startintent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
         startService(startintent);*/



         /**buit code**/
//         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//         fab.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//
//                 Intent in=new Intent(SettingActivity.this,AboutUsActivity.class);
//                 startActivity(in);
//             }
//         });
//




         recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
         recyclerView.setLayoutManager(linearLayoutManager);
         // call the constructor of CustomAdapter to send the reference and data to Adapter
         // set the Adapter to RecyclerView
         cb1 = (CuboidButton) findViewById(R.id.cb1);
         cb2 = (CuboidButton) findViewById(R.id.cb2);
         cb3 = (CuboidButton) findViewById(R.id.cb3);
         cb4 = (CuboidButton) findViewById(R.id.cb4);
         // fab5=findViewById(R.id.floatingActionButton5);
         pricetv = (TextView) findViewById(R.id.pricetv);
         //eurotv=findViewById(R.id.eurotv);
         cb1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectedButton="btc";
                 btcClick();
             }
         });

         cb2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectedButton="eth";
                 ethClick();
             }
         });
         cb3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectedButton="euro";
                 euroClick();
             }
         });
         cb4.setOnClickListener(new View.OnClickListener() {
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


public class OkHttpHandler extends AsyncTask<String,String,String> {

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS).build();

    @Override
    protected String doInBackground(String...params) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s!=null)
            Log.i("data",s);
        else Log.i("data","data not found");
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
                Collections.sort(coinList, new Comparator<Coin>() {
                    @Override
                    public int compare(Coin coin, Coin t1) {
                        String s1 = coin.SortOrder;
                        String s2 = t1.SortOrder;
                        return s1.compareToIgnoreCase(s2);
                    }

                });
                customAdapter = new MyAdapter(SettingActivity.this, coinList);
                recyclerView.setAdapter(customAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
    void btcClick(){
        cb1.setCircle_color(R.color.colorFabTint);
        cb2.setCircle_color(R.color.colorwhite);
        cb3.setCircle_color(R.color.colorwhite);
        cb4.setCircle_color(R.color.colorwhite);
       //        coinList.clear();
//        for(int i=0;i<20;i++)
//            if(i%2==1)
//                rows.add(new row("abc","฿ "+String.format("%.2f", i*21.23*2),String.format("%.2f", i*3.9),true));
//            else
//                rows.add(new row("abc","฿ "+String.format("%.2f", i*15.6*2),String.format("%.2f", i*6.8),false));

        recyclerView.setAdapter(new MyAdapter(SettingActivity.this,coinList));
        pricetv.setText(getString(R.string.btct));
        cb1.setTextColor(0xffffff);
        cb2.setTextColor(0x1c2030);
        cb3.setTextColor(0x1c2030);
        cb4.setTextColor(0x1c2030);
   }
    void ethClick(){
        cb1.setCircle_color(R.color.colorwhite);
        cb2.setCircle_color(R.color.colorFabTint);
        cb3.setCircle_color(R.color.colorwhite);
        cb4.setCircle_color(R.color.colorwhite);
        recyclerView.setAdapter(new MyAdapter(SettingActivity.this,coinList));
        pricetv.setText(getString(R.string.etht));
        cb1.setTextColor(0x1c2030);
        cb2.setTextColor(0xffffff);
        cb3.setTextColor(0x1c2030);
        cb4.setTextColor(0x1c2030);
    }
    void euroClick(){
        cb1.setCircle_color(R.color.colorwhite);
        cb2.setCircle_color(R.color.colorwhite);
        cb3.setCircle_color(R.color.colorFabTint);
        cb4.setCircle_color(R.color.colorwhite); recyclerView.setAdapter(new MyAdapter(SettingActivity.this,coinList));
        pricetv.setText(getString(R.string.eutot));
        cb1.setTextColor(0x1c2030);
        cb2.setTextColor(0x1c2030);
        cb3.setTextColor(0xffffff);
        cb4.setTextColor(0x1c2030);
   }

    void usdClick(){
        cb1.setCircle_color(R.color.colorwhite);
        cb2.setCircle_color(R.color.colorwhite);
        cb3.setCircle_color(R.color.colorwhite);
        cb4.setCircle_color(R.color.colorFabTint);
        recyclerView.setAdapter(new MyAdapter(SettingActivity.this,coinList));
        pricetv.setText(getString(R.string.usdt));
        cb1.setTextColor(0x1c2030);
        cb2.setTextColor(0x1c2030);
        cb3.setTextColor(0x1c2030);
        cb4.setTextColor(0xffffff);
         }

}
