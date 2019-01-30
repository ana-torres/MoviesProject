package eoi.es.recycleview.scenes.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eoi.es.recycleview.MovieRecyclerAdapter;
import eoi.es.recycleview.R;
import eoi.es.recycleview.data.BusinessCallback;
import eoi.es.recycleview.data.entity.Movie;
import eoi.es.recycleview.scenes.moviedetail.MovieDetailActivity;

public class MoviesListActivity extends AppCompatActivity {

    RecyclerView rvMovies;
    ArrayList<Movie> movies = new ArrayList<>();
    MovieRecyclerAdapter adapter;
    Integer pageTotalItem;
    int actualPage = 0;
    boolean isLoading = false;

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

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aquí ponemos lo que pasa cuando hago click
                Intent intent = new Intent(MoviesListActivity.this, MovieDetailActivity.class);

                int index = rvMovies.getChildAdapterPosition(view);
                Movie movie = movies.get(index);
                intent.putExtra("selectedMovie", movie);

                startActivity(intent);

            }
        });

        //El layout manager le dice al RV cómo se tiene que pintar de forma lineal

        final GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvMovies.setLayoutManager(manager);
        rvMovies.setAdapter(adapter);
        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                //int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                int lastCompleteVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();

                int actualPageRes = lastCompleteVisibleItemPosition / pageTotalItem;
                actualPageRes++;

                if (isLoading == false && lastCompleteVisibleItemPosition == (totalItemCount - 3)) {
                    loadData();
                }

            }
        });
    }

    private void loadData() {

        isLoading = true;
        actualPage++;

        MoviesListManager.getMovieList(actualPage, new BusinessCallback<List<Movie>>() {
            @Override
            public void success(List<Movie> moviesFromManager) {

                if (actualPage == 1) {
                    pageTotalItem = moviesFromManager.size();
                }

                movies.addAll(moviesFromManager);
                adapter.notifyDataSetChanged();
                isLoading = false;
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
