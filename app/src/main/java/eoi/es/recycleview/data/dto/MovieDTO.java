package eoi.es.recycleview.data.dto;

import java.util.Calendar;

public class MovieDTO {

    String title;
    String poster_path;
    Calendar release_date;

    public Calendar getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Calendar release_date) {
        this.release_date = release_date;
    }


/*    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }*/


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


}
