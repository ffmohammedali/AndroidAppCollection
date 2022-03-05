package com.example.projectdirectory.MusicPlayer;

public class Constants {
    public interface ACTION {
        public static String MAIN_ACTION = "com.action.main";
        public static String PREV_ACTION = "com.action.prev";
        public static String PLAY_ACTION = "com.action.play";
        public static String NEXT_ACTION = "com.action.next";
        public static String NEXT_CANCEL = "com.action.cancel";
        public static String STARTFOREGROUND_ACTION = "com.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 100;
    }
}