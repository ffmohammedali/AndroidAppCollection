package com.example.projectdirectory.MusicPlayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.projectdirectory.R;

import java.io.File;
import java.util.ArrayList;
import static com.example.projectdirectory.MusicPlayer.app.ChannelId;

public class MusicService extends Service {
    private static final String LOG_TAG = "ForegroundService";
    public static MediaPlayer mediaPlayerS;
    public static ArrayList<File> songlistS;
    public static int position;
    public static int progress;
    public static Thread updateSeekBar;
    public static int duration;
public  static boolean textArt = true;
    class MusicBinder extends Binder
    {
        public  MusicService getService()
        {
            return  MusicService.this;
        }

    }
    @Nullable
    @Override
    public void onCreate() {
        super.onCreate();
        songlistS = new ArrayList<>();
    }
    public ArrayList<File> getSonglistS() {
        return songlistS;
    }

    public void setSonglistS(ArrayList<File> songlistS) {
        this.songlistS = songlistS;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    private IBinder onBind = new MusicBinder();


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent dialogIntent = new Intent(this,SongPlayer.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
        startActivity(dialogIntent);
        Bundle bundle = intent.getExtras();
        textArt =true;

        if (bundle != null) {

        songlistS = (ArrayList) bundle.getParcelableArrayList("song_File");
        position = bundle.getInt("position");

    }


        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            Uri uri = Uri.parse(songlistS.get(position).toString());
            if(mediaPlayerS ==null) {
                mediaPlayerS = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayerS.start();
                duration = mediaPlayerS.getDuration()/1000;

            }
            else {
                mediaPlayerS.release();
                mediaPlayerS = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayerS.start();
                duration = mediaPlayerS.getDuration()/1000;
                textArt =true;

            }
            if (mediaPlayerS.isPlaying())
            {
                mediaPlayerS.release();
                mediaPlayerS = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayerS.start();
                duration = mediaPlayerS.getDuration()/1000;
                textArt =true;
            }
        //    Log.i(LOG_TAG, "Received Start Foreground Intent ");
            ///   Intent
            Intent notificationIntent = new Intent(this, SongPlayer.class);
            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Intent previousIntent = new Intent(this, MusicService.class);
            previousIntent.setAction(Constants.ACTION.PREV_ACTION);
            PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                    previousIntent, 0);

            Intent playIntent = new Intent(this, MusicService.class);
            playIntent.setAction(Constants.ACTION.PLAY_ACTION );
            PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                    playIntent, 0);

            Intent nextIntent = new Intent(this, MusicService.class);
            nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
            PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                    nextIntent, 0);
            Intent cancelIntent = new Intent(this, MusicService.class);
            cancelIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            PendingIntent pcancelIntent = PendingIntent.getService(this, 0,
                    nextIntent,0);


            Notification notification = new NotificationCompat.Builder(this, ChannelId)
                    .setContentTitle("Music Player")
                    .setTicker("Music Player")
                    .setContentText(songlistS.get(position).getName().replace(".mp3", ""))
                    .setSmallIcon(R.drawable.ic_headset_black_24dp)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .addAction(android.R.drawable.ic_media_previous,
                            "Previous", ppreviousIntent)

                    .addAction(android.R.drawable.ic_media_play, "P",
                            pplayIntent)
                    .addAction(android.R.drawable.ic_media_next, "Next",
                            pnextIntent)
                   // .addAction(android.R.drawable.ic_delete,"cancel",pcancelIntent)
                    .setAutoCancel(true)
                    .build();
            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                    notification);
        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {

            Log.i(LOG_TAG, "Clicked prev");

            mediaPlayerS.stop();
            mediaPlayerS.release();
            position = (position - 1) < 0 ? (songlistS.size() - 1) : (position - 1);
            Uri uri1 = Uri.parse(songlistS.get(position).toString());
            mediaPlayerS = MediaPlayer.create(getApplicationContext(), uri1);
            mediaPlayerS.start();
            duration = mediaPlayerS.getDuration()/1000;
            textArt =true;

        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {

            if (mediaPlayerS.isPlaying()) {
                mediaPlayerS.pause();
            } else {

                mediaPlayerS.start();
            }


        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {


            Log.i(LOG_TAG, "Clicked Next");

            mediaPlayerS.stop();
            mediaPlayerS.release();
            position = (position + 1) % songlistS.size();
            Uri uri1 = Uri.parse(songlistS.get(position).toString());
            mediaPlayerS = MediaPlayer.create(getApplicationContext(), uri1);

            mediaPlayerS.start();
            duration = mediaPlayerS.getDuration()/1000;
            textArt =true;

        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {


            Log.i(LOG_TAG, "Received Stop Foreground Intent");

            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
        textArt =true;

    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        return onBind;
    }


}