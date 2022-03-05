package com.example.projectdirectory.async_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectdirectory.R;

import java.util.Random;

public class SimpleAsyncTaskActivity extends AppCompatActivity {

 private Button btn;
 private ProgressBar prgs;
 private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        btn = findViewById(R.id.progressBT);
        prgs =findViewById(R.id.progressBar);
        txtView =findViewById(R.id.progressText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ProgressBarThread  pr = new ProgressBarThread();
                 prgs.setVisibility(View.VISIBLE);
                 pr.execute();

            }
        });
    }
    class ProgressBarThread extends AsyncTask<Void,Integer,Integer> {
String TAG = "activity";


        @Override
        protected Integer doInBackground(Void... voids) {
           for (int c =0 ; c<=100 ; c++)
           {

              publishProgress(c);
              try {

                  Thread.sleep ((int)(new Random().nextInt(2000)));
              }
              catch (Exception e)
              {
                  Log.i(TAG, "doInBackground: ");
              }
           }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            prgs.setVisibility(View.INVISIBLE);
            txtView.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Completed",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            prgs.setProgress(values[0]);
           txtView.setText(" "+ values[0] + "%");

        }
    }

}
