package eoi.es.recycleview.scenes.movielist;


import java.util.List;

import eoi.es.recycleview.app.App;
import eoi.es.recycleview.data.BusinessCallback;
import eoi.es.recycleview.data.api.RestClient;
import eoi.es.recycleview.data.api.mapper.MovieMapper;
import eoi.es.recycleview.data.dto.ListOfMoviesDTO;
import eoi.es.recycleview.data.entity.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListManager {

    public static void getMovieList(Integer page, final BusinessCallback<List<Movie>> businessCallback) {

        String apiKey = RestClient.apiKey;
        String language = RestClient.language;


        Call<ListOfMoviesDTO> call = App.getInstance().getRestClient().getMovieServices().getUpcomingMovies(apiKey, language, page);

        call.enqueue(new Callback<ListOfMoviesDTO>() {
            @Override
            public void onResponse(Call<ListOfMoviesDTO> call, Response<ListOfMoviesDTO> response) {
                if (response != null && response.isSuccessful()) {
                    ListOfMoviesDTO listOfMoviesDTO = response.body();
                    List<Movie> entityList = MovieMapper.entityListFrom(listOfMoviesDTO.getResults());
                    businessCallback.success(entityList);
                } else {
                    businessCallback.failure(null);
                }

            }

            @Override
            public void onFailure(Call<ListOfMoviesDTO> call, Throwable t) {

                businessCallback.failure(null);

            }
        });


    }
}
