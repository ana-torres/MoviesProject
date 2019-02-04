package eoi.es.recycleview.data.api.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FavouriteRealm extends RealmObject {

    @PrimaryKey
    Long movieId;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
