package com.example.projectdirectory.MusicPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectdirectory.R;

import java.io.File;
import java.util.ArrayList;

public class SongPlayer extends AppCompatActivity implements View.OnClickListener {
    private TextView musicTitle;
    public static Integer position;
    private MediaPlayer mediaPlayer;
    public static SeekBar seekBar;
    private Thread updateSeekBar;
    private ArrayList<File> songlist;
    private Button pause;
    private Button forward;
    private Button backward;
    private ImageView imageView;
    private Bitmap bm;
    private Intent intentTest;
    private static boolean playFlag;
    private ServiceConnection serviceConnection;
    private MusicService musicService;
    private boolean isServiceBound = false;
    private Intent serviceIntent;
    public static int posForTI;
    public static int currentPos;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songlist = MusicService.songlistS;
        serviceIntent = new Intent(this, MusicService.class);
        setContentView(R.layout.activity_song_player);
        imageView = findViewById(R.id.songPlayerImage);
        forward = findViewById(R.id.musicPlayerForward);
        backward = findViewById(R.id.musicPlayerBackward);
        pause = findViewById(R.id.musicPlayerPlay);
        musicTitle = findViewById(R.id.songPlayerTitle);
        seekBar = findViewById(R.id.songPlayerSeekBar);

        if (!isServiceBound)
            setBindService();
        MusicService.textArt =true;
       seekBar.setMax(MusicService.mediaPlayerS.getDuration()/1000);
       ////////
         final Handler mHandler = new Handler();

