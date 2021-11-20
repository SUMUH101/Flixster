package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // inflates a layout from XML and returns the holder with the view layouts in it (viewholder)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView= LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false); //inflate item movie xml
        return new ViewHolder(movieView);
    }
    //populates data into item view through view holder (put data into view inside of the holder)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get movie at position passed in
        Movie movie= movies.get(position);
        // bind the movie data into view Holder
        holder.bind(movie);

    }
    //total count of items in list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String ImageUrl;
            //if phone in landscape mode then Image url is back drop image else its poster image
            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                ImageUrl= movie.getBackdropPath();
            }else{
                ImageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(ImageUrl).into(ivPoster);

        }
    }
}
