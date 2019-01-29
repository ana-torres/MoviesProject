package eoi.es.recycleview.data.api.services;

import eoi.es.recycleview.data.dto.ListOfCastDTO;
import eoi.es.recycleview.data.dto.ListOfMoviesDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieServices {

    //https://api.themoviedb.org/3/movie/upcoming?api_key=2cb88f6bdd09877db8127bcd6208914a&language=en-US&page=1

    /**
     * Obtiene el listado de películas recientes
     *
     * @param apiKey   api Key de la api themoviedb
     * @param language idioma de los resultados
     * @param page     página solicitada
     * @return listado de películas
     */
    @GET("movie/upcoming")
    Call<ListOfMoviesDTO> getUpcomingMovies(@Query("api_key") String apiKey,
                                            @Query("language") String language,
                                            @Query("page") Integer page);

    //https://api.themoviedb.org/3/movie/297802/credits?api_key=2cb88f6bdd09877db8127bcd6208914a

    /**
     * Obtiene el listado de actores de una película
     *
     * @param movieId id de la película
     * @param apiKey  apiKey de la appi themoviedb
     * @return una lista de actores
     */
    @GET("movie/{movieId}/credits")
    Call<ListOfCastDTO> getCastFromMovies(@Path("movieId") Long movieId,
                                          @Query("api_key") String apiKey);
}
