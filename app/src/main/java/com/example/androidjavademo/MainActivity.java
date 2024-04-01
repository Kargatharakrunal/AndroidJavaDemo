package com.example.androidjavademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidjavademo.adapter.RecyclerAdapter;
import com.example.androidjavademo.global.ApiClient;
import com.example.androidjavademo.interfaces.ApiInterface;
import com.example.androidjavademo.model.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Movie> movieList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    Thread thread;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        imageView = findViewById(R.id.splash);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), movieList);
        recyclerView.setAdapter(recyclerAdapter);
        thread = new Thread() {
            public void run() {
                try {
                    synchronized (this) {
                        imageView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        wait(5000);

                    }
                } catch (Exception ignored) {
                } finally {

                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<List<Movie>> call = apiInterface.getMovies();
//                    Call<List<Movie>> call = ApiClient.getClient().create(ApiInterface.class).getMovies();
                    call.enqueue(new Callback<List<Movie>>() {
                        @Override
                        public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                            movieList = response.body();
                            recyclerAdapter.setMovieList(movieList);
                            imageView.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Call<List<Movie>> call, Throwable t) {

                        }
                    });

                }
            }

        };
        thread.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
    }
}