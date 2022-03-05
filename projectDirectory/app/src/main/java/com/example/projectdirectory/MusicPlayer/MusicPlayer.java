package com.example.projectdirectory.MusicPlayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectdirectory.R;

import java.io.File;
import java.util.ArrayList;

public class MusicPlayer extends AppCompatActivity implements CustomAdapterMusicPlayer.OnMusicClick {

    RecyclerView recyclerView;
    private TextView textView;
    private ArrayList<File> arrayList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<File>();
        setContentView(R.layout.activity_music_player);

        textView = findViewById(R.id.fortest1);
        recyclerView = findViewById(R.id.recycleviewForMusic);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);

        }
        linearLayoutManager = new LinearLayoutManager(this);
        arrayList = readMusics(Environment.getExternalStorageDirectory());
        Log.i("activity", "onCreate: " + arrayList.size());
        System.out.println(arrayList.size() + "  size of file");
        CustomAdapterMusicPlayer adapter = new CustomAdapterMusicPlayer(arrayList, this, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }

    private ArrayList<File> readMusics(File root) {
        ArrayList<File> arrayList = new ArrayList<File>();
        File files[] = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                arrayList.addAll(readMusics(file));

            } else if (file.getName().endsWith(".mp3") || file.getName().endsWith(".flac")) {

                arrayList.add(file);


            }
        }
        return arrayList;
    }

    @Override
    public void onClickMusic(int position) {


        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("song_File", arrayList);
        intent.putExtra("position", position);
        intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        startService(intent);


    }
}
