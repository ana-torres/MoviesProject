package eoi.es.recycleview.scenes.moviedetail;


import java.util.List;

import eoi.es.recycleview.app.App;
import eoi.es.recycleview.data.BusinessCallback;
import eoi.es.recycleview.data.api.RestClient;
import eoi.es.recycleview.data.api.mapper.CastMapper;
import eoi.es.recycleview.data.dto.ListOfCastDTO;
import eoi.es.recycleview.data.entity.Cast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailManager {

    public static void getCastBy(Long movieId, final BusinessCallback<List<Cast>> businessCallback) {

        Call<ListOfCastDTO> call = App.getInstance()
                .getRestClient()
                .getMovieServices()
                .getCastFromMovies(movieId, RestClient.apiKey);

        call.enqueue(new Callback<ListOfCastDTO>() {
            @Override
            public void onResponse(Call<ListOfCastDTO> call, Response<ListOfCastDTO> response) {
                if (response.isSuccessful() && response.body() != null) {

                    ListOfCastDTO listOfCast = response.body();
                    List<Cast> entities = CastMapper.entityListFrom(listOfCast.getCast());
                    businessCallback.success(entities);


                } else {

                    businessCallback.failure(null);

                }
            }

            @Override
            public void onFailure(Call<ListOfCastDTO> call, Throwable t) {

                businessCallback.failure(null);
            }
        });

    }
}
