package eoi.es.recycleview.data.entity;

import java.util.Calendar;

public class Movie {

    String title;
    String director;
    String genre;
    String description;
    String coverUrl;
    Calendar year;

    public Calendar getYear() {
        return year;
    }

    public void setYear(Calendar year) {
        this.year = year;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
/*
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }*/
}
