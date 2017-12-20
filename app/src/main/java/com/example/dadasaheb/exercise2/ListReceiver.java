package com.example.dadasaheb.exercise2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ListReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(MainActivity.appAvailable.equals("first")||MainActivity.appAvailable.equals("true"))
            if (intent.getStringExtra("arraylist").equals("changed"))
            {
                if (MainActivity.selectedsort.equals("price")) MainActivity.mpricesort();
                else if (MainActivity.selectedsort.equals("change")) MainActivity.mchangesort();
                MainActivity.customAdapter.notifyDataSetChanged();
                Log.i("receiver","chanaged "+MainActivity.appAvailable);
            }
    }
}
