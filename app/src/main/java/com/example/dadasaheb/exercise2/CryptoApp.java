package com.example.dadasaheb.exercise2;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by admin on 26/12/17.
 */

public class CryptoApp extends Application {
    private Socket mSocket;
    {
        try {
           // mSocket = IO.socket(Constants.SERVER_URL);
            mSocket = IO.socket(MainActivity.url2);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}
