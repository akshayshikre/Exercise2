package com.example.dadasaheb.exercise2;

/**
 * Created by dadasaheb on 13/12/17.
 */

public class Constants {
    public interface ACTION{
        public static String MAIN_ACTION="com.example.dadasaheb.exercise2.main";
        public static String BUTTON_ACTION="com.example.dadasaheb.exercise2.button";
        public static String SETTING_ACTION="com.example.dadasaheb.exercise2.setting";
        public static String STARTFOREGROUND_ACTION="com.example.dadasaheb.exercise2.startforeground";
        public static String STOPFOREGROUND_ACTION="com.example.dadasaheb.exercise2.stopforeground";
        public static String mBroadcastArrayListAction = "com.example.dadasaheb.exercise2.arraylist";

    }
    public interface NOTIFICATION_ID{
        public static int FOREGROUND_SERVICE=999;
    }
    public static final String SERVER_URL = "https://socket-io-chat.now.sh/";
}
