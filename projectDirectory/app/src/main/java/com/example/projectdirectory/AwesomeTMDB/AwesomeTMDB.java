package com.example.projectdirectory.AwesomeTMDB;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdirectory.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class AwesomeTMDB extends AppCompatActivity {
    // https://api.themoviedb.org/3/search/movie?api_key=1fa447854b2a0f239a52fb0c6d297ae6&language=en-US&query=search&page=1&include_adult=false

    private static String Base_URL = "https://api.themoviedb.org";
    private static int PAGE = 1;
    private static String API_KEY = "1fa447854b2a0f239a52fb0c6d297ae6";
    private static String LANGUAGE = "en-US";
    private static String Query = "search";
    private static Boolean ADULT = false;

    private RecyclerView recyclerView;
    private EditText editText;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awesome_tmdb);
        editText = findViewById(R.id.editTexttmdb);
        recyclerView = findViewById(R.id.recyclerViewtmdb);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        handler = new Handler();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                handler.removeCallbacks(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //n need to work in here

                        System.out.println("null string pri");
                        Query = editText.getText().toString();
                        if (Query.isEmpty() == false) {

                            Call<MovieResults> call = apiInterface.getMovies(API_KEY, LANGUAGE, Query, PAGE, ADULT);

                            call.enqueue(new Callback<MovieResults>() {
                                @Override
                                public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {

                                    MovieResults results = response.body();
                                    //  System.out.println("total     "+results.getTotalResults());
                                    List<MovieResults.Result> listOfMovies = results.getResults();
                                    CustomAdapterTMDB adapter = new CustomAdapterTMDB(listOfMovies, this);
                                    recyclerView.setAdapter(adapter);
                                }

                                @Override
                                public void onFailure(Call<MovieResults> call, Throwable t) {

                                }
                            });

                        }
                    }
                }, 3000);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
