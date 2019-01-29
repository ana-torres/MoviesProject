package eoi.es.recycleview.scenes.moviedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import eoi.es.recycleview.R;
import eoi.es.recycleview.data.BusinessCallback;
import eoi.es.recycleview.data.api.RestClient;
import eoi.es.recycleview.data.entity.Cast;
import eoi.es.recycleview.data.entity.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView ivCover;
    TextView tvTitle;
    TextView tvGenreAndYear;
    TextView tvDirector;
    ImageView ivActor1;
    ImageView ivActor2;
    ImageView ivActor3;
    TextView tvDescription;
    List<Cast> castList = new ArrayList<>();
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initView();
        receiveParams();
        prepareView();

        loadData();


    }

    /**
     * Le pide al manager la información de los actores
     */
    private void loadData() {

        MovieDetailManager.getCastBy(movie.getId(), new BusinessCallback<List<Cast>>() {
            @Override
            public void success(List<Cast> casts) {
                int i = 0;
                while (casts != null && i < casts.size() && i < 3) {
                    castList.add(casts.get(i));
                    i++;
                }

                upadteViewWithCastInfo();
            }

            @Override
            public void failure(Object error) {
                //No hago nada
            }
        });
    }

    /**
     * Actualiza la pantalla con la información de los actores
     */
    private void upadteViewWithCastInfo() {

        if (!castList.isEmpty()) {
            setCastImage(castList.get(0), ivActor1);
        }
        if (1 < castList.size()) {
            setCastImage(castList.get(1), ivActor2);
        }
        if (2 < castList.size()) {
            setCastImage(castList.get(2), ivActor3);
        }
    }

    private void setCastImage(Cast cast, ImageView ivActor) {
        String url = RestClient.imageBaseUrl + cast.getProfileImageUrl();
        Glide
                .with(MovieDetailActivity.this)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(ivActor);
    }

    private void receiveParams() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = (Movie) bundle.get("selectedMovie");
        }
    }

    /**
     * Rellena la vista
     */
    private void prepareView() {
        tvTitle.setText(movie.getTitle());
        tvGenreAndYear.setText(movie.getYear());
        String url = RestClient.imageBaseUrl + movie.getCoverUrl();
        Glide.with(this).load(url).into(ivCover);
    }

    private void initView() {
        //Enlazo las variables java con el xml
        ivCover = findViewById(R.id.ivCover);
        tvTitle = findViewById(R.id.tvTitle);
        tvGenreAndYear = findViewById(R.id.tvGenreAndYear);
        tvDirector = findViewById(R.id.tvDirector);
        ivActor1 = findViewById(R.id.ivActor1);
        ivActor2 = findViewById(R.id.ivActor2);
        ivActor3 = findViewById(R.id.ivActor3);
        tvDescription = findViewById(R.id.tvDescription);
    }
}
