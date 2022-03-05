package com.example.projectdirectory.AwesomeTMDB;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectdirectory.R;

public class Tmdbdescription extends AppCompatActivity {
private TextView textDescr;
    private TextView texttitle;
private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmdbdescription);
      textDescr =  findViewById(R.id.imdbdescription);
      texttitle =findViewById(R.id.imdbdtitle);
      imageView =findViewById(R.id.tmdbImageView1);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value1 = extras.getString("description");
            String value2 = extras.getString("title");
            String value3 = extras.getString("imagepath");

           texttitle.setText(value2);
            textDescr.setText(value1);
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w185"+value3)
                    .into(imageView);
        }

    }
}
