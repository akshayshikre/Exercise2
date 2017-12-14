package com.example.dadasaheb.exercise2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

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

import static com.example.dadasaheb.exercise2.MainActivity.url;

public class MyService2 extends Service {
    ArrayList<Coin> coinList= new ArrayList<Coin>();
    int count,
            click;
    NotificationCompat.Builder notificationbldr;
    public MyService2() {
    }

    @Override
    public void onCreate() {
        Log.i("Method","onCreate");


        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);


        Intent previousIntent = new Intent(this, MyService3.class);
        previousIntent.setAction(Constants.ACTION.BUTTON_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0, previousIntent, 0);


        Intent nextIntent = new Intent(this, SettingActivity.class);
        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        nextIntent.setAction(Constants.ACTION.SETTING_ACTION);
        PendingIntent pnextIntent = PendingIntent.getActivity(this, 0, nextIntent, 0);

        notificationbldr= new NotificationCompat.Builder(this)
                .setContentTitle("Exercise 2 content title")
                .setTicker("Exercise 2 ticker")
                .setSmallIcon(R.drawable.ic_menu_camera)
                //.setLargeIcon(Bitmap.createScaledBitmap(Drawable.createFromPath(),128,128,false)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_input_add, "Click", ppreviousIntent)
                // .addAction(android.R.drawable.ic_media_play, "Play", pplayIntent)
                .addAction(android.R.drawable.ic_menu_manage, "Setting", pnextIntent);


        new MyTask().execute();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Method","onStartCommand");



/*******************************************/

        if(intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            Log.i("onStartCommand", "Received startforeground intent");
            Notification notification=notificationbldr.setContentText("Started Clicked"+click++).build();
            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, notificationbldr.build());

        }
        else if(intent.getAction().equals(Constants.ACTION.BUTTON_ACTION)){
            Log.i("onStartCommand","PREV_ACTION");
            Notification notification=notificationbldr.setContentText("Clicked"+click++).build();
            NotificationManager notimgr= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // noti.flags |=Notification.FLAG_AUTO_CANCEL;
            notimgr.notify(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,notification);

        }

        else if(intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)){
            Log.i("onStartCommand","STOPFOREGROUND_ACTION");
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i("Method","onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
        Log.i("Method","onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }






    class MyTask extends AsyncTask<String,String,String> {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String s=values[0];
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

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... strings) {

            for (int i = 0; i < 999; i++)
                try {
                    Thread.sleep(10000);
                    Log.i("count", " " + i + " " + count);
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    count++;
                    publishProgress(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return null;
        }




        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SharedPreferences sp=getSharedPreferences(MainActivity.MY_PREFS,MODE_PRIVATE);
            count=sp.getInt("counter",0);
            Log.i("Method"," in onPreExecute"+" "+count);
        }

    }


}
