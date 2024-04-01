package com.example.androidjavademo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidjavademo.R;
import com.example.androidjavademo.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {
    Context context;
    List<Movie> movieList;

    public RecyclerAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.tvMovieName.setText(movieList.get(position).getTitle());

//        Glide.with(context).load(Uri.parse(movieList.get(position).getImageUrl())).into(holder.image);
        Picasso.get().load(movieList.get(position).getImageUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        if(movieList!=null){
            return movieList.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tvMovieName;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName=itemView.findViewById(R.id.title);
            image=itemView.findViewById(R.id.image);
        }
    }
}
