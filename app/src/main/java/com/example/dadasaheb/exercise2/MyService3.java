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

public class MyService3 extends Service {
    int count,
    click;
    NotificationCompat.Builder notificationbldr;
    public MyService3() {
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






    class MyTask extends AsyncTask<Void,Integer,Void> {
        Intent in;
        PendingIntent pi;

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SharedPreferences sp=getSharedPreferences(MainActivity.MY_PREFS,MODE_PRIVATE);
            count=sp.getInt("counter",0);
            Log.i("Method"," in onPreExecute"+" "+count);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Notification notification=notificationbldr.setContentText("Thread count"+count++).build();
            NotificationManager notimgr= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // noti.flags |=Notification.FLAG_AUTO_CANCEL;
            notimgr.notify(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,notification);
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
