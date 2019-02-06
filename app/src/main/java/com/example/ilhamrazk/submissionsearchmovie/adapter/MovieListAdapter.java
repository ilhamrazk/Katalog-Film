package com.example.ilhamrazk.submissionsearchmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ilhamrazk.submissionsearchmovie.DetailActivity;
import com.example.ilhamrazk.submissionsearchmovie.GlideApp;
import com.example.ilhamrazk.submissionsearchmovie.MainActivity;
import com.example.ilhamrazk.submissionsearchmovie.MyGlideApp;
import com.example.ilhamrazk.submissionsearchmovie.R;
import com.example.ilhamrazk.submissionsearchmovie.model.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Response.Result> list = new ArrayList<>();


    public MovieListAdapter(List<Response.Result> data){
        list.addAll(data);
    }

    public void addData(List<Response.Result> data){
        this.list.clear();
        this.list.addAll(data);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(list.get(i).title);
        viewHolder.overview.setText(list.get(i).overview);
        GlideApp.with(viewHolder.posterMovie.getContext()).load("https://image.tmdb.org/t/p/w185"+list.get(i).posterPath).into(viewHolder.posterMovie);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(v.getContext(), DetailActivity.class);
//                moveIntent.putExtra(moveIntent.EXTRA_TITLE, list.get(i).title);
                moveIntent.putExtra("title", list.get(i).title);
                moveIntent.putExtra("overview", list.get(i).overview);
                moveIntent.putExtra("rating", list.get(i).voteAverage);
                moveIntent.putExtra("language", list.get(i).originalLanguage);
                moveIntent.putExtra("Release", list.get(i).releaseDate);
                String urlposter = "https://image.tmdb.org/t/p/w185"+list.get(i).posterPath;
                moveIntent.putExtra("poster", urlposter);
                v.getContext().startActivity(moveIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView overview;
        ImageView posterMovie;

        public ViewHolder (View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            overview = itemView.findViewById(R.id.movie_overview);
            posterMovie = itemView.findViewById(R.id.img_movie);

        }


    }
}
