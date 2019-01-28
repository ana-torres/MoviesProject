package eoi.es.recycleview.scenes.movielist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eoi.es.recycleview.MovieRecyclerAdapter;
import eoi.es.recycleview.R;
import eoi.es.recycleview.data.BusinessCallback;
import eoi.es.recycleview.data.entity.Movie;

public class MoviesListActivity extends AppCompatActivity {

    RecyclerView rvMovies;
    ArrayList<Movie> movies = new ArrayList<>();
    MovieRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieslist);

        initView();
        loadData();

    }

    private void initView() {
        //Enlazamos el xml con la variable java
        rvMovies = findViewById(R.id.rvMovies);

        //addMovies();

        adapter = new MovieRecyclerAdapter(this, R.layout.cardview_movie, movies);

        //El layout manager le dice al RV cómo se tiene que pintar de forma lineal

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvMovies.setLayoutManager(manager);
        rvMovies.setAdapter(adapter);
    }

    private void loadData() {
        MoviesListManager.getMovieList(1, new BusinessCallback<List<Movie>>() {
            @Override
            public void success(List<Movie> moviesFromManager) {
                movies.addAll(moviesFromManager);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(Object error) {

                Toast.makeText(MoviesListActivity.this, "Error", Toast.LENGTH_LONG).show();

            }
        });
    }

/*    private void addMovies() {
        for (int item = 0 ; item < 100; item++){
            Movie movie = new Movie();
            movie.setCoverUrl("https://image.tmdb.org/t/p/w600_and_h900_bestv2/z5gqW3fffzQ16Jv5vDScpypq1FA.jpg");
            String title = "Película número " + item;
            movie.setTitle(title);
            movie.setGenre("Género");
            movie.setYear("2019");

            movies.add(movie);
        }*/

}