        SongPlayer.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(MusicService.mediaPlayerS != null){
                    int mCurrentPosition = MusicService.mediaPlayerS.getCurrentPosition() / 1000;

                    SongPlayer.seekBar.setProgress(mCurrentPosition);
                }
                if(MusicService.textArt==true || MusicService.textArt==false)
                {
                    musicTitle.setText(MusicService.songlistS.get(MusicService.position).getName().replace(".mp3", " "));
                    Uri uri = Uri.parse(MusicService.songlistS.get(MusicService.position).toString());
                    ////////
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(MusicService.songlistS.get(MusicService.position).getPath());
                    try {

                        byte[] bytes = mediaMetadataRetriever.getEmbeddedPicture();

                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                          //  Glide.with(getApplicationContext()).asBitmap().load(bitmap).into(imageView);

                        if (bytes != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                        else
                        {
                            imageView.setImageResource(0);
                        }



                        Log.i("bitmap10", "run: "+bitmap);
                    }

                    catch (Exception e)
                    {
                        Log.i("METADATA", "artSet: ");
                    }
                    MusicService.textArt = false;
                }
                if (MusicService.mediaPlayerS.isPlaying())
                {
                    pause.setBackgroundResource(R.drawable.ic_pause_black_24dp);

                }
                else {
                    pause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);

                }
                mHandler.postDelayed(this, 100
                );
            }
        });


        //////////
      //  updateSeekBar.start();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            songlist = (ArrayList) bundle.getParcelableArrayList("song_File");
            position = bundle.getInt("position");
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

              //  MusicService.mediaPlayerS.seekTo(seekBar.getProgress());

            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(MusicService.mediaPlayerS != null && fromUser){
                    MusicService.mediaPlayerS.seekTo(progress * 1000);
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(SongPlayer.this, MusicService.class);
                startIntent.setAction(Constants.ACTION.NEXT_ACTION);


                startService(startIntent);
                seekBar.setProgress(0);
                seekBar.setMax(MusicService.mediaPlayerS.getDuration()/1000);

                MusicService.textArt = true;

            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(SongPlayer.this, MusicService.class);
                startIntent.setAction(Constants.ACTION.PREV_ACTION);

                startService(startIntent);

                seekBar.setProgress(0);
                seekBar.setMax(MusicService.mediaPlayerS.getDuration()/1000);
                MusicService.textArt =true;

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(SongPlayer.this, MusicService.class);
                startIntent.setAction(Constants.ACTION.PLAY_ACTION);
                artSet();
                startService(startIntent);
                if (MusicService.mediaPlayerS.isPlaying()) {
                    pause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);

                } else {
                    pause.setBackgroundResource(R.drawable.ic_pause_black_24dp);;
                }

            }
        });
    }

    private void setBindService() {
        if (serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MusicService.MusicBinder serviceBinder = (MusicService.MusicBinder) service;
                    musicService = serviceBinder.getService();
                    isServiceBound = true;

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceBound = false;

                }
            };
            bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }
    /*
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_player);
        imageView = findViewById(R.id.songPlayerImage);
        forward = findViewById(R.id.musicPlayerForward);
        backward = findViewById(R.id.musicPlayerBackward);
        pause = findViewById(R.id.musicPlayerPlay);
        musicTitle = findViewById(R.id.songPlayerTitle);
        seekBar = findViewById(R.id.songPlayerSeekBar);

        updateSeekBar = new Thread()
        {
            @Override
            public void run() {
                int totalDuration = mediaPlayer.getDuration();
                int currentPosition = 0;
                while (currentPosition < totalDuration) {
                    try {
                        sleep(500);
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            songlist = (ArrayList) bundle.getParcelableArrayList("song_File");
            position = bundle.getInt("position");
            musicTitle.setText(songlist.get(position).getName().toString());

            Log.i("051219", "onCreate: "+ position);

            Uri uri = Uri.parse(songlist.get(position).toString());
            ////////
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(songlist.get(position).getPath());
            Log.i("image ", "onCreate: " + songlist.get(position).getAbsolutePath());

            try {
                byte[] bytes = mediaMetadataRetriever.getEmbeddedPicture();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                Glide.with(this).asBitmap().load(bitmap).into(imageView);
            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            }


            ///////
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();

            seekBar.setMax(mediaPlayer.getDuration());
            updateSeekBar.start();
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            });
            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seekBar.setMax(mediaPlayer.getDuration());
                    if (mediaPlayer.isPlaying()) {
                        pause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                        mediaPlayer.pause();
                        intentTest = new Intent(getApplicationContext(),MusicService.class);
                        intentTest.putExtra("song_File", songlist);
                      intentTest.putExtra("position", position);
                        startService(intentTest);
                    } else {
                        pause.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                        intentTest = new Intent(getApplicationContext(),MusicService.class);
                        intentTest.putExtra("song_File", songlist);
                         intentTest.putExtra("position", position);
                        mediaPlayer.start();
                    }
                }
            });

            forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mediaPlayer.stop();
                    mediaPlayer.release();
                    position = (position + 1) % songlist.size();
                    Uri uri1 = Uri.parse(songlist.get(position).toString());
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), uri1);
                    musicTitle.setText(songlist.get(position).getName().toString());
                    seekBar.setProgress(0);
                    seekBar.setMax(mediaPlayer.getDuration());

                        mediaPlayer.start();

                  //  updateSeekBar.start();
                    Intent intent = new Intent(getApplicationContext(),MusicService.class);
                    intent.putExtra("song_File", songlist);
                    intent.putExtra("position", position);
                    //////////
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(songlist.get(position).getPath());
                    Log.i("image ", "onCreate: " + songlist.get(position).getAbsolutePath());

                    try {
                        byte[] bytes = mediaMetadataRetriever.getEmbeddedPicture();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        Glide.with(getApplicationContext()).asBitmap().load(bitmap).into(imageView);
                    } catch (Exception e) {
                        Log.e("TAG", e.getMessage());
                    }

                    ////////

                    startService(intent);
                }
            });
            backward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mediaPlayer.stop();
                    mediaPlayer.release();
                    position = (position - 1) < 0 ? (songlist.size() - 1) : (position - 1);
                    Uri uri1 = Uri.parse(songlist.get(position).toString());
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), uri1);
                    musicTitle.setText(songlist.get(position).getName().toString());
                    /////


                    //////////
                    //  imageView.setBackground(mediaPlayer);
                    seekBar.setProgress(0);
                    seekBar.setMax(mediaPlayer.getDuration());

                        mediaPlayer.start();
              //      updateSeekBar.start();
                    Intent intent = new Intent(getApplicationContext(),MusicService.class);
                    intent.putExtra("song_File", songlist);
                    intent.putExtra("position", position);
                    /////////////
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(songlist.get(position).getPath());
                    Log.i("image ", "onCreate: " + songlist.get(position).getAbsolutePath());

                    try {
                        byte[] bytes = mediaMetadataRetriever.getEmbeddedPicture();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        Glide.with(getApplicationContext()).asBitmap().load(bitmap).into(imageView);
                    } catch (Exception e) {
                        Log.e("TAG", e.getMessage());
                    }
                    ////////
                    startService(intent);

                }
            });

        }

        Intent intent = new Intent(this,MusicService.class);
        intent.putExtra("song_File", songlist);
        intent.putExtra("position", position);
        startService(intent);
    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
    if (!MusicService.mediaPlayerS.isPlaying()) {
      stopService(serviceIntent);
    }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {

    }
    public  void artSet()
    {

    }


}