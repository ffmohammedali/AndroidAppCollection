package com.example.projectdirectory.MusicPlayer;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

public class app extends Application {
    public static final  String ChannelId = "examplechannel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();

    }
    private void createNotification()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel serviceChannel = new NotificationChannel(ChannelId,
                    "example service", NotificationManager.IMPORTANCE_LOW
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }

    }
}
