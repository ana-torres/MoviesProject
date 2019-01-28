package eoi.es.recycleview.data.api;

import java.util.concurrent.TimeUnit;

import eoi.es.recycleview.data.api.services.MovieServices;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static final int NOT_FOUND = 404;
    public static final int CONNECT_TIMEOUT = 20000;
    public static final int WRITE_TIMEOUT = 20000;
    public static final int READ_TIMEOUT = 20000;
    public static final int NOT_ACEPTED = 406;

    public static final String apiKey = "2cb88f6bdd09877db8127bcd6208914a";
    public static final String language = "en-US";
    public static String imageBaseUrl = "https://image.tmdb.org/t/p/w500";
    MovieServices movieServices;

    /**
     * Url base de los servicios de la API
     */
    private String baseUrl = "https://api.themoviedb.org/3/";

    //Constructor
    public RestClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);


        OkHttpClient client = okHttpClient.build();

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        initServices(restAdapter);

    }

    public MovieServices getMovieServices() {
        return movieServices;
    }

    private void initServices(Retrofit restAdapter) {
        movieServices = restAdapter.create(MovieServices.class);
    }

}
