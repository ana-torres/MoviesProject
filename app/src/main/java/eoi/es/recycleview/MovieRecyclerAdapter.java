package eoi.es.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import eoi.es.recycleview.data.api.RestClient;
import eoi.es.recycleview.data.entity.Movie;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    Context context;
    int resource;
    ArrayList<Movie> movies;

    public MovieRecyclerAdapter(Context context, int resource, ArrayList<Movie> movies) {
        this.context = context;
        this.resource = resource;
        this.movies = movies;
    }

    /**
     * @param viewGroup "padre" de nuestra vista
     * @param i         posición de la celda en la lista
     * @return viewholder creado
     */

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //1.Inflamos la celda que nos llega en resource
        View itemView = LayoutInflater.from(context).
                inflate(resource, viewGroup, false);

        //2.Crear el viewholder
        MovieViewHolder movieViewHolder = new MovieViewHolder(itemView, context);

        //3. Devolvemos el viewHolder que ya tiene los datos
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {

        Movie movie = movies.get(position);
        movieViewHolder.bindMovie(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    //Clase interna al adapter que representa a un viewHolder
    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ImageView ivCover;
        TextView tvTitle;
        TextView tvGenreAndYear;

        public MovieViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            this.context = context;
            ivCover = itemView.findViewById(R.id.ivCover);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvGenreAndYear = itemView.findViewById(R.id.tvGenreAndYear);
        }

        /**
         * Enlaza la película con el viewHolder
         *
         * @param movie película
         */

        public void bindMovie(Movie movie) {
            tvTitle.setText(movie.getTitle());
            String genreAndText = movie.getYear();
            tvGenreAndYear.setText(genreAndText);

            String url = RestClient.imageBaseUrl + movie.getCoverUrl();
            Glide.with(context).load(url).into(ivCover);
        }
    }
}
