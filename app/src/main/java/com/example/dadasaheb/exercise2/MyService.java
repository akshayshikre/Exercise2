package com.example.dadasaheb.exercise2;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dadasaheb on 12/12/17.
 */

public class MyService extends Service {


     int count;
    public MyService(){

    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Method"," in onCreate"+" "+count);
        new MyTask().execute();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Method"," in onStartCommand"+" "+count);
        return START_NOT_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        SharedPreferences sp=getSharedPreferences(MainActivity.MY_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putInt("counter",count);
        ed.apply();
        Log.i("Method"," in onTaskRemoved"+" "+count);
        super.onTaskRemoved(rootIntent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyTask extends AsyncTask<Void,Integer,Void>{
        Intent in;
        PendingIntent pi;
        Notification noti;
        NotificationManager notimgr;
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SharedPreferences sp=getSharedPreferences(MainActivity.MY_PREFS,MODE_PRIVATE);
            count=sp.getInt("counter",0);
            Log.i("Method"," in onPreExecute"+" "+count);
           in =new Intent(MyService.this,MainActivity.class);
            pi=PendingIntent.getActivity(MyService.this,0,in,0);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Notification.Builder notib=new Notification.Builder(MyService.this)
                    .setContentTitle("Notification"+values[0])
                    .setContentText("Message"+values[0])
                    .setSmallIcon(R.drawable.btcicons)
                    .setContentIntent(pi);

            Notification noti=notib.build();
            NotificationManager notimgr= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            noti.flags |=Notification.FLAG_AUTO_CANCEL;
            notimgr.notify(0,noti);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0;i<999;i++)
                try {
                    Thread.sleep(1000);
                    Log.i("count"," "+i+" "+count);
                    count++;
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            return null;
        }
    }
}
