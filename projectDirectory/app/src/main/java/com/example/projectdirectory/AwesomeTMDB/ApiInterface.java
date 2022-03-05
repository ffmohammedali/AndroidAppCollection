package com.example.projectdirectory.AwesomeTMDB;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/search/movie?")
    Call<MovieResults> getMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String search,
            @Query("page") int page,

            @Query("include_adult") boolean x

    );

    // https://api.themoviedb.org/3/search/movie?api_key=1fa447854b2a0f239a52fb0c6d297ae6&language=en-US&query=search&page=1&include_adult=false


}
