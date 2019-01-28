package eoi.es.recycleview.data.api.services;

import eoi.es.recycleview.data.dto.ListOfMoviesDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieServices {

    //https://api.themoviedb.org/3/movie/upcoming?api_key=2cb88f6bdd09877db8127bcd6208914a&language=en-US&page=1
    @GET("movie/upcoming")
    Call<ListOfMoviesDTO> getUpcomingMovies(@Query("api_key") String apiKey,
                                            @Query("language") String language,
                                            @Query("page") Integer page);

}
