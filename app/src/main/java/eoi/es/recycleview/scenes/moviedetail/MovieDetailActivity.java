package eoi.es.recycleview.scenes.moviedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eoi.es.recycleview.R;
import eoi.es.recycleview.app.App;
import eoi.es.recycleview.data.BusinessCallback;
import eoi.es.recycleview.data.api.RestClient;
import eoi.es.recycleview.data.api.database.FavouriteRealm;
import eoi.es.recycleview.data.entity.Cast;
import eoi.es.recycleview.data.entity.Movie;
import eoi.es.recycleview.scenes.castlist.CastListActivity;
import io.realm.Realm;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivCover)
    ImageView ivCover;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvGenreAndYear)
    TextView tvGenreAndYear;
    @BindView(R.id.tvDirector)
    TextView tvDirector;
    @BindView(R.id.ivActor1)
    ImageView ivActor1;
    @BindView(R.id.ivActor2)
    ImageView ivActor2;
    @BindView(R.id.ivActor3)
    ImageView ivActor3;
    @BindView(R.id.tvDescription)
    TextView tvDescription;

    private Movie movie;
    @BindView(R.id.btnFavorite)
    ImageButton btnFavorite;
    Realm realm;
    ArrayList<Cast> castList = new ArrayList<>();
    private boolean movieIsFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        realm = App.getInstance().getRealm();
        receiveParams();
        prepareView();

        loadData();


    }

    @OnClick(R.id.llCastList)
    public void OnClicCastList() {
        Intent intent = new Intent(MovieDetailActivity.this, CastListActivity.class);
        intent.putExtra("castList", castList);
        startActivity(intent);


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
        tvDescription.setText(movie.getDescription());


        if (isMovieIsFavourite()) {
            setFavouriteImage();
        } else {
            setNoFavouriteImage();
        }
    }


    /**
     * Comprueba si es favorita la película
     *
     * @return
     */
    public boolean isMovieIsFavourite() {

        FavouriteRealm query = getFavouriteRealm();

        return query != null;

    }

    /**
     * Obtiene de base de datos el objeto de la peli favorita
     *
     * @return
     */
    private FavouriteRealm getFavouriteRealm() {

        //Consulta par aobtener ( si existe ) la película favorita
        return realm
                .where(FavouriteRealm.class)
                .equalTo("movieId", movie.getId()).findFirst();
    }

    @OnClick(R.id.btnFavorite)
    public void onClickFavourite(View view) {

        movieIsFavourite = isMovieIsFavourite();

        //Si es favorita
        if (movieIsFavourite) {

            //Cambio la imagen a no favorita
            setNoFavouriteImage();

            //Elimino de bd el registro
            realm.beginTransaction();
            //Consulta para obtener ( si existe) la película favorita
            FavouriteRealm query = getFavouriteRealm();
            query.deleteFromRealm();
            realm.commitTransaction();

        } else {
            //Cambio la imagen por la de "favorita"
            setFavouriteImage();

            FavouriteRealm favouriteRealm = new FavouriteRealm();
            favouriteRealm.setMovieId(movie.getId());
            realm.beginTransaction();
            realm.insert(favouriteRealm);
            realm.commitTransaction();
        }


    }

    private void setFavouriteImage() {
        btnFavorite
                .setImageDrawable
                        (ContextCompat
                                .getDrawable(this,
                                        R.drawable.ic_heart_red));
    }

    private void setNoFavouriteImage() {
        btnFavorite
                .setImageDrawable
                        (ContextCompat
                                .getDrawable(this,
                                        R.drawable.ic_heart_empty));
    }
}
