package com.example.androidjavademo.interfaces;



import com.example.androidjavademo.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("photos/")
    Call<List<Movie>> getMovies();
}
